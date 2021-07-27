package br.com.zupacademy.osmarjunior.casadocodigo.dto;

import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Autor;

import java.util.List;
import java.util.stream.Collectors;

public class AutorDto {

    private Long id;
    private String nome;
    private String descricao;

    public AutorDto(Autor autor) {
        this.id = autor.getId();
        this.nome = autor.getNome();
        this.descricao = autor.getDescricao();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public static List<AutorDto> converter(List<Autor> autores) {
        return autores.stream().map(AutorDto::new).collect(Collectors.toList());
    }
}
