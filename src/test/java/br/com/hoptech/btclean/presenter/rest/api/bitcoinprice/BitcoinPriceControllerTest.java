package br.com.hoptech.btclean.presenter.rest.api.bitcoinprice;

import br.com.hoptech.btclean.core.domain.BitcoinPrice;
import br.com.hoptech.btclean.core.entities.TestCoreEntityGenerator;
import br.com.hoptech.btclean.core.usecases.bitcoinprice.GetLastBitcoinPriceUseCase;
import br.com.hoptech.btclean.presenter.rest.api.common.BaseControllerTest;
import br.com.hoptech.btclean.presenter.usecases.UseCaseExecutorImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = BitcoinPriceController.class)
class BitcoinPriceControllerTest extends BaseControllerTest {

    @Configuration
    @ComponentScan(basePackages = {
            "br.com.hoptech.btclean.presenter.rest.api.bitcoinprice",
            "br.com.hoptech.btclean.presenter.rest.api.common"})
    static class Config {
    }

    @SpyBean
    private UseCaseExecutorImpl useCaseExecutor;

    @MockBean
    private GetLastBitcoinPriceUseCase getLastBitcoinPriceUseCase;

    @Autowired
    private MockMvc mockMvc;

    @Override
    protected MockMvc getMockMvc() {
        return mockMvc;
    }

    @Test
    void getLastBitcoinPriceReturnOK() throws Exception {
        // given
        BitcoinPrice bitcoinPrice = TestCoreEntityGenerator.randomBitcoinPrice();
        GetLastBitcoinPriceUseCase.InputValues input = new GetLastBitcoinPriceUseCase.InputValues();
        GetLastBitcoinPriceUseCase.OutputValues output = new GetLastBitcoinPriceUseCase.OutputValues(bitcoinPrice);

        System.out.println("-----------> " + bitcoinPrice.toString());

        // and
        doReturn(output)
                .when(getLastBitcoinPriceUseCase)
                .execute(input);

        // when
        final RequestBuilder payload = asyncGetRequest("/bitcoinprices/last");

        System.out.println("-----------> " + payload.toString());

        // then
        mockMvc.perform(payload)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("time", is(bitcoinPrice.getTime())))
                .andExpect(jsonPath("usdrate", is(bitcoinPrice.getUsdRate())))
                .andExpect(jsonPath("eurrate", is(bitcoinPrice.getEurRate())));

    }

    @Test
    void getLastBitcoinPriceReturnNotFound() throws Exception {
        // given
        GetLastBitcoinPriceUseCase.InputValues input = new GetLastBitcoinPriceUseCase.InputValues();
        GetLastBitcoinPriceUseCase.OutputValues output = new GetLastBitcoinPriceUseCase.OutputValues(null);

        // and
        doReturn(output)
                .when(getLastBitcoinPriceUseCase)
                .execute(input);

        // when
        final RequestBuilder payload = asyncGetRequest("/bitcoinprices/last");

        // then
        mockMvc.perform(payload)
                .andExpect(status().isNotFound());
    }
}