package de.hszg.fei.lagerverwaltung.controller;

import de.hszg.fei.lagerverwaltung.entity.*;
import de.hszg.fei.lagerverwaltung.repository.*;
import de.hszg.fei.lagerverwaltung.view.LagerAvailabilityView;
import de.hszg.fei.lagerverwaltung.view.LagerPostView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/lager")
public class LagerController {
    private final FahrwerkRepository fahrwerkRepository;
    private final RadRepository radRepository;
    private final InnenausstattungRepository innenausstattungRepository;
    private final KarosserieRepository karosserieRepository;

    private final LagerRadRepository lagerRadRepository;
    private final LagerKarosserieRepository lagerKarosserieRepository;
    private final LagerInnenausstattungRepository lagerInnenausstattungRepository;
    private final LagerFahrwerkRepository lagerFahrwerkRepository;

    public LagerController(FahrwerkRepository fahrwerkRepository, RadRepository radRepository, InnenausstattungRepository innenausstattungRepository, KarosserieRepository karosserieRepository, LagerRadRepository lagerRadRepository, LagerKarosserieRepository lagerKarosserieRepository, LagerInnenausstattungRepository lagerInnenausstattungRepository, LagerFahrwerkRepository lagerFahrwerkRepository) {
        this.fahrwerkRepository = fahrwerkRepository;
        this.radRepository = radRepository;
        this.innenausstattungRepository = innenausstattungRepository;
        this.karosserieRepository = karosserieRepository;
        this.lagerRadRepository = lagerRadRepository;
        this.lagerKarosserieRepository = lagerKarosserieRepository;
        this.lagerInnenausstattungRepository = lagerInnenausstattungRepository;
        this.lagerFahrwerkRepository = lagerFahrwerkRepository;
    }

    @RequestMapping(value = "/karosserie", method = RequestMethod.POST)
    public ResponseEntity<String> storeLagerKarosserie(@RequestBody LagerPostView lagerPostView) {
        Optional<Karosserie> karosserieOptional = this.karosserieRepository.findById(lagerPostView.getId());

        if (!karosserieOptional.isPresent()) {
            return ResponseEntity.status(404).build();
        }

        if (lagerPostView.getMenge() < 1) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

        for (int i = 0; i < lagerPostView.getMenge(); i++) {
            LagerKarosserie lagerKarosserie = new LagerKarosserie(null, lagerPostView.getEintreffen(), karosserieOptional.get());
            lagerKarosserieRepository.save(lagerKarosserie);
        }

        return ResponseEntity.status(201).build();
    }

    @RequestMapping(value = "/karosserie", method = RequestMethod.GET)
    public ResponseEntity<List<LagerKarosserie>> getLagerKarosserien() {
        return ResponseEntity.ok(this.lagerKarosserieRepository.findAll());
    }

    @RequestMapping(value = "/karosserie/avail", method = RequestMethod.GET)
    public ResponseEntity<LagerAvailabilityView> getAvailableKarosserien() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
        List<LagerKarosserie> lagerKarosseries = this.lagerKarosserieRepository.findAllByEintreffenBefore(localDateTime.toEpochSecond(ZoneOffset.UTC));

        LagerAvailabilityView lagerAvailabilityView = new LagerAvailabilityView(localDateTime.toEpochSecond(ZoneOffset.UTC), lagerKarosseries.size());

