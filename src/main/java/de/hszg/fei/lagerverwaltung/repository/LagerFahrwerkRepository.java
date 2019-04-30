package de.hszg.fei.lagerverwaltung.repository;

import de.hszg.fei.lagerverwaltung.entity.LagerFahrwerk;
import de.hszg.fei.lagerverwaltung.entity.LagerRad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LagerFahrwerkRepository extends JpaRepository<LagerFahrwerk, Long> {
    List<LagerFahrwerk> findAllByEintreffenBefore(Long before);
}
