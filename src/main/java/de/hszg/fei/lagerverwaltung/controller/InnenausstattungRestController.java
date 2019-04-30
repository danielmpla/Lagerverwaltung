package de.hszg.fei.lagerverwaltung.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.hszg.fei.lagerverwaltung.entity.Innenausstattung;
import de.hszg.fei.lagerverwaltung.repository.InnenausstattungRepository;

@RestController
@RequestMapping("/innenausstattung")
public class InnenausstattungRestController {
	private final InnenausstattungRepository innenausstattungRepository;

	public InnenausstattungRestController(InnenausstattungRepository innenausstattungRepository) {
		this.innenausstattungRepository = innenausstattungRepository;
	}

	@RequestMapping(value = "", method=RequestMethod.GET)
	public ResponseEntity<List<Innenausstattung>> getAll() {
		return ResponseEntity.ok(innenausstattungRepository.findAll());
	}
	
	@RequestMapping(value = "", method=RequestMethod.POST)
	public ResponseEntity<String> createInnenausstattung(@RequestBody Innenausstattung innenausstattung) {
		innenausstattungRepository.save(innenausstattung);
		
		return ResponseEntity.status(201).build();
	}

	@RequestMapping(value = "", method=RequestMethod.PUT)
	public ResponseEntity<Innenausstattung> updateInnenausstattung(@RequestBody Innenausstattung innenausstattung) {
		if (innenausstattung == null || innenausstattung.getId() == null) {
			return ResponseEntity.status(404).build();
		}

		Optional<Innenausstattung> dbInnenausstattungOptional = this.innenausstattungRepository.findById(innenausstattung.getId());

		if (!dbInnenausstattungOptional.isPresent()) {
			return ResponseEntity.status(404).build();
		}

		Innenausstattung dbInnenausstattung = dbInnenausstattungOptional.get();

		dbInnenausstattung.update(innenausstattung);

		return ResponseEntity.ok(this.innenausstattungRepository.save(dbInnenausstattung));
	}
	
	@RequestMapping(value = "/name/{name}", method=RequestMethod.GET)
	public ResponseEntity<List<Innenausstattung>> getByName(@PathVariable String name) {
		List<Innenausstattung> innenausstattungen = innenausstattungRepository.findAllByName(name);
		
		if (innenausstattungen.size() == 0) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok(innenausstattungen);
	}
	
	@RequestMapping(value = "/typ/{typ}", method=RequestMethod.GET)
	public ResponseEntity<List<Innenausstattung>> getByTyp(@PathVariable String typ) {
		List<Innenausstattung> innenausstattungen = innenausstattungRepository.findAllByTyp(typ);
		
		if (innenausstattungen.size() == 0) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok(innenausstattungen);
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable Long id) {
		if (id == null || !innenausstattungRepository.findById(id).isPresent()) {
			return ResponseEntity.status(404).build();
		}
		
		innenausstattungRepository.deleteById(id);
		
		return ResponseEntity.status(204).build();
	}
}
