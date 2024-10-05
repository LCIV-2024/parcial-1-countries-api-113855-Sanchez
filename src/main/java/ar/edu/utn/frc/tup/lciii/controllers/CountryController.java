package ar.edu.utn.frc.tup.lciii.controllers;
import ar.edu.utn.frc.tup.lciii.dtos.CountryDto;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@AllArgsConstructor
public class CountryController {

    @Autowired
    private CountryService countryService;




    @GetMapping("/api/countries")
    public List<CountryDto> getCountriesAllByName(@RequestParam(name = "name", required = false) String name) {

        return countryService.getAllCountryDto(name);
    }

    @GetMapping ("api/countries/{continent}/continent")
    public List<CountryDto> getCountriesDtoContinent (@PathVariable(name = "continent") String continent) {

        return  countryService.getAllCountriesByContinent(continent);
    }

    @GetMapping ("/api/countries/{language}/language")
    public List<CountryDto> Language (@PathVariable(name = "language") String language) {

        return  countryService.getAllCountriesByLanguage(language);
    }

    @GetMapping ("/api/countries/most-borders")
    public CountryDto mostBorder () {

        return  countryService.getMostBorder();
    }
}