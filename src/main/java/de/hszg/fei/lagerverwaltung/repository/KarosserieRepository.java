package de.hszg.fei.lagerverwaltung.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.hszg.fei.lagerverwaltung.entity.Karosserie;

public interface KarosserieRepository extends JpaRepository<Karosserie, Long> {
	List<Karosserie> findAllByName(String name);
	List<Karosserie> findAllByTyp(String typ);
}
