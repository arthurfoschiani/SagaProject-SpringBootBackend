package br.com.br.saga.model;

import java.time.LocalDate;

import br.com.br.saga.validation.IdadeMinima;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo email não pode estar em branco.")
    @Email(message = "O email deve ser válido.")
    private String email;

    @NotBlank(message = "O campo nome não pode estar em branco.")
    @Size(min = 2, max = 50, message = "O nome deve conter entre 2 e 50 caracteres.")
    private String nome;

    @NotBlank(message = "O campo senha não pode estar em branco.")
    @Size(min = 6, message = "A senha deve conter pelo menos 6 caracteres.")
    private String senha;

    @Past(message = "A data de nascimento deve estar no passado.")
    @IdadeMinima(message = "O usuário deve ter no mínimo 12 anos de idade.")
    LocalDate dataNascimento;
}