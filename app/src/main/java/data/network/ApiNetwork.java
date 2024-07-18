package data.network;

import data.network.request.RequestIp;
import data.network.responses.ResponseValidate;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiNetwork {

    @POST("/")
    Call<ResponseValidate> sendIpAddress(@Body RequestIp body);

}
