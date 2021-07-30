package br.com.zupacademy.osmarjunior.casadocodigo.form;

import br.com.zupacademy.osmarjunior.casadocodigo.annotation.UniqueValue;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Pais;

import javax.validation.constraints.NotBlank;

public class PaisFormRequest {

    @NotBlank
    @UniqueValue(classDomain = Pais.class, attributeName = "nome")
    private String nome;

    @Deprecated
    public PaisFormRequest() {
    }

    public PaisFormRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    /*
    *  Jackson só consegui injetar valores via set,
    * ainda não identifiquei o motivo do construtor não ser reconhecido
    * */
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pais converterToModel() {
        return new Pais(this.nome);
    }
}
