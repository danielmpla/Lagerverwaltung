package de.hszg.fei.lagerverwaltung.controller;

import de.hszg.fei.lagerverwaltung.entity.Karosserie;
import de.hszg.fei.lagerverwaltung.repository.KarosserieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/karosserie")
public class KarosserieRestController {
    private final KarosserieRepository karosserieRepository;

    public KarosserieRestController(KarosserieRepository karosserieRepository) {
        this.karosserieRepository = karosserieRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Karosserie>> getAll() {
        return ResponseEntity.ok(karosserieRepository.findAll());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> createKarosserie(@RequestBody Karosserie karosserie) {
        karosserieRepository.save(karosserie);

        return ResponseEntity.status(201).build();
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<Karosserie> updateKarosserie(@RequestBody Karosserie karosserie) {
        if (karosserie == null || karosserie.getId() == null) {
            return ResponseEntity.status(404).build();
        }

        Optional<Karosserie> dbKarosserieOptional = karosserieRepository.findById(karosserie.getId());

        if (!dbKarosserieOptional.isPresent()) {
            return ResponseEntity.status(404).build();
        }

        Karosserie dbKarosserie = dbKarosserieOptional.get();

        dbKarosserie.update(karosserie);

        return ResponseEntity.ok(karosserieRepository.save(dbKarosserie));
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Karosserie>> getByName(String name) {
        List<Karosserie> karosserien = karosserieRepository.findAllByName(name);

        if (karosserien.size() == 0) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(karosserien);
    }

    @RequestMapping(value = "/typ/{typ}", method = RequestMethod.GET)
    public ResponseEntity<List<Karosserie>> getByTyp(String typ) {
        List<Karosserie> karosserien = karosserieRepository.findAllByTyp(typ);

        if (karosserien.size() == 0) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(karosserien);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> remove(@PathVariable Long id) {
        if (id == null || !karosserieRepository.findById(id).isPresent()) {
            return ResponseEntity.status(404).build();
        }

        try {
            karosserieRepository.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(204).build();
    }
}
