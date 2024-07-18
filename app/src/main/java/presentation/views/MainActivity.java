package presentation.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import com.demo.cloudnix.R;
import com.demo.cloudnix.databinding.ActivityMainBinding;

import javax.inject.Inject;

import di.components.DaggerMainComponent;
import di.components.MainComponent;
import di.modules.AppModule;
import di.modules.MainModule;
import di.modules.NetworkModule;
import presentation.SDApp;
import presentation.contracts.IpAddressContract;
import presentation.presenters.MainPresenter;

public class MainActivity extends AppCompatActivity implements IpAddressContract.view {

    @Inject
    MainPresenter presenter;


    MainComponent component;

    Drawable drawable;

    static {
        System.loadLibrary("native-lib");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDagger();
        presenter.setView(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    protected void onResume() {
        super.onResume();
        String ipAddress = getIPAddress();
        binding.ip.setText(ipAddress);
        presenter.sendIpAddress(ipAddress);
    }

    @Override
    public void showResponse(Boolean validate) {

        binding.content.setVisibility(View.VISIBLE);

        if(validate) {
            drawable = ContextCompat.getDrawable(MainActivity.this, R.drawable.ok_icon);
            binding.response.setText(R.string.positive);
            binding.response.setTextColor(Color.GREEN);
        } else {
            drawable = ContextCompat.getDrawable(MainActivity.this, R.drawable.error_icon);
            binding.response.setText(R.string.negative);
            binding.response.setTextColor(Color.RED);
        }
        binding.response.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void showLoading() {
        binding.loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.loader.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String errorMessage) {

    }

    private void initDagger(){

        if (component == null) {
            component = DaggerMainComponent.builder()
                    .appModule(new AppModule(SDApp.get(this)))
                    .mainModule(new MainModule())
                    .networkModule(new NetworkModule())
                    .build();

            component.inject(this);
        }

    }

    /**
     * A native method that is implemented by the 'cloudnix' native library,
     * which is packaged with this application.
     */
    public native String getIPAddress();
}