package app.repo.com.gitrepoproject;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import app.repo.com.gitrepoproject.dao.DaoUtils;
import app.repo.com.gitrepoproject.model.Repo;
import app.repo.com.gitrepoproject.view.model.DeteilsViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class DeteilsActivity extends AppCompatActivity {
    public static final String REPO = "repo";
    @BindView(R.id.name_text)
    TextView nameTextView;
    @BindView(R.id.stars_text)
    TextView starsTextView;
    @BindView(R.id.add_to_bookmarks_button)
    Button addToBoomarks;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private DeteilsViewModel deteilsViewModel;
    private Realm realm;
    private Repo repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deteils);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        realm = Realm.getDefaultInstance();
        deteilsViewModel = ViewModelProviders.of(this).get(DeteilsViewModel.class);
        repo = deteilsViewModel.initRepo((Repo) getIntent().getSerializableExtra(REPO));
        initUi();
    }

    private void initUi() {
        nameTextView.setText(repo.getName());
        starsTextView.setText(String.valueOf(repo.getStargazersCount()));
        addToBoomarks.setText(repo.isBookmarked() ? R.string.remove_from_bookmarks : R.string.add_to_bookmarks);
        addToBoomarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repo.isBookmarked()) {
                    removeToBookmarks();
                } else {
                    addToBookmarks();
                }
            }
        });
    }

    private void addToBookmarks() {
        repo.setBookmarked(true);
        addToBoomarks.setText(R.string.remove_from_bookmarks);
        DaoUtils.addToBookmark(repo.getId(), realm);

    }

    private void removeToBookmarks() {
        repo.setBookmarked(false);
        addToBoomarks.setText(R.string.add_to_bookmarks);
        DaoUtils.removeFromBookmarks(repo.getId(), realm);
    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}
