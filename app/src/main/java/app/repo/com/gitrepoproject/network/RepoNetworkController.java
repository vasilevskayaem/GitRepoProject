package app.repo.com.gitrepoproject.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepoNetworkController {
    public static final String REPO_URL = "https://api.github.com";


    private static RepoApi repoApi;

    public static RepoApi getApi(OkHttpClient cacheClient) {
        if (repoApi == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(REPO_URL)
                    .client(cacheClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            repoApi = retrofit.create(RepoApi.class);
        }
        return repoApi;
    }
}
