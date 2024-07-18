package presentation.util.base;

import android.content.Context;

public interface BaseView<T> {
    Context getContext();
    void showLoading();
    void hideLoading();
    void showMessage(String errorMessage);
}
