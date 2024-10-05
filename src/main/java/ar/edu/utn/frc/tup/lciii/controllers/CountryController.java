package ar.edu.utn.frc.tup.lciii.controllers;
import ar.edu.utn.frc.tup.lciii.dtos.AmountOfCountryToSaveDto;
import ar.edu.utn.frc.tup.lciii.dtos.CountryDto;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import jakarta.validation.Valid;
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
    public List<CountryDto> getCountriesAllByName(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name="code", required = false) String code) {

        return countryService.getAllCountryDto(name,code);
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

    @PostMapping("/api/countries")
    public List<CountryDto> createCountry(@RequestBody @Valid AmountOfCountryToSaveDto amountDto) {
    return countryService.postAmountOfCountryToSave(amountDto);

    }
}