package br.com.hoptech.btclean.core.domain;

import lombok.Value;

@Value
public class BitcoinPrice {

    private final Long id;
    private final String time;
    private final double usdRate;
    private final double eurRate;

}
