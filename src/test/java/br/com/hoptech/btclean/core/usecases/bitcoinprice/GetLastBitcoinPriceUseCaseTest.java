package br.com.hoptech.btclean.core.usecases.bitcoinprice;

import br.com.hoptech.btclean.core.domain.BitcoinPrice;
import br.com.hoptech.btclean.core.entities.TestCoreEntityGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class GetLastBitcoinPriceUseCaseTest {

    @Mock
    BitcoinPriceRepository repository;


    @InjectMocks
    GetLastBitcoinPriceUseCase useCase;

    @Test
    public void returnLastBitcoinPrice() {

        // given
        BitcoinPrice bitcoinPrice = TestCoreEntityGenerator.randomBitcoinPrice();
        BitcoinPrice toBeMatched = new BitcoinPrice(
                bitcoinPrice.getId(),
                bitcoinPrice.getTime(),
                bitcoinPrice.getUsdRate(),
                bitcoinPrice.getEurRate());

        // and
        doReturn(bitcoinPrice)
                .when(repository)
                .getLastPrice();


        // when
        final BitcoinPrice actual = useCase.execute(new GetLastBitcoinPriceUseCase.InputValues()).getBitcoinPrice();

        // then
        assertEquals(actual, toBeMatched);

    }

    @Test
    public void noBitcoinPriceStored() {

        // given
        doReturn(null)
                .when(repository)
                .getLastPrice();

        // when
        final BitcoinPrice actual = useCase.execute(new GetLastBitcoinPriceUseCase.InputValues()).getBitcoinPrice();

        // then
        assertNull(actual);

    }



}