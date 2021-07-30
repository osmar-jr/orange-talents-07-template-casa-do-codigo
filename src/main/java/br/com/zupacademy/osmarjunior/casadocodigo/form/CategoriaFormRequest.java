package br.com.zupacademy.osmarjunior.casadocodigo.form;

import br.com.zupacademy.osmarjunior.casadocodigo.annotation.UniqueValue;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Categoria;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoriaFormRequest {
    @NotNull
    @NotEmpty
    @NotBlank
    @UniqueValue(classDomain = Categoria.class, attributeName = "nome")
    private String nome;

    @Deprecated
    public CategoriaFormRequest() {
    }

    public CategoriaFormRequest(@NotNull @NotBlank @NotEmpty String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Categoria converterToModel(){
        return new Categoria(this.nome);
    }
}
