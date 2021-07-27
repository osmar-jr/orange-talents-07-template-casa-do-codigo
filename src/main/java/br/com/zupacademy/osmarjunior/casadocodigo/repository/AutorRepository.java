package br.com.zupacademy.osmarjunior.casadocodigo.repository;

import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
