package br.com.hoptech.btclean.core.usecases.bitcoinprice;

import br.com.hoptech.btclean.core.domain.BitcoinPrice;

public interface BitcoinPriceRepository {

    BitcoinPrice persist(BitcoinPrice bitcoinPrice);

    BitcoinPrice getLastPrice();


}
