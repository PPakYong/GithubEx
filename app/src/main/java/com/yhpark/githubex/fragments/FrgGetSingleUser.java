package com.yhpark.githubex.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yhpark.githubex.R;
import com.yhpark.githubex.api.GitHubUsers;
import com.yhpark.githubex.model.DBHelper;
import com.yhpark.githubex.model.UserResult;
import com.yhpark.githubex.retrofit.RetrofitClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.Unbinder;
import retrofit2.Response;

/**
 * Created by YongHyeon on 2017-04-06.
 */

public class FrgGetSingleUser extends RetrofitFragment {

    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.btSearch)
    Button btSearch;
    @BindView(R.id.btSave)
    Button btSave;
    @BindView(R.id.llResult)
    LinearLayout llResult;
    Unbinder unbinder;
    @BindView(R.id.tvResult)
    TextView tvResult;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.frg_get_single_user, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    UserResult result;

    @Override
    void setViewByResponse(Response response) {
        Log.i(getClass().getSimpleName(), "onResponse(" + response.raw().request().url() + ")");
        result = (UserResult) response.body();
        if (result != null) {
            tvResult.setText(new Gson().toJson(result).toString().replaceAll(",", ",\n"));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnEditorAction(R.id.etUsername)
    public boolean onEditorAction(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            requestEvent();
        }
        return false;
    }

    @OnClick({R.id.btSearch, R.id.btSave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btSearch:
                requestEvent();
                break;
            case R.id.btSave:
                DBHelper.getInstance(getActivity()).insertUserData(result);
                break;
        }
    }

    public void requestEvent() {
        if ("".equals(etUsername.getText().toString())) {
            Toast.makeText(getActivity(), getString(R.string.toast_empty_user_name), Toast.LENGTH_SHORT).show();
        } else {
            GitHubUsers api = (GitHubUsers) new RetrofitClient<>().getClient(GitHubUsers.class);
            api.getSingleUser(etUsername.getText().toString()).enqueue(this);
        }
    }
}
