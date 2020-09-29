package br.com.hoptech.btclean.presenter.config;

import br.com.hoptech.btclean.core.usecases.bitcoinprice.BitcoinPriceRepository;
import br.com.hoptech.btclean.core.usecases.bitcoinprice.GetLastBitcoinPriceUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Module {

    @Bean
    GetLastBitcoinPriceUseCase getLastBitcoinPriceUseCase(BitcoinPriceRepository repository) {
        return new GetLastBitcoinPriceUseCase(repository);
    }
}
