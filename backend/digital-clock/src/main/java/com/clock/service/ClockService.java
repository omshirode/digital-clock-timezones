package com.clock.service;

import com.clock.dto.ClockDTO;
import com.clock.entity.FavoriteTimeZone;
import com.clock.repository.FavoriteTimeZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClockService {

    @Autowired
    private FavoriteTimeZoneRepository favoriteTimeZoneRepository;

    public List<ClockDTO> getAllTimeZones() {
        return ZoneId.getAvailableZoneIds().stream()
                .sorted()
                .map(this::createClockDTO)
                .collect(Collectors.toList());
    }

    public ClockDTO getTimeForTimeZone(String timezone) {
        return createClockDTO(timezone);
    }

    public List<ClockDTO> getFavoriteTimeZones() {
        return favoriteTimeZoneRepository.findAll().stream()
                .map(ftz -> createClockDTO(ftz.getTimezone()))
                .collect(Collectors.toList());
    }

    public void saveFavoriteTimeZone(String timezone) {
        if (!favoriteTimeZoneRepository.existsByTimezone(timezone)) {
            FavoriteTimeZone ftz = new FavoriteTimeZone();
            ftz.setTimezone(timezone);
            favoriteTimeZoneRepository.save(ftz);
        }
    }

    public void removeFavoriteTimeZone(String timezone) {
        favoriteTimeZoneRepository.deleteByTimezone(timezone);
    }

    private ClockDTO createClockDTO(String timezone) {
        try {
            ZoneId zoneId = ZoneId.of(timezone);
            ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");

            String time = zonedDateTime.format(timeFormatter);
            String date = zonedDateTime.format(dateFormatter);
            String offset = String.format("UTC%s", zonedDateTime.getOffset());

            return new ClockDTO(timezone, time, date, offset);
        } catch (Exception e) {
            return null;
        }
    }
}