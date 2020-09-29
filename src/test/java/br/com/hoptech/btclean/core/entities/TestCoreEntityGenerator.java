package br.com.hoptech.btclean.core.entities;

import br.com.hoptech.btclean.core.domain.BitcoinPrice;
import com.github.javafaker.Faker;

import java.util.concurrent.TimeUnit;

public class TestCoreEntityGenerator {
    private static final Faker faker = new Faker();

    public static BitcoinPrice randomBitcoinPrice() {
        return new BitcoinPrice(faker.number().randomNumber(), faker.date().past(1000, 4, TimeUnit.SECONDS).toString(), 1200.0, 1000.0);
    }

}
