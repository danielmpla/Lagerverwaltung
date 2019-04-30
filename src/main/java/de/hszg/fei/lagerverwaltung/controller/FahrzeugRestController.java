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

import de.hszg.fei.lagerverwaltung.entity.Fahrzeug;
import de.hszg.fei.lagerverwaltung.repository.FahrzeugRepository;

@RestController
@RequestMapping("/fahrzeug")
public class FahrzeugRestController {
	@Autowired
	private FahrzeugRepository fahrzeugRepository;
	
	@RequestMapping(value = "/all", method=RequestMethod.GET)
	public ResponseEntity<List<Fahrzeug>> getAllFahrzeuge() {
		return ResponseEntity.ok(fahrzeugRepository.findAll());
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Fahrzeug> getFahrzeugById(@PathVariable Long id) {
		Optional<Fahrzeug> fahrzeugOptional = fahrzeugRepository.findById(id);
		
		if (fahrzeugOptional.isPresent()) {
			return ResponseEntity.ok(fahrzeugOptional.get());
		} else {
			return ResponseEntity.status(404).build();
		}
	}
	
	@RequestMapping(value = "", method=RequestMethod.POST)
	public ResponseEntity<String> createFahrzeug(@RequestBody Fahrzeug fahrzeug) {
		fahrzeugRepository.save(fahrzeug);
		
		return ResponseEntity.status(201).build();
	}
	
	@RequestMapping(value = "/rad/{name}", method=RequestMethod.GET)
	public ResponseEntity<List<Fahrzeug>> getAllByRad(@PathVariable String name) {
		List<Fahrzeug> fahrzeuge = fahrzeugRepository.findAllByRadName(name);
		
		if (fahrzeuge.size() == 0) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok(fahrzeuge);
	}
	
	@RequestMapping(value = "/innenausstattung/{name}", method=RequestMethod.GET)
	public ResponseEntity<List<Fahrzeug>> getAllByInnenausstattung(@PathVariable String name) {
		List<Fahrzeug> fahrzeuge = fahrzeugRepository.findAllByInnenausstattungName(name);
		
		if (fahrzeuge.size() == 0) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok(fahrzeuge);
	}
	
	@RequestMapping(value = "/karosserie/{name}", method=RequestMethod.GET)
	public ResponseEntity<List<Fahrzeug>> getAllByKarosserie(@PathVariable String name) {
		List<Fahrzeug> fahrzeuge = fahrzeugRepository.findAllByKarosserieName(name);
		
		if (fahrzeuge.size() == 0) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok(fahrzeuge);
	}
	
	@RequestMapping(value = "/fahrwerk/{name}", method=RequestMethod.GET)
	public ResponseEntity<List<Fahrzeug>> getAllByFahrwerk(@PathVariable String name) {
		List<Fahrzeug> fahrzeuge = fahrzeugRepository.findAllByFahrwerkName(name);
		
		if (fahrzeuge.size() == 0) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok(fahrzeuge);
	}
	
	@RequestMapping(value = "", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@RequestBody Fahrzeug fahrzeug) {
		if (fahrzeug == null || fahrzeug.getId() == null) {
			return ResponseEntity.status(404).build();
		}
		
		fahrzeugRepository.deleteById(fahrzeug.getId());
		
		return ResponseEntity.status(200).build();
	}
}
