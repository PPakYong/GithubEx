package com.yhpark.githubex.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yhpark.githubex.R;
import com.yhpark.githubex.adapter.RecyclerAdapter;
import com.yhpark.githubex.api.GitHubUsers;
import com.yhpark.githubex.model.DBHelper;
import com.yhpark.githubex.model.UserResult;
import com.yhpark.githubex.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.Unbinder;
import retrofit2.Response;

/**
 * Created by YongHyeon on 2017-04-06.
 */

public class FrgGetAllUser extends RetrofitFragment {

    @BindView(R.id.etSince)
    EditText etSince;
    @BindView(R.id.btSearch)
    Button btSearch;
    @BindView(R.id.rvResult)
    RecyclerView rvResult;
    @BindView(R.id.btSave)
    Button btSave;
    @BindView(R.id.btNext)
    Button btNext;
    @BindView(R.id.llResult)
    LinearLayout llResult;

    int currentPage = 1;
    final int per_page = 100;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.frg_get_all_user, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    List<UserResult> result = null;

    @Override
    void setViewByResponse(Response response) {
        result = (ArrayList<UserResult>) response.body();

        RecyclerAdapter adapter = new RecyclerAdapter(getActivity(), result);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        rvResult.setLayoutManager(manager);
        rvResult.setAdapter(adapter);
    }

    @OnEditorAction(R.id.etSince)
    public boolean onEditorAction(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            requestEvent();
        }
        return false;
    }

    public void requestEvent() {
        if ("".equals(etSince.getText().toString())) {
            Toast.makeText(getActivity(), getString(R.string.toast_empty_since), Toast.LENGTH_SHORT).show();
        } else {
            GitHubUsers api = (GitHubUsers) new RetrofitClient<>().getClient(GitHubUsers.class);
            api.getAllUsers(etSince.getText().toString(), currentPage, per_page).enqueue(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btSearch, R.id.btSave, R.id.btNext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btSearch:
                requestEvent();
                break;
            case R.id.btSave:
                saveLoader.execute();
                break;
            case R.id.btNext:
                currentPage++;
                requestEvent();
                break;
        }
    }

    AsyncTask saveLoader = new AsyncTask() {
        @Override
        protected Object doInBackground(Object[] params) {
            return DBHelper.getInstance(getActivity()).insertUserData(result);
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (isAdded()) {
                Toast.makeText(getActivity(), (boolean) o ? getString(R.string.toast_save_complete) : getString(R.string.toast_save_failed), Toast.LENGTH_SHORT).show();
            }
        }
    };
}
