package data.repository.datasource;

import android.util.Log;

import com.google.gson.Gson;

import data.exceptions.MyException;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RxHelper {
    private static final String TAG = "RxHelper";  //RxHelper.class.getName();

    @NonNull
    public static <T>Observable<T> getObserbable(@NonNull final Call<T> reponse){

        Log.i(TAG, reponse.request().url() + "   header url");
        Log.i(TAG, reponse.request().headers() + "   header ");

        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(final ObservableEmitter<T> emitter) throws Exception {

                reponse.enqueue(new Callback<T>() {
                    @Override
                    public void onResponse(Call<T> call, Response<T> response) {

                        Log.i(TAG,   "onResponse");

                        Gson g = new Gson();

                        Log.i(TAG, response.code() + "");
                        Log.i(TAG, g.toJson(response.body()) + "");
                        Log.i(TAG, response.message() + "");
                        Log.i(TAG, response.errorBody() + "");


                        if (!emitter.isDisposed()) {
                            if(response.isSuccessful()){
                                emitter.onNext(response.body());
                            }else{
                                MyException myException = new MyException("Ups algo salio mal!");
                                emitter.onError(myException);
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<T> call, Throwable t) {
//                        Log.i(TAG,   "onFailure");
//                        Log.i(TAG,   t.getMessage());

                        if (!emitter.isDisposed()) {
                            MyException myException = new MyException("Ocurrio un problema, por favor revisa tu conexion");
                            emitter.onError(myException);
                        }
                    }

                });
            }
        });
    }



    @NonNull
    public static <T>Observable<T> getObserbablePasarella(@NonNull final Call<T> reponse){

        Log.i(TAG, reponse.request().url() + "");
        Log.i(TAG, reponse.request().headers() + "");

        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(final ObservableEmitter<T> emitter) throws Exception {

                reponse.enqueue(new Callback<T>() {
                    @Override
                    public void onResponse(Call<T> call, Response<T> response) {

                        Log.i(TAG,   "onResponse");

                        Gson g = new Gson();

//
                        Log.i(TAG, response.code() + "");
                        Log.i(TAG, g.toJson(response.body()) + "");
                        Log.i(TAG, response.message() + "");
                        Log.i(TAG, response.errorBody() + "");
                        Log.i(TAG, g.toJson(response.errorBody()) + "");


                        if (!emitter.isDisposed()) {
                            if(response.body()!=null)
                                emitter.onNext(response.body());
                            else
                                emitter.onError(new MyException("Problemas procesando su tarjeta, intente con una nueva tarjeta!"));
                        }
                    }

                    @Override
                    public void onFailure(Call<T> call, Throwable t) {
                        if (!emitter.isDisposed()) {
                            emitter.onError(t);
                        }
                    }

                });
            }
        });
    }



}
