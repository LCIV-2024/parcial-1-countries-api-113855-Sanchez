package ar.edu.utn.frc.tup.lciii.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CountryControllersTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listCountries() throws Exception {
        this.mockMvc.perform(get("/api/countries")).andDo(print()).andExpect(status().isOk());

    }
    @Test
    public void listCountriesByName() throws Exception {
        this.mockMvc.perform(get("/api/countries?name=ARG")).andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void getCountryByContinent() throws Exception {
        this.mockMvc.perform(get("/api/countries/Americas/continent")).andDo(print()).andExpect(status().isOk());

    }
    @Test
    public void getCountryByLanguages() throws Exception {
        this.mockMvc.perform(get("/api/countries/English/language")).andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void postCountry() throws Exception {
        this.mockMvc.perform(post("/api/countries")
                        .contentType("application/json")
                        .content("{\"amountOfCountryToSave\":11}"))
                .andExpect(status().is5xxServerError());
    }

}
