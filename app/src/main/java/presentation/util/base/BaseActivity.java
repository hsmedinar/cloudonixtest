package presentation.util.base;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        if (savedInstanceState != null) {
            Log.i("savedInstance crate", "ingreso");
        }

        initView();
    }

    public void initView() {
    }



    protected abstract int getLayoutId();
}
