package com.yhpark.githubex.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yhpark.githubex.R;
import com.yhpark.githubex.model.UserResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by YongHyeon on 2017-04-06.
 */

public class FrgUserDetail extends Fragment {
    @BindView(R.id.tvDetail)
    TextView tvDetail;
    @BindView(R.id.btClose)
    Button btClose;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.frg_user_detail, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            UserResult result = getArguments().getParcelable("result");
            tvDetail.setText(new Gson().toJson(result).toString().replaceAll(",", ",\n"));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btClose)
    public void onViewClicked() {
        if (isAdded()) {
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        }
    }
}
