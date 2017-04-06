package com.yhpark.githubex.fragments;

import android.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ppyh0 on 2017-04-07.
 */

public abstract class RetrofitFragment extends Fragment implements Callback {
    @Override
    public void onResponse(Call call, Response response) {
        Log.i(getClass().getSimpleName(), "onResponse(" + response.raw().request().url() + ")");
        if (response.isSuccessful()) {
            setViewByResponse(response);
        } else {
            Toast.makeText(getActivity(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        Log.i(getClass().getSimpleName(), "onFailure");
        Toast.makeText(getActivity(), "Exception - cause by " + t.getCause(), Toast.LENGTH_LONG).show();
    }

    abstract void setViewByResponse(Response response);
}
