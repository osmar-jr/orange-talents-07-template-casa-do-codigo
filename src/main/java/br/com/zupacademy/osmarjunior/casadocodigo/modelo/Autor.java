package br.com.zupacademy.osmarjunior.casadocodigo.modelo;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Autor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotEmpty @NotBlank
    private String nome;

    @NotNull @NotEmpty @NotBlank @Email
    @Column(unique = true)
    private String email;

    @NotNull @NotEmpty @NotBlank @Length(max = 400)
    private String descricao;

    @NotNull
    private LocalDateTime criadoEm;

    @Deprecated
    public Autor() {
    }

    public Autor(@NotBlank String nome,
                 @NotBlank @Email String email,
                 @NotBlank @Length(max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.criadoEm = LocalDateTime.now();
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
}
