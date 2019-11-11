package br.com.evoluum.controllers;

import br.com.evoluum.JavaApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MunicipioControllerTest extends JavaApplicationTests {

    private static final int QUANT_OF_CIDADES = 5570;
    private static final int ID_FLORIANOPOLIS = 4205407;

    @Test
    public void shouldReturnAllMunicipiosInJSON() throws Exception {
        mockMvc.perform(get(MunicipioController.URL_GET_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(QUANT_OF_CIDADES)));
    }

    @Test
    public void findAllStatesInJSONByName() throws Exception {
        mockMvc.perform(get(MunicipioController.URL_GET_JSON_BY_NAME, "Florianópolis"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", equalTo(ID_FLORIANOPOLIS)));
    }

}
