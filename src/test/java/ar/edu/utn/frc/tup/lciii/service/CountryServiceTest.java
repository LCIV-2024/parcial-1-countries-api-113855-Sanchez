package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.AmountOfCountryToSaveDto;
import ar.edu.utn.frc.tup.lciii.dtos.CountryDto;
import ar.edu.utn.frc.tup.lciii.model.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Test
    public void testClient() {

            List<Country> teams = countryService.getAllCountries();
            assertEquals(250, teams.size());

    }

    @Test
    public void testAllCountries() {

        List<CountryDto> teams = countryService.getAllCountryDto(null);
        assertEquals(250, teams.size());

    }

    @Test
    public void testCountryByName() {

        List<CountryDto> teams = countryService.getFindName("ARG");
        assertEquals(1, teams.size());
        assertEquals("ARG", teams.get(0).getCode());

    }

    @Test
    public void testByContinents() {
        List<CountryDto> teams = countryService.getAllCountriesByContinent("Americas");
        assertEquals(56, teams.size());
    }

    @Test
    public void testByLanguages(){
        List<CountryDto> teams = countryService.getAllCountriesByLanguage("English");
        assertEquals(91, teams.size());
    }
    
    @Test
    public void testPostCountries(){
        AmountOfCountryToSaveDto amount = new AmountOfCountryToSaveDto(5);
        List<CountryDto> countries = countryService.postAmountOfCountryToSave(amount);
        assertEquals(5,countries.size());
        amount = new AmountOfCountryToSaveDto(10);
        List<CountryDto> countries2 = countryService.postAmountOfCountryToSave(amount);
        assertEquals(15,countries2.size());

    }

    @Test
    public void testMostBorder(){
        CountryDto china = countryService.getMostBorder();
        assertEquals("China",china.getName());

    }




}
