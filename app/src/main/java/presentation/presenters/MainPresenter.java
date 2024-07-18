package presentation.presenters;

import javax.inject.Inject;

import domain.Params;
import domain.interactors.SendIpAddress;
import domain.interactors.DefaultObserver;
import presentation.contracts.IpAddressContract;
import presentation.util.SDConstants;

public class MainPresenter implements IpAddressContract.presenter {

    private final SendIpAddress sendIpAddress;
    IpAddressContract.view view;

    @Inject
    public MainPresenter(SendIpAddress sendIpAddress) {
        this.sendIpAddress = sendIpAddress;
    }

    @Override
    public void sendIpAddress(String ip) {
        showViewLoading();
        Params params = Params.create();
        params.putString(SDConstants.ip, ip);
        sendIpAddress.execute(new ObserverIpAddress(), params);
    }

    @Override
    public void showResponse(Boolean validate) {
        view.showResponse(validate);
    }

    @Override
    public void setView(IpAddressContract.view view) {
        this.view=view;
    }

    private void showViewLoading() {
        this.view.showLoading();
    }

    private void hideViewLoading() {
        this.view.hideLoading();
    }

    @Override
    public void detachView() {
        this.view=null;
    }

    private void showErrorMessage(String errorMessage) {
        this.view.showMessage(errorMessage);
    }

    private final class ObserverIpAddress extends DefaultObserver<Boolean> {

        @Override
        public void onNext(Boolean aBoolean) {
            hideViewLoading();
            MainPresenter.this.showResponse(aBoolean);
        }

        @Override
        public void onError(Throwable e) {
            MainPresenter.this.showErrorMessage(e.getMessage());
        }

        @Override
        public void onComplete() {

        }
    }


}
