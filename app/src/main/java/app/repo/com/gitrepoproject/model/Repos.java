package app.repo.com.gitrepoproject.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Repos {
    @Expose
    private List<Repo> repos = null;

    public Repos() {
    }

    public List<Repo> getRepos() {
        return repos;
    }

    public void setRepos(List<Repo> repos) {
        this.repos = repos;
    }
}
