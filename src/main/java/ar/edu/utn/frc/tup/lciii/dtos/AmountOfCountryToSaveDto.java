package ar.edu.utn.frc.tup.lciii.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmountOfCountryToSaveDto {
    @NotNull(message = "required field" )
    @Max(value = 10, message = "The amount cannot be greater than 10")
    @Min(value = 1,message = "the amount cannot be less than 0")
   private int amountOfCountryToSave;
}
