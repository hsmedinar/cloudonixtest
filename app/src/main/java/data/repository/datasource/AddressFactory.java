package data.repository.datasource;

import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;

import data.network.ApiNetwork;
import data.network.request.RequestIp;
import data.network.responses.ResponseValidate;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class AddressFactory {

    final private ApiNetwork apiNetwork;


    @Inject
    public AddressFactory(ApiNetwork apiNetwork) {
        this.apiNetwork = apiNetwork;
    }

    public Observable<Boolean> validateAddress(RequestIp body) {
        Gson g = new Gson();
        Log.i("body", g.toJson(body));
        return RxHelper.getObserbable(apiNetwork.sendIpAddress(body)).map(new Function<ResponseValidate, Boolean>() {
            @Override
            public Boolean apply(ResponseValidate responseValidate) throws Exception {


                Gson g = new Gson();
                Log.i("respuesta", g.toJson(responseValidate));

                return responseValidate.getNat();
            }
        });

    }

}
