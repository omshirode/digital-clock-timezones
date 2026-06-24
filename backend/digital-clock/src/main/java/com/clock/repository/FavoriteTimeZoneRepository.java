package com.clock.repository;

import com.clock.entity.FavoriteTimeZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteTimeZoneRepository extends JpaRepository<FavoriteTimeZone, Long> {
    boolean existsByTimezone(String timezone);
    void deleteByTimezone(String timezone);
}