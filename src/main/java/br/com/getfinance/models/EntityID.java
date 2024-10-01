package br.com.getfinance.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@ToString
public class EntityID {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
