package android.eko.todo;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class InitActivity extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Init  REALM
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name(BuildConfig.DB_NAME)
                .schemaVersion(BuildConfig.DB_VERSION)
                .build();
        Realm.setDefaultConfiguration(config);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Realm.getDefaultInstance().close();
    }
}
