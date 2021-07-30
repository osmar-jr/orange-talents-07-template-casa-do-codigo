package br.com.zupacademy.osmarjunior.casadocodigo.dto;

import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Estado;

public class EstadoDto {

    private Long id;
    private String nome;
    private PaisDto pais;

    public EstadoDto(Long id, String nome, PaisDto pais) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
    }

    public EstadoDto(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static EstadoDto converterToDto(Estado estado) {
        if (estado.getPais() == null){
            return new EstadoDto(estado.getId(), estado.getNome());
        }

        PaisDto paisDto = new PaisDto(estado.getPais());
        return new EstadoDto(estado.getId(), estado.getNome(), paisDto);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public PaisDto getPais() {
        return pais;
    }
}
