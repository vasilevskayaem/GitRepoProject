package app.repo.com.gitrepoproject.dao;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class DaoUtils {
    public static List<Bookmark> getBookmarks(Realm realm) {
        List<Bookmark> result = realm.where(Bookmark.class).findAll();
        return result;
    }

    public static void addToBookmark(Long id, Realm realm) {
        final Bookmark bookmark = new Bookmark(id);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(bookmark);
            }
        });
    }

    public static void removeFromBookmarks(final long id, Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Bookmark> results = realm.where(Bookmark.class).equalTo("id", id).findAll();
                results.deleteAllFromRealm();
            }
        });
    }
}
