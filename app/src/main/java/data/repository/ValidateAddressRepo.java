package data.repository;

import javax.inject.Inject;

import data.network.request.RequestIp;
import data.repository.datasource.AddressFactory;
import domain.repository.AddressRepository;
import io.reactivex.Observable;

public class ValidateAddressRepo implements AddressRepository {

    private final AddressFactory factory;

    @Inject
    public ValidateAddressRepo(AddressFactory factory) {
        this.factory = factory;
    }

    @Override
    public Observable<Boolean> validateIp(RequestIp body) {
        return factory.validateAddress(body);
    }
}
