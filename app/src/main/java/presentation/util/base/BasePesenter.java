package presentation.util.base;

public interface BasePesenter<V> {
    void setView(V view);
    void detachView();
}
