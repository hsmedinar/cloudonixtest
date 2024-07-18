package domain.interactors;

import javax.inject.Inject;

import data.network.request.RequestIp;
import domain.Params;
import domain.mapper.AddressMapper;
import domain.repository.AddressRepository;
import io.reactivex.Observable;

public class SendIpAddress extends UseCase<Boolean, Params> {

    private final AddressRepository repository;

    @Inject
    public SendIpAddress(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    Observable<Boolean> buildUseCaseObservable(Params params) {
        return repository.validateIp(AddressMapper.transformRequestIp(params));
    }
}
