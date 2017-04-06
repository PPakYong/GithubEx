package com.yhpark.githubex.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yhpark.githubex.R;
import com.yhpark.githubex.model.RecyclerViewHolder;
import com.yhpark.githubex.model.UserResult;

import java.util.List;

/**
 * Created by ppyh0 on 2017-04-06.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<UserResult> results;
    private Context context;

    public RecyclerAdapter(Context context, List<UserResult> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.row_user_result, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        holder.tvResultItem.setText(results.get(position).login);
        ((View) holder.tvResultItem.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, results.get(position).login, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}
