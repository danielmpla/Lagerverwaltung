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

import de.hszg.fei.lagerverwaltung.entity.Fahrwerk;
import de.hszg.fei.lagerverwaltung.repository.FahrwerkRepository;

@RestController
@RequestMapping("/fahrwerk")
public class FahrwerkRestController {
	private final FahrwerkRepository fahrwerkRepository;

	public FahrwerkRestController(FahrwerkRepository fahrwerkRepository) {
		this.fahrwerkRepository = fahrwerkRepository;
	}

	@RequestMapping(value = "", method=RequestMethod.GET)
	public ResponseEntity<List<Fahrwerk>> getAll() {
		return ResponseEntity.ok(fahrwerkRepository.findAll());
	}
	
	@RequestMapping(value = "", method=RequestMethod.POST)
	public ResponseEntity<String> createFahrwerk(@RequestBody Fahrwerk fahrwerk) {
		fahrwerkRepository.save(fahrwerk);
		
		return ResponseEntity.status(201).build();
	}

	@RequestMapping(value = "", method=RequestMethod.PUT)
	public ResponseEntity<Fahrwerk> updateFahrwerk(@RequestBody Fahrwerk fahrwerk) {
		if (fahrwerk == null || fahrwerk.getId() == null) {
			return ResponseEntity.status(404).build();
		}

		Optional<Fahrwerk> dbFahrwerkOptional = this.fahrwerkRepository.findById(fahrwerk.getId());

		if (!dbFahrwerkOptional.isPresent()) {
			return ResponseEntity.status(404).build();
		}

		Fahrwerk dbFahrwerk = dbFahrwerkOptional.get();

		dbFahrwerk.update(fahrwerk);

		return ResponseEntity.ok(dbFahrwerk);
	}

	@RequestMapping(value = "/name/{name}", method=RequestMethod.GET)
	public ResponseEntity<List<Fahrwerk>> getByName(@PathVariable String name) {
		List<Fahrwerk> fahrwerke = fahrwerkRepository.findAllByName(name);
		
		if (fahrwerke.size() == 0) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok(fahrwerke);
	}
	
	@RequestMapping(value = "/typ/{typ}", method=RequestMethod.GET)
	public ResponseEntity<List<Fahrwerk>> getByTyp(@PathVariable String typ) {
		List<Fahrwerk> fahrwerke = fahrwerkRepository.findAllByTyp(typ);
		
		if (fahrwerke.size() == 0) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok(fahrwerke);
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable Long id) {
		if (id == null || !fahrwerkRepository.findById(id).isPresent()) {
			return ResponseEntity.status(404).build();
		}
		
		fahrwerkRepository.deleteById(id);
		
		return ResponseEntity.status(204).build();
	}
}
