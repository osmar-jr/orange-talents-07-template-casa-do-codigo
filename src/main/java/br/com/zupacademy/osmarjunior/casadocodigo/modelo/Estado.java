package br.com.zupacademy.osmarjunior.casadocodigo.modelo;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @ManyToOne
    private Pais pais;

    @Deprecated
    public Estado() {
    }

    public Estado(@NotBlank String nome) {
        this.nome = nome;
    }

    public Estado(@NotBlank String nome, @NotNull @Valid Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Pais getPais() {
        return pais;
    }
}
