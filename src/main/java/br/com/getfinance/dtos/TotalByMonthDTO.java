package br.com.getfinance.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TotalByMonthDTO {

    private Double total;
    private LocalDate date;

    public static TotalByMonthDTO fromEntity(Object[] entity){
        return new TotalByMonthDTO(
                (Double) entity[0],
                (LocalDate) entity[1]);
    }
}
