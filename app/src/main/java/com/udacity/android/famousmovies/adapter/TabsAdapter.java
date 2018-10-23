package com.udacity.android.famousmovies.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.udacity.android.famousmovies.R;
import com.udacity.android.famousmovies.data.network.ApiUtils;
import com.udacity.android.famousmovies.fragments.MoviesFragment;

public class TabsAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public  TabsAdapter(Context context, FragmentManager fragmentManager){
        super(fragmentManager);
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return mContext.getString(R.string.tab_popular);
        }else if(position == 1){
            return  mContext.getString(R.string.tab_top_rated);
        }
        return  mContext.getString(R.string.tab_favourite);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if(position ==0){
            fragment = MoviesFragment.newInstance(ApiUtils.POPULAR_MOVIES_FILTER);
        }else if(position == 1){
            fragment = MoviesFragment.newInstance(ApiUtils.TOP_RATED_MOVIES_FILTER);
        }else{
            fragment = MoviesFragment.newInstance(ApiUtils.FAVORITE_MOVIES_FILTER);
        }
        return fragment;
    }
}
