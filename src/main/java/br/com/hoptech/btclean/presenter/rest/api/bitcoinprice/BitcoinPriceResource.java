package br.com.hoptech.btclean.presenter.rest.api.bitcoinprice;

import br.com.hoptech.btclean.core.domain.BitcoinPrice;
import br.com.hoptech.btclean.presenter.rest.api.entities.BitcoinPriceResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/bitcoinprices")
public interface BitcoinPriceResource {

    @GetMapping("/last")
    CompletableFuture<BitcoinPriceResponse> getLastBitcoinPrice();

}
