package domain.repository;

import dagger.Provides;
import data.network.request.RequestIp;
import io.reactivex.Observable;


public interface AddressRepository {
    Observable<Boolean> validateIp(RequestIp body);
}