        return ResponseEntity.ok(lagerAvailabilityView);
    }


    @RequestMapping(value = "/rad", method = RequestMethod.POST)
    public ResponseEntity<String> storeLagerRad(@RequestBody LagerPostView lagerPostView) {
        Optional<Rad> radOptional = this.radRepository.findById(lagerPostView.getId());

        if (!radOptional.isPresent()) {
            return ResponseEntity.status(404).build();
        }

        if (lagerPostView.getMenge() < 1) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

        for (int i = 0; i < lagerPostView.getMenge(); i++) {
            LagerRad lagerRad = new LagerRad(null, lagerPostView.getEintreffen(), radOptional.get());
            this.lagerRadRepository.save(lagerRad);
        }

        return ResponseEntity.status(201).build();
    }

    @RequestMapping(value = "/rad", method = RequestMethod.GET)
    public ResponseEntity<List<LagerRad>> getLagerRaeder() {
        return ResponseEntity.ok(this.lagerRadRepository.findAll());
    }

    @RequestMapping(value = "/rad/avail", method = RequestMethod.GET)
    public ResponseEntity<LagerAvailabilityView> getAvailableRaeder() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
        List<LagerRad> lagerRads = this.lagerRadRepository.findAllByEintreffenBefore(localDateTime.toEpochSecond(ZoneOffset.UTC));

        LagerAvailabilityView lagerAvailabilityView = new LagerAvailabilityView(localDateTime.toEpochSecond(ZoneOffset.UTC), lagerRads.size());

        return ResponseEntity.ok(lagerAvailabilityView);
    }


    @RequestMapping(value = "/fahrwerk", method = RequestMethod.POST)
    public ResponseEntity<String> storeLagerFahrwerk(@RequestBody LagerPostView lagerPostView) {
        Optional<Fahrwerk> fahrwerkOptional = this.fahrwerkRepository.findById(lagerPostView.getId());

        if (!fahrwerkOptional.isPresent()) {
            return ResponseEntity.status(404).build();
        }

        if (lagerPostView.getMenge() < 1) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

        for (int i = 0; i < lagerPostView.getMenge(); i++) {
            LagerFahrwerk lagerFahrwerk = new LagerFahrwerk(null, lagerPostView.getEintreffen(), fahrwerkOptional.get());
            this.lagerFahrwerkRepository.save(lagerFahrwerk);
        }

        return ResponseEntity.status(201).build();
    }

    @RequestMapping(value = "/fahrwerk", method = RequestMethod.GET)
    public ResponseEntity<List<LagerFahrwerk>> getLagerFahrwerke() {
        return ResponseEntity.ok(this.lagerFahrwerkRepository.findAll());
    }

    @RequestMapping(value = "/fahrwerk/avail", method = RequestMethod.GET)
    public ResponseEntity<LagerAvailabilityView> getAvailableFahrwerke() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
        List<LagerFahrwerk> lagerFahrwerke = this.lagerFahrwerkRepository.findAllByEintreffenBefore(localDateTime.toEpochSecond(ZoneOffset.UTC));

        LagerAvailabilityView lagerAvailabilityView = new LagerAvailabilityView(localDateTime.toEpochSecond(ZoneOffset.UTC), lagerFahrwerke.size());

        return ResponseEntity.ok(lagerAvailabilityView);
    }

    @RequestMapping(value = "/innenausstattung", method = RequestMethod.POST)
    public ResponseEntity<String> storeLagerInnenausstatung(@RequestBody LagerPostView lagerPostView) {
        Optional<Innenausstattung> innenausstattungOptional = this.innenausstattungRepository.findById(lagerPostView.getId());

        if (!innenausstattungOptional.isPresent()) {
            return ResponseEntity.status(404).build();
        }

        if (lagerPostView.getMenge() < 1) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

        for (int i = 0; i < lagerPostView.getMenge(); i++) {
            LagerInnenausstattung lagerInnenausstattung = new LagerInnenausstattung(null, lagerPostView.getEintreffen(), innenausstattungOptional.get());
            this.lagerInnenausstattungRepository.save(lagerInnenausstattung);
        }

        return ResponseEntity.status(201).build();
    }

    @RequestMapping(value = "/innenausstattung", method = RequestMethod.GET)
    public ResponseEntity<List<LagerInnenausstattung>> getLagerInnenausstattungen() {
        return ResponseEntity.ok(this.lagerInnenausstattungRepository.findAll());
    }

    @RequestMapping(value = "/innenausstattung/avail", method = RequestMethod.GET)
    public ResponseEntity<LagerAvailabilityView> getAvailableInnenausstattungen() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
        List<LagerInnenausstattung> lagerInnenausstattungen = this.lagerInnenausstattungRepository.findAllByEintreffenBefore(localDateTime.toEpochSecond(ZoneOffset.UTC));

        LagerAvailabilityView lagerAvailabilityView = new LagerAvailabilityView(localDateTime.toEpochSecond(ZoneOffset.UTC), lagerInnenausstattungen.size());

        return ResponseEntity.ok(lagerAvailabilityView);
    }
}
