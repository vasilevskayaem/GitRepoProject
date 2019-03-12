package app.repo.com.gitrepoproject.view.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

import app.repo.com.gitrepoproject.dao.Bookmark;
import app.repo.com.gitrepoproject.dao.DaoUtils;
import app.repo.com.gitrepoproject.model.Repo;
import app.repo.com.gitrepoproject.network.CacheClient;
import app.repo.com.gitrepoproject.network.RepoApi;
import app.repo.com.gitrepoproject.network.RepoNetworkController;
import io.realm.Realm;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoViewModel extends AndroidViewModel {
    private MutableLiveData<List<Repo>> downloadedRepos;

    private MutableLiveData<String> error = new MutableLiveData<>();

    public RepoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Repo>> getRepos() {
        if (downloadedRepos == null) {
            downloadedRepos = new MutableLiveData<List<Repo>>();
            loadRepos();
        }
        return downloadedRepos;
    }


    private void loadRepos() {
        OkHttpClient cacheClient = CacheClient.getCacheClient();
        RepoApi repoApi = RepoNetworkController.getApi(cacheClient);
        repoApi.getRepos().enqueue(new Callback<List<Repo>>() {

            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (response.body() != null) {
                    addBookmarksInformation(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                error.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void addBookmarksInformation(List<Repo> repos) {
        //Async task here not use activity context, so it should not be memory leaks
        new AsyncTask<List<Repo>, Void, List<Repo>>() {
            @Override
            protected List<Repo> doInBackground(List<Repo>... lists) {
                Realm realm = Realm.getDefaultInstance();
                List<Bookmark> bookmarks = DaoUtils.getBookmarks(realm);
                for (Repo repo : lists[0]) {
                    boolean isBookmarked = false;
                    for (Bookmark bookmark : bookmarks) {
                        if (repo.getId().equals(bookmark.getId())) {
                            isBookmarked = true;
                            break;
                        }
                    }
                    repo.setBookmarked(isBookmarked);
                }
                realm.close();
                return lists[0];
            }

            @Override
            protected void onPostExecute(List<Repo> repos) {
                downloadedRepos.setValue(repos);
            }
        }.execute(repos);
    }

    public void addBookmarksInformation() {
        if (downloadedRepos != null && downloadedRepos.getValue() != null) {
            addBookmarksInformation(downloadedRepos.getValue());
        }
    }


}
