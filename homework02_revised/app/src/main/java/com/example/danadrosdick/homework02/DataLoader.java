package com.example.danadrosdick.homework02;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

//extended from lab05
/*this is an extension from Lab05. The purpose of this method is to take the Data and load it */


class DataLoader extends AsyncTaskLoader<String> {

    private String mQueryString;

    public DataLoader(Context c, String queryString){
        super(c);
        mQueryString = queryString;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        return NetworkUtils.getData(mQueryString);
    }
}
