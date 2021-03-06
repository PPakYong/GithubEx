package com.yhpark.githubex.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yhpark.githubex.R;
import com.yhpark.githubex.adapter.RecyclerAdapter;
import com.yhpark.githubex.model.DBHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by ppyh0 on 2017-04-07.
 */

public class FrgGetDBUser extends Fragment {

    @BindView(R.id.rvDBResult)
    RecyclerView rvDBResult;
    Unbinder unbinder;
    @BindView(R.id.btRefresh)
    Button btRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.frg_get_db_user, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbLoader.execute();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    AsyncTask dbLoader = new AsyncTask() {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.show();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            return DBHelper.getInstance(getActivity()).getDBUser();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (progressDialog != null) {
                progressDialog.dismiss();
            }

            RecyclerAdapter adapter = new RecyclerAdapter(getActivity(), (List) o);
            RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
            rvDBResult.setLayoutManager(manager);
            rvDBResult.setAdapter(adapter);
        }
    };

    @OnClick(R.id.btRefresh)
    public void onViewClicked() {
        dbLoader.execute();
    }
}
