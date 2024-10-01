package br.com.getfinance.models;

import br.com.getfinance.enums.SourceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_incomes")
public class Income extends EntityID{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "source")
    @Enumerated(EnumType.STRING)
    private SourceType sourceType;

    @Column(name = "description")
    private String description;

    @Column(name = "income_date")
    private LocalDate date;
}
