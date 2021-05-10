package com.sssoyalan.soccerleague.adapters;

import android.content.Context;
import android.view.*;
import android.widget.*;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.sssoyalan.soccerleague.R;
import com.sssoyalan.soccerleague.models.FixtureModel;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {
    private List<String> mData;
    private LayoutInflater mInflater;
    private ViewPager2 viewPager2;
    private FixtureAdapter fixtureAdapter = new FixtureAdapter();
    private List<FixtureModel> weeks;
    private Context context;

    private int[] colorArray = new int[]{android.R.color.black, android.R.color.holo_blue_dark, android.R.color.holo_green_dark, android.R.color.holo_red_dark};

    public ViewPagerAdapter(Context context, List<String> data, ViewPager2 viewPager2 ,List<FixtureModel> weeks, FixtureAdapter fixtureAdapter) {
        this.context=context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.viewPager2 = viewPager2;
        this.weeks = weeks;
        this.fixtureAdapter = fixtureAdapter;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_viewpager, parent, false);
        fixtureAdapter = new FixtureAdapter();
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String animal = mData.get(position);
        holder.myTextView.setText(animal);
        fixtureAdapter.getDiffer().submitList(weeks.get(position).getMatch_model());
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        RelativeLayout relativeLayout;
        Button button;
        RecyclerView rvFixture;
        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.week_id);
            relativeLayout = itemView.findViewById(R.id.container);
            button = itemView.findViewById(R.id.btnToggle);
            rvFixture = itemView.findViewById(R.id.rvFixture);
            rvFixture.setAdapter(fixtureAdapter);
            rvFixture.setLayoutManager(new LinearLayoutManager(context));
        }
    }
}
