package app.repo.com.gitrepoproject.network;

import java.util.List;

import app.repo.com.gitrepoproject.model.Repo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RepoApi {

    @GET("/orgs/square/repos")
    Call<List<Repo>> getRepos();
}
