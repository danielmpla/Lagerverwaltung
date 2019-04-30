package de.hszg.fei.lagerverwaltung.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.hszg.fei.lagerverwaltung.entity.Rad;

public interface RadRepository extends JpaRepository<Rad, Long> {
	List<Rad> findAllByName(String name);
	List<Rad> findAllByFarbe(String farbe);
}
