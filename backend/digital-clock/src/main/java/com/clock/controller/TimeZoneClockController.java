package com.clock.controller;

import com.clock.dto.ClockDTO;
import com.clock.service.ClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clock")
@CrossOrigin(origins = "*")
public class TimeZoneClockController {

    @Autowired
    private ClockService clockService;

    @GetMapping("/timezones")
    public ResponseEntity<List<ClockDTO>> getAllTimeZones() {
        return ResponseEntity.ok(clockService.getAllTimeZones());
    }

    @GetMapping("/current-time/{timezone}")
    public ResponseEntity<ClockDTO> getCurrentTime(@PathVariable String timezone) {
        return ResponseEntity.ok(clockService.getTimeForTimeZone(timezone));
    }

    @PostMapping("/save-favorite")
    public ResponseEntity<String> saveFavoriteTimeZone(@RequestBody Map<String, String> request) {
        clockService.saveFavoriteTimeZone(request.get("timezone"));
        return ResponseEntity.ok("Timezone saved successfully");
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<ClockDTO>> getFavoriteTimeZones() {
        return ResponseEntity.ok(clockService.getFavoriteTimeZones());
    }

    @DeleteMapping("/remove-favorite/{timezone}")
    public ResponseEntity<String> removeFavoriteTimeZone(@PathVariable String timezone) {
        clockService.removeFavoriteTimeZone(timezone);
        return ResponseEntity.ok("Timezone removed successfully");
    }
}