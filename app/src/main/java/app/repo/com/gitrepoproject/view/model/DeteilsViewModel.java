package app.repo.com.gitrepoproject.view.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import app.repo.com.gitrepoproject.model.Repo;

public class DeteilsViewModel extends AndroidViewModel {
    MutableLiveData<Repo> repo;

    public DeteilsViewModel(@NonNull Application application) {
        super(application);
    }


    public Repo initRepo(Repo repo) {
        // if repo exists just use old value, for example after rotation
        if (this.repo == null) {
            this.repo = new MutableLiveData<>();
            this.repo.setValue(repo);
        }
        return this.repo.getValue();
    }
}
