package domain.mapper;

import data.network.request.RequestIp;
import domain.Params;
import presentation.util.SDConstants;

public class AddressMapper {

    public static RequestIp transformRequestIp(Params params) {
        RequestIp requets = new RequestIp();

        if(params!=null){
            requets.setAddress(params.getString(SDConstants.ip, ""));
        }

        return requets;
    }

}
