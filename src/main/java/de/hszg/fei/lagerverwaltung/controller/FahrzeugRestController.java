package de.hszg.fei.lagerverwaltung.controller;

import de.hszg.fei.lagerverwaltung.entity.*;
import de.hszg.fei.lagerverwaltung.repository.*;
import de.hszg.fei.lagerverwaltung.view.FahrzeugView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fahrzeug")
public class FahrzeugRestController {
    private final FahrzeugRepository fahrzeugRepository;
    private final RadRepository radRepository;
    private final KarosserieRepository karosserieRepository;
    private final InnenausstattungRepository innenausstattungRepository;
    private final FahrwerkRepository fahrwerkRepository;

    public FahrzeugRestController(FahrzeugRepository fahrzeugRepository, RadRepository radRepository, KarosserieRepository karosserieRepository, InnenausstattungRepository innenausstattungRepository, FahrwerkRepository fahrwerkRepository) {
        this.fahrzeugRepository = fahrzeugRepository;
        this.radRepository = radRepository;
        this.karosserieRepository = karosserieRepository;
        this.innenausstattungRepository = innenausstattungRepository;
        this.fahrwerkRepository = fahrwerkRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Fahrzeug>> getAllFahrzeuge() {
        return ResponseEntity.ok(fahrzeugRepository.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Fahrzeug> getFahrzeugById(@PathVariable Long id) {
        Optional<Fahrzeug> fahrzeugOptional = fahrzeugRepository.findById(id);

        if (fahrzeugOptional.isPresent()) {
            return ResponseEntity.ok(fahrzeugOptional.get());
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> createFahrzeug(@RequestBody FahrzeugView fahrzeugView) {
        Optional<Rad> rad = radRepository.findById(fahrzeugView.getRadId());
        Optional<Fahrwerk> fahrwerk = fahrwerkRepository.findById(fahrzeugView.getFahrwerkId());
        Optional<Karosserie> karosserie = karosserieRepository.findById(fahrzeugView.getKarosserieId());
        Optional<Innenausstattung> innenausstattung = innenausstattungRepository.findById(fahrzeugView.getInnenausstattungId());

        if (rad.isPresent() && fahrwerk.isPresent() && karosserie.isPresent() && innenausstattung.isPresent()) {
            Fahrzeug fahrzeug = new Fahrzeug(fahrzeugView.getName(), karosserie.get(), fahrwerk.get(), innenausstattung.get(), rad.get(), fahrzeugView.getAnzahlRaeder(), fahrzeugView.getMenge(), fahrzeugView.getProduktionsDatum());

            fahrzeugRepository.save(fahrzeug);

            return ResponseEntity.status(201).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @RequestMapping(value = "/rad/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Fahrzeug>> getAllByRad(@PathVariable String name) {
        List<Fahrzeug> fahrzeuge = fahrzeugRepository.findAllByRadName(name);

        if (fahrzeuge.size() == 0) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(fahrzeuge);
    }

    @RequestMapping(value = "/innenausstattung/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Fahrzeug>> getAllByInnenausstattung(@PathVariable String name) {
        List<Fahrzeug> fahrzeuge = fahrzeugRepository.findAllByInnenausstattungName(name);

        if (fahrzeuge.size() == 0) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(fahrzeuge);
    }

    @RequestMapping(value = "/karosserie/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Fahrzeug>> getAllByKarosserie(@PathVariable String name) {
        List<Fahrzeug> fahrzeuge = fahrzeugRepository.findAllByKarosserieName(name);

        if (fahrzeuge.size() == 0) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(fahrzeuge);
    }

    @RequestMapping(value = "/fahrwerk/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Fahrzeug>> getAllByFahrwerk(@PathVariable String name) {
        List<Fahrzeug> fahrzeuge = fahrzeugRepository.findAllByFahrwerkName(name);

        if (fahrzeuge.size() == 0) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(fahrzeuge);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> remove(@PathVariable Long id) {
        if (id == null || !fahrzeugRepository.findById(id).isPresent()) {
            return ResponseEntity.status(404).build();
        }
        try {
            fahrzeugRepository.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(200).build();
    }
}
