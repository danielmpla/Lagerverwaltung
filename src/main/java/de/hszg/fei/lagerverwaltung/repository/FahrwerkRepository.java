package de.hszg.fei.lagerverwaltung.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.hszg.fei.lagerverwaltung.entity.Fahrwerk;

public interface FahrwerkRepository extends JpaRepository<Fahrwerk, Long> {
	List<Fahrwerk> findAllByName(String name);
	List<Fahrwerk> findAllByTyp(String typ);
}
