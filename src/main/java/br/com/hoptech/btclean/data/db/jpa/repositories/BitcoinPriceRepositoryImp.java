package br.com.hoptech.btclean.data.db.jpa.repositories;

import br.com.hoptech.btclean.core.domain.BitcoinPrice;
import br.com.hoptech.btclean.core.usecases.bitcoinprice.BitcoinPriceRepository;
import br.com.hoptech.btclean.data.db.jpa.entities.BitcoinPriceData;
import org.springframework.stereotype.Repository;

@Repository
public class BitcoinPriceRepositoryImp implements BitcoinPriceRepository {

    private JPABitcoinPriceRepository jpaBitcoinPriceRepository;

    public BitcoinPriceRepositoryImp(JPABitcoinPriceRepository jpaBitcoinPriceRepository) {
        this.jpaBitcoinPriceRepository = jpaBitcoinPriceRepository;
    }

    @Override
    public BitcoinPrice persist(BitcoinPrice bitcoinPrice) {
        final BitcoinPriceData bitcoinPriceData = BitcoinPriceData.from(bitcoinPrice);
        return jpaBitcoinPriceRepository.save(bitcoinPriceData).fromThis();
    }

    @Override
    public BitcoinPrice getLastPrice() {
        return jpaBitcoinPriceRepository.findTopByOrderByIdDesc();
    }
}
