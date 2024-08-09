package es.cic.curso.ejerc007.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso.ejerc007.model.Bicicleta;
import es.cic.curso.ejerc007.service.BicicletaService;

@RestController
@RequestMapping("/api/bicicleta")
public class BicicletaController {

    @Autowired
    private BicicletaService bicicletaService;

    @PostMapping()
    public long crear(@RequestBody Bicicleta bicicleta) {
        return bicicletaService.crear(bicicleta);
    }

    @GetMapping("/{id}")
    public Optional<Bicicleta> leer(@PathVariable("id") long id) {
        return bicicletaService.leer(id);
    }

}
