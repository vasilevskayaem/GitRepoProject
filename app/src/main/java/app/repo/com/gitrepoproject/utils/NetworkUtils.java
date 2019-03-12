package app.repo.com.gitrepoproject.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import app.repo.com.gitrepoproject.RepoApplication;

public class NetworkUtils {


    public static boolean internetAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) RepoApplication
                .getAppContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
