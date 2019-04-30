package de.hszg.fei.lagerverwaltung.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.hszg.fei.lagerverwaltung.entity.Fahrzeug;

public interface FahrzeugRepository extends JpaRepository<Fahrzeug, Long> {
	List<Fahrzeug> findAllByRadName(String name);
	List<Fahrzeug> findAllByInnenausstattungName(String name);
	List<Fahrzeug> findAllByKarosserieName(String name);
	List<Fahrzeug> findAllByFahrwerkName(String name);
}
