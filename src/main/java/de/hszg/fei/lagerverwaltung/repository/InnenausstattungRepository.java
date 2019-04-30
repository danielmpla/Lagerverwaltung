package de.hszg.fei.lagerverwaltung.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.hszg.fei.lagerverwaltung.entity.Innenausstattung;

public interface InnenausstattungRepository extends JpaRepository<Innenausstattung, Long> {
	List<Innenausstattung> findAllByName(String name);
	List<Innenausstattung> findAllByTyp(String typ);
}
