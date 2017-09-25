package com.starwarsgo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.starwarsgo.application.StarWarsGOApplication;

/**
 * Created by jsantini on 25/09/17.
 */

public class NetworkUtil {

    /**
     * Checks whether there's an active internet connection at the current moment.
     * @return TRUE if there's internet, FALSE if you're offline.
     */
    public static boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) StarWarsGOApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
