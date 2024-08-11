package es.cic.curso.ejerc007.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rueda")
public class Rueda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
