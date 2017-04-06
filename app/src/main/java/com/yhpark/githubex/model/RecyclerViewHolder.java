package com.yhpark.githubex.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yhpark.githubex.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YongHyeon on 2017-04-06.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvResultItem)
    public TextView tvResultItem;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
