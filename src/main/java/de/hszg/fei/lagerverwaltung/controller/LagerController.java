package de.hszg.fei.lagerverwaltung.controller;

import de.hszg.fei.lagerverwaltung.entity.*;
import de.hszg.fei.lagerverwaltung.repository.*;
import de.hszg.fei.lagerverwaltung.view.LagerAvailabilityView;
import de.hszg.fei.lagerverwaltung.view.LagerPostView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;

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
    public ResponseEntity<List<LagerAvailabilityView>> getAvailableKarosserien() {
        List<LagerAvailabilityView> lagerAvailabilityViews = new ArrayList<>();

        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
        List<LagerKarosserie> lagerKarosseries = this.lagerKarosserieRepository.findAllByEintreffenBefore(localDateTime.toEpochSecond(ZoneOffset.UTC));

        Set<Long> ids = new HashSet<>();
        lagerKarosseries.forEach(l -> ids.add(l.getKarosserie().getId()));

        for (Long id : ids) {
            int numberOfKaroserien = (int) lagerKarosseries.stream().filter(l -> l.getKarosserie().getId().equals(id)).count();
            LagerAvailabilityView lagerAvailabilityView = new LagerAvailabilityView(id, localDateTime.toEpochSecond(ZoneOffset.UTC), numberOfKaroserien);
            lagerAvailabilityViews.add(lagerAvailabilityView);
        }

        return ResponseEntity.ok(lagerAvailabilityViews);
    }

    @RequestMapping(value = "/karosserie/avail/{id}", method = RequestMethod.GET)
    public ResponseEntity<LagerAvailabilityView> getAvailableKarosserien(@PathVariable Long id) {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
        List<LagerKarosserie> lagerKarosseries = this.lagerKarosserieRepository.findAllByEintreffenBeforeAndAndId(localDateTime.toEpochSecond(ZoneOffset.UTC), id);

        LagerAvailabilityView lagerAvailabilityView = new LagerAvailabilityView(id, localDateTime.toEpochSecond(ZoneOffset.UTC), lagerKarosseries.size());

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
    public ResponseEntity<List<LagerAvailabilityView>> getAvailableRaeder() {
        List<LagerAvailabilityView> lagerAvailabilityViews = new ArrayList<>();

        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
        List<LagerRad> lagerRads = this.lagerRadRepository.findAllByEintreffenBefore(localDateTime.toEpochSecond(ZoneOffset.UTC));

        Set<Long> ids = new HashSet<>();
        lagerRads.forEach(l -> ids.add(l.getRad().getId()));

        for (Long id : ids) {
            int numberOfRaeder = (int) lagerRads.stream().filter(l -> l.getRad().getId().equals(id)).count();

            LagerAvailabilityView lagerAvailabilityView = new LagerAvailabilityView(id, localDateTime.toEpochSecond(ZoneOffset.UTC), numberOfRaeder);
            lagerAvailabilityViews.add(lagerAvailabilityView);
        }

        return ResponseEntity.ok(lagerAvailabilityViews);
    }

    @RequestMapping(value = "/rad/avail/{id}", method = RequestMethod.GET)
    public ResponseEntity<LagerAvailabilityView> getAvailableRaeder(@PathVariable Long id) {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
        List<LagerRad> lagerRads = this.lagerRadRepository.findAllByEintreffenBeforeAndId(localDateTime.toEpochSecond(ZoneOffset.UTC), id);

        LagerAvailabilityView lagerAvailabilityView = new LagerAvailabilityView(id, localDateTime.toEpochSecond(ZoneOffset.UTC), lagerRads.size());

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
    public ResponseEntity<List<LagerAvailabilityView>> getAvailableFahrwerke() {
        List<LagerAvailabilityView> lagerAvailabilityViews = new ArrayList<>();

        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
        List<LagerFahrwerk> lagerFahrwerke = this.lagerFahrwerkRepository.findAllByEintreffenBefore(localDateTime.toEpochSecond(ZoneOffset.UTC));

        Set<Long> ids = new HashSet<>();
        lagerFahrwerke.forEach(l -> ids.add(l.getFahrwerk().getId()));

        for (Long id : ids) {
            int numberOfFahrwerke = (int) lagerFahrwerke.stream().filter(l -> l.getFahrwerk().getId().equals(id)).count();
            LagerAvailabilityView lagerAvailabilityView = new LagerAvailabilityView(id, localDateTime.toEpochSecond(ZoneOffset.UTC), numberOfFahrwerke);
            lagerAvailabilityViews.add(lagerAvailabilityView);
        }

        return ResponseEntity.ok(lagerAvailabilityViews);
    }

    @RequestMapping(value = "/fahrwerk/avail/{id}", method = RequestMethod.GET)
    public ResponseEntity<LagerAvailabilityView> getAvailableFahrwerke(@PathVariable Long id) {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
        List<LagerFahrwerk> lagerFahrwerke = this.lagerFahrwerkRepository.findAllByEintreffenBeforeAndId(localDateTime.toEpochSecond(ZoneOffset.UTC), id);

        LagerAvailabilityView lagerAvailabilityView = new LagerAvailabilityView(id, localDateTime.toEpochSecond(ZoneOffset.UTC), lagerFahrwerke.size());

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
    public ResponseEntity<List<LagerAvailabilityView>> getAvailableInnenausstattungen() {
        List<LagerAvailabilityView> lagerAvailabilityViews = new ArrayList<>();

        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
        List<LagerInnenausstattung> lagerInnenausstattungen = this.lagerInnenausstattungRepository.findAllByEintreffenBefore(localDateTime.toEpochSecond(ZoneOffset.UTC));

        Set<Long> ids = new HashSet<>();
        lagerInnenausstattungen.forEach(l -> ids.add(l.getInnenausstattung().getId()));

        for (Long id : ids) {
            int numberOfInnenausstattungen = (int) lagerInnenausstattungen.stream().filter(l -> l.getInnenausstattung().getId().equals(id)).count();
            LagerAvailabilityView lagerAvailabilityView = new LagerAvailabilityView(id, localDateTime.toEpochSecond(ZoneOffset.UTC), numberOfInnenausstattungen);
            lagerAvailabilityViews.add(lagerAvailabilityView);
        }


        return ResponseEntity.ok(lagerAvailabilityViews);
    }

    @RequestMapping(value = "/innenausstattung/avail/{id}", method = RequestMethod.GET)
    public ResponseEntity<LagerAvailabilityView> getAvailableInnenausstattungen(@PathVariable Long id) {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
        List<LagerInnenausstattung> lagerInnenausstattungen = this.lagerInnenausstattungRepository.findAllByEintreffenBeforeAndId(localDateTime.toEpochSecond(ZoneOffset.UTC), id);

        LagerAvailabilityView lagerAvailabilityView = new LagerAvailabilityView(id, localDateTime.toEpochSecond(ZoneOffset.UTC), lagerInnenausstattungen.size());

        return ResponseEntity.ok(lagerAvailabilityView);
    }
}
