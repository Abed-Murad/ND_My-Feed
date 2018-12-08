package com.am.my_feed.util;

import android.support.v4.app.Fragment;

import com.am.my_feed.network.APIClient;
import com.am.my_feed.network.ApiRequests;

public class BaseFragment extends Fragment {
  protected   ApiRequests apiService = APIClient.getClient().create(ApiRequests.class);
    protected static final String ARG_TITLE = "title";

}
