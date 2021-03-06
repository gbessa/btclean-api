package br.com.hoptech.btclean.core.usecases.bitcoinprice;

import br.com.hoptech.btclean.core.domain.BitcoinPrice;
import br.com.hoptech.btclean.core.usecases.UseCase;
import lombok.Value;

public class GetLastBitcoinPriceUseCase implements UseCase<GetLastBitcoinPriceUseCase.InputValues, GetLastBitcoinPriceUseCase.OutputValues> {
    private BitcoinPriceRepository repository;

    public GetLastBitcoinPriceUseCase(BitcoinPriceRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        System.out.println("========> GetLastBitcoinPriceUseCase execute");
        repository.persist(new BitcoinPrice(123L, "123123123123", 123123.000, 321212.09090));
        return new OutputValues(repository.getLastPrice());
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final BitcoinPrice bitcoinPrice;
    }
}
