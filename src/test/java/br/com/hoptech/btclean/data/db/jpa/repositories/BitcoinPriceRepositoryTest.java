package br.com.hoptech.btclean.data.db.jpa.repositories;

import br.com.hoptech.btclean.core.domain.BitcoinPrice;
import br.com.hoptech.btclean.core.entities.TestCoreEntityGenerator;
import br.com.hoptech.btclean.data.db.jpa.entities.BitcoinPriceData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BitcoinPriceRepositoryTest {

    @Autowired
    private JPABitcoinPriceRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @AutoConfigurationPackage
    @Configuration
    @EntityScan("br.com.hoptech.btclean.data.db.jpa.entities")
    static class Config {
    }

    @Test
    public void saveBitcoinPrice() {
        // given
        BitcoinPrice bitcoinPrice = TestCoreEntityGenerator.randomBitcoinPrice();
        BitcoinPriceData bitcoinPriceData = new BitcoinPriceData(null, bitcoinPrice.getTime(), bitcoinPrice.getUsdRate(), bitcoinPrice.getEurRate());

        // and
        bitcoinPriceData = entityManager.persistFlushFind(bitcoinPriceData);

        // when
        BitcoinPriceData savedBitcoinPrice = repository.save(bitcoinPriceData);

        // then
        assertThat(savedBitcoinPrice.getId()).isNotNull();
    }

    @Test
    public void getLastBitcoinPrice() {
        // given
        BitcoinPrice bitcoinPriceFirst = TestCoreEntityGenerator.randomBitcoinPrice();
        BitcoinPrice bitcoinPriceSecond = TestCoreEntityGenerator.randomBitcoinPrice();
        BitcoinPrice bitcoinPriceLast = TestCoreEntityGenerator.randomBitcoinPrice();
        BitcoinPriceData bitcoinPriceDataFirst = new BitcoinPriceData(null, bitcoinPriceFirst.getTime(), bitcoinPriceFirst.getUsdRate(), bitcoinPriceFirst.getEurRate());
        BitcoinPriceData bitcoinPriceDataSecond = new BitcoinPriceData(null, bitcoinPriceSecond.getTime(), bitcoinPriceSecond.getUsdRate(), bitcoinPriceSecond.getEurRate());
        BitcoinPriceData bitcoinPriceDataLast = new BitcoinPriceData(null, bitcoinPriceLast.getTime(), bitcoinPriceLast.getUsdRate(), bitcoinPriceLast.getEurRate());

        // and
        bitcoinPriceDataFirst = entityManager.persistFlushFind(bitcoinPriceDataFirst);
        bitcoinPriceDataSecond = entityManager.persistFlushFind(bitcoinPriceDataSecond);
        bitcoinPriceDataLast = entityManager.persistFlushFind(bitcoinPriceDataLast);

        // when
        BitcoinPrice actual = repository.findTopByOrderByIdDesc();

        // then
        assertEquals(actual.getTime(), bitcoinPriceDataLast.getTime());
    }
}