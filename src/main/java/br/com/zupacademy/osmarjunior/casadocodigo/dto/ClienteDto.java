package br.com.zupacademy.osmarjunior.casadocodigo.dto;

import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Cliente;

public class ClienteDto {

    private Long id;
    private String nome;

    public ClienteDto(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static ClienteDto toClienteDto(Cliente cliente) {
        return new ClienteDto(cliente.getId(), cliente.getNome());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
