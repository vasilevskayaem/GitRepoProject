package app.repo.com.gitrepoproject.network;

import java.io.IOException;

import app.repo.com.gitrepoproject.utils.NetworkUtils;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;

public class CacheIntercepter implements Interceptor {

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        if (!NetworkUtils.internetAvailable()) {
            builder.cacheControl(CacheControl.FORCE_CACHE);
        }
        return chain.proceed(builder.build());
    }
}
