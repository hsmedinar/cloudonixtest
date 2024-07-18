package di.modules;

import dagger.Module;
import dagger.Provides;
import data.network.ApiNetwork;
import data.repository.ValidateAddressRepo;
import data.repository.datasource.AddressFactory;
import domain.repository.AddressRepository;

@Module
public class MainModule {

    @Provides
    AddressFactory provideAddressFactoryData(ApiNetwork apiNetwork){
        return new AddressFactory(apiNetwork);
    }

    @Provides
    AddressRepository provideAddressRepository(AddressFactory factoryData){
        return new ValidateAddressRepo(factoryData);
    }

}
