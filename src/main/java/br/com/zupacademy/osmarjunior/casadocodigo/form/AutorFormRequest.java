package br.com.zupacademy.osmarjunior.casadocodigo.form;

import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Autor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Validated
public class AutorFormRequest {

    @NotNull @NotEmpty @NotBlank
    private String nome;

    @NotNull @NotEmpty @NotBlank @Email
    private String email;

    @NotNull @NotEmpty @NotBlank
    @Length(max = 400)
    private String descricao;

    public Autor converterToModel() {
        return new Autor(nome, email, descricao);
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDescricao() {
        return descricao;
    }
}
