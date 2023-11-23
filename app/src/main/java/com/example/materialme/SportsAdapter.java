package com.example.materialme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.SportsViewHolder> {
    private GradientDrawable mGradientDrawable;
    private ArrayList<SportActivity> mSportsData;
    private Context mContext;

    SportsAdapter(Context context, ArrayList<SportActivity> sportsData) {
        this.mSportsData = sportsData;
        this.mContext = context;

        mGradientDrawable = new GradientDrawable();
        mGradientDrawable.setColor(Color.GRAY);

        Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.img_badminton);
        if (drawable != null) {
            mGradientDrawable.setSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
    }

    @NonNull
    @Override
    public SportsAdapter.SportsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SportsViewHolder(mContext, LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false), mGradientDrawable);
    }

    @Override
    public void onBindViewHolder(@NonNull SportsViewHolder holder, int position) {
        SportActivity currentSport = mSportsData.get(position);
        holder.bindTo(currentSport);
    }

    @Override
    public int getItemCount() {
        return mSportsData.size();
    }

    public static class SportsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private GradientDrawable gradientDrawable;
        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mSportsImage;
        private Context mContext;
        private SportActivity mCurrentSport;
        private GradientDrawable mGradientDrawable;
        public SportsViewHolder(@NonNull Context context, View itemView, GradientDrawable mGradientDrawable) {
            super(itemView);

            mTitleText = (TextView) itemView.findViewById(R.id.title);
            mInfoText = (TextView) itemView.findViewById(R.id.subTitle);
            mSportsImage = (ImageView) itemView.findViewById(R.id.sportsImage);

            mContext = context;
            mGradientDrawable = gradientDrawable;

            itemView.setOnClickListener(this);
        }

        public void bindTo(SportActivity currentSport) {
            mTitleText.setText(currentSport.getTitle());
            mInfoText.setText(currentSport.getInfo());

            mCurrentSport = currentSport;

            Glide.with(mContext).load(currentSport.
                    getImageResource()).placeholder(mGradientDrawable).into(mSportsImage);
        }


        @Override
        public void onClick(View view) {
            Intent detailIntent = SportActivity.starter(mContext, mCurrentSport.getTitle(),
                    mCurrentSport.getImageResource());

            mContext.startActivity(detailIntent);
        }
    }
}
