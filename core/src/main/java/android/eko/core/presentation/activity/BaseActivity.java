package android.eko.core.presentation.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseActivity<BINDER extends ViewDataBinding> extends AppCompatActivity {

    protected BINDER binder;

    protected abstract void injectDependencies();

    protected abstract @LayoutRes
    int getLayoutId();

    /**
     * The onCreate base will init this view
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = DataBindingUtil.setContentView(this, getLayoutId());

        injectDependencies();
        initView();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        unbindViews();
        super.onDestroy();
    }

    protected void startActivity(Class activityClass) {
        Intent i = new Intent(this, activityClass);
        startActivity(i);
    }

    private void unbindViews() {
        if (binder != null)
            binder.unbind();
    }
}
