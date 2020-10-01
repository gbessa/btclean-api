package br.com.hoptech.btclean.presenter;

import br.com.hoptech.btclean.core.usecases.bitcoinprice.BitcoinPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.com.hoptech.btclean.presenter", "br.com.hoptech.btclean.data.db.jpa"})
public class BtcleanApplication {

	public static void main(String[] args) {
		SpringApplication.run(BtcleanApplication.class, args);
	}

}
