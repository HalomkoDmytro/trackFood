package org.hdo.trackFood.repository;

import org.hdo.trackFood.model.DayConsume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayConsumeRepository extends JpaRepository<DayConsume, Long> {
}
