package br.com.hoptech.btclean.presenter.rest.api.bitcoinprice;

import br.com.hoptech.btclean.core.domain.BitcoinPrice;
import br.com.hoptech.btclean.core.usecases.UseCaseExecutor;
import br.com.hoptech.btclean.core.usecases.bitcoinprice.GetLastBitcoinPriceUseCase;
import br.com.hoptech.btclean.presenter.rest.api.entities.BitcoinPriceResponse;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class BitcoinPriceController implements BitcoinPriceResource {
    private UseCaseExecutor useCaseExecutor;
    private GetLastBitcoinPriceUseCase getLastBitcoinPriceUseCase;

    public BitcoinPriceController(UseCaseExecutor useCaseExecutor, GetLastBitcoinPriceUseCase getLastBitcoinPriceUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.getLastBitcoinPriceUseCase = getLastBitcoinPriceUseCase;
    }


    @Override
    public CompletableFuture<BitcoinPriceResponse> getLastBitcoinPrice() {
        System.out.println("$$$$$$$$$ getLastBitcoinPrice Controller");
        return useCaseExecutor.execute(
                getLastBitcoinPriceUseCase,
                new GetLastBitcoinPriceUseCase.InputValues(),
                outputValues -> BitcoinPriceResponse.from(outputValues.getBitcoinPrice()));
    }

}
