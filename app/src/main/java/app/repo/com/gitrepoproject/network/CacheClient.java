package app.repo.com.gitrepoproject.network;

import java.io.File;

import app.repo.com.gitrepoproject.RepoApplication;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class CacheClient {

    public static final String CACHE_DIRECTORY = "repo_cache";
    private static OkHttpClient okHttpClient;

    public static OkHttpClient getCacheClient() {
        if (okHttpClient == null) {
            int cacheSize = 10 * 1024 * 1024;
            File httpCacheDirectory =
                    new File(RepoApplication.getAppContext().getCacheDir()
                            , CacheClient.CACHE_DIRECTORY);
            Cache cache = new Cache(httpCacheDirectory, cacheSize);
            okHttpClient = new OkHttpClient.Builder()
                    .cache(cache)
                    .addInterceptor(new CacheIntercepter())
                    .build();
        }
        return okHttpClient;
    }

}
