package de.hszg.fei.lagerverwaltung.repository;

import de.hszg.fei.lagerverwaltung.entity.LagerRad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LagerRadRepository extends JpaRepository<LagerRad, Long> {
    List<LagerRad> findAllByEintreffenBefore(Long before);
}
