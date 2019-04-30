package de.hszg.fei.lagerverwaltung.repository;

import de.hszg.fei.lagerverwaltung.entity.LagerKarosserie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LagerKarosserieRepository extends JpaRepository<LagerKarosserie, Long> {
    List<LagerKarosserie> findAllByEintreffenBefore(Long before);
}
