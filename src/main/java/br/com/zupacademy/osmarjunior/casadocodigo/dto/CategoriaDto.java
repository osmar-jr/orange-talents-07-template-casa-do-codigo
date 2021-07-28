package br.com.zupacademy.osmarjunior.casadocodigo.dto;

import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Categoria;

public class CategoriaDto {

    private String nome;

    public CategoriaDto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
