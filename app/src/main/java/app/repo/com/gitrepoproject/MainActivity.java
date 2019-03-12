package app.repo.com.gitrepoproject;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import app.repo.com.gitrepoproject.adapters.ReposAdapter;
import app.repo.com.gitrepoproject.model.Repo;
import app.repo.com.gitrepoproject.view.model.RepoViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.repos_list)
    RecyclerView reposList;
    private RepoViewModel repoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        progressBar.setVisibility(View.VISIBLE);
        repoViewModel = ViewModelProviders.of(this).get(RepoViewModel.class);
        repoViewModel.getRepos().observe(this, new Observer<List<Repo>>() {
            @Override
            public void onChanged(@Nullable List<Repo> repos) {
                progressBar.setVisibility(View.GONE);
                ReposAdapter reposAdapter = new ReposAdapter(repos);
                reposList.setAdapter(reposAdapter);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        repoViewModel.addBookmarksInformation();
    }
}
