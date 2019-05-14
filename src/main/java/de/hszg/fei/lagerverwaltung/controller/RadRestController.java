package de.hszg.fei.lagerverwaltung.controller;

import de.hszg.fei.lagerverwaltung.entity.Rad;
import de.hszg.fei.lagerverwaltung.repository.RadRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rad")
public class RadRestController {
	private final RadRepository radRepository;

	public RadRestController(RadRepository radRepository) {
		this.radRepository = radRepository;
	}

	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<Rad>> getAll() {
		return ResponseEntity.ok(radRepository.findAll());
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> createRad(@RequestBody Rad rad) {
		radRepository.save(rad);
		return ResponseEntity.status(201).build();
	}

	@RequestMapping(value = "", method=RequestMethod.PUT)
	public ResponseEntity<Rad> updateRad(@RequestBody Rad rad) {
		if (rad == null || rad.getId() == null) {
			return ResponseEntity.status(404).build();
		}

		Optional<Rad> dbRadOptional = this.radRepository.findById(rad.getId());

		if (!dbRadOptional.isPresent()) {
			return ResponseEntity.status(404).build();
		}

		Rad dbRad = dbRadOptional.get();

		dbRad.update(rad);

		return ResponseEntity.ok(radRepository.save(dbRad));
	}

	@RequestMapping(value="/name/{name}", method=RequestMethod.GET)
	public ResponseEntity<List<Rad>> getByName(@PathVariable String name) {
		List<Rad> raeder = radRepository.findAllByName(name);
		
		if (raeder.size() == 0) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok(raeder);
	}
	
	@RequestMapping(value = "/farbe/{farbe}", method=RequestMethod.GET)
	public ResponseEntity<List<Rad>> getByFarbe(@PathVariable String farbe) {
		List<Rad> raeder = radRepository.findAllByFarbe(farbe);
		
		if (raeder.size() == 0) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok(raeder);
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable Long id) {
		if (id == null || !radRepository.findById(id).isPresent()) {
			return ResponseEntity.status(404).build();
		}

		try {
			radRepository.deleteById(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
		return ResponseEntity.status(204).build();
	}
}
