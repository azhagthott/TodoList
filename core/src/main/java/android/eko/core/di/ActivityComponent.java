package android.eko.core.di;

import android.app.Activity;

public interface ActivityComponent<T extends Activity> {
    void inject(T target);
}