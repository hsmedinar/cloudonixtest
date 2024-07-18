package presentation.contracts;

import presentation.util.base.BasePesenter;
import presentation.util.base.BaseView;

public interface IpAddressContract {

    interface view extends BaseView {
        void showResponse(Boolean validate);
    }

    interface  presenter extends BasePesenter<view> {
        void sendIpAddress(String ip);
        void showResponse(Boolean validate);
    }

}
