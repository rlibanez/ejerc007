package es.cic.curso.ejerc007.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.cic.curso.ejerc007.model.Bicicleta;

public interface BicicletaRepository extends JpaRepository<Bicicleta, Long> {

}
