package es.cic.curso.ejerc007.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import es.cic.curso.ejerc007.model.Bicicleta;
import es.cic.curso.ejerc007.repository.BicicletaRepository;

@Service
public class BicicletaService {

    @Autowired
    BicicletaRepository bicicletaRepository;

    @PreAuthorize("hasRole('VENDEDOR')")
    public long crear(Bicicleta bicicleta) {
        bicicletaRepository.save(bicicleta);
        return bicicleta.getId();
    }

    @PreAuthorize("hasRole('TRABAJADOR')")
    public Optional<Bicicleta> leer(long id) {
        return bicicletaRepository.findById(id);
    }

}
