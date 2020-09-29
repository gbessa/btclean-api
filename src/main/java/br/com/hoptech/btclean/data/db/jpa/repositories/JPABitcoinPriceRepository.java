package br.com.hoptech.btclean.data.db.jpa.repositories;

import br.com.hoptech.btclean.core.domain.BitcoinPrice;
import br.com.hoptech.btclean.data.db.jpa.entities.BitcoinPriceData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPABitcoinPriceRepository extends JpaRepository<BitcoinPriceData, Long> {

    BitcoinPrice findTopByOrderByIdDesc();
}
