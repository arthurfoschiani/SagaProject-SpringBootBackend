package br.com.br.saga.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo idFilme não pode ser nulo.")
    @Min(value = 1, message = "O campo idFilme deve ser um número positivo.")
    private Long idFilme;

    @NotNull(message = "O campo idUsuario não pode ser nulo.")
    @Min(value = 1, message = "O campo idUsuario deve ser um número positivo.")
    private Long idUsuario;
}