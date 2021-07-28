package br.com.zupacademy.osmarjunior.casadocodigo.repository;

import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNome(String nome);
}
