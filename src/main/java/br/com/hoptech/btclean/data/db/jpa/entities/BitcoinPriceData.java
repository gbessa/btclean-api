package br.com.hoptech.btclean.data.db.jpa.entities;

import br.com.hoptech.btclean.core.domain.BitcoinPrice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "bitcoinPrice")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "bitcoinPrice")

public class BitcoinPriceData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String time;

    @Column(nullable = false)
    private double usdRate;

    @Column(nullable = false)
    private double eurRate;

    public static BitcoinPriceData from(BitcoinPrice bitcoinPrice) {
        return new BitcoinPriceData(null, bitcoinPrice.getTime(), bitcoinPrice.getUsdRate(), bitcoinPrice.getEurRate());
    }

    public BitcoinPrice fromThis() {
        return new BitcoinPrice(
                id,
                time,
                usdRate,
                eurRate
        );
    }
}
