package br.com.hoptech.btclean.presenter.rest.api.entities;

import br.com.hoptech.btclean.core.domain.BitcoinPrice;
import lombok.Value;

@Value
public class BitcoinPriceResponse {
    private final String time;
    private final double usdrate;
    private final double eurrate;

    public static BitcoinPriceResponse from(BitcoinPrice bitcoinPrice) {
        return new BitcoinPriceResponse(
                bitcoinPrice.getTime(),
                bitcoinPrice.getUsdRate(),
                bitcoinPrice.getEurRate()
        );
    }
}
