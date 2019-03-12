package app.repo.com.gitrepoproject.dao;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Bookmark extends RealmObject {
    @PrimaryKey
    private long id;

    public Bookmark() {
    }

    public Bookmark(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
