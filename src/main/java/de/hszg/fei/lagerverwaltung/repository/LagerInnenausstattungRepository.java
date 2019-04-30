package de.hszg.fei.lagerverwaltung.repository;

import de.hszg.fei.lagerverwaltung.entity.LagerInnenausstattung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LagerInnenausstattungRepository extends JpaRepository<LagerInnenausstattung, Long> {
    List<LagerInnenausstattung> findAllByEintreffenBefore(Long before);
}
