package com.ahmed.backend.controller;

import com.ahmed.backend.model.Supervisor;
import com.ahmed.backend.service.SupervisorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supervisors")
public class SupervisorController {

    private final SupervisorService supervisorService;

    public SupervisorController(SupervisorService supervisorService) {
        this.supervisorService = supervisorService;
    }

    @GetMapping
    public List<Supervisor> getAllSupervisors() {
        return supervisorService.getAllSupervisors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supervisor> getSupervisorById(@PathVariable Long id) {
        return supervisorService.getSupervisorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Supervisor createSupervisor(@RequestBody Supervisor supervisor) {
        return supervisorService.createSupervisor(supervisor);
    }

    @PutMapping("/{id}")
    public Supervisor updateSupervisor(@PathVariable Long id, @RequestBody Supervisor supervisor) {
        return supervisorService.updateSupervisor(id, supervisor);
    }

    @DeleteMapping("/{id}")
    public void deleteSupervisor(@PathVariable Long id) {
        supervisorService.deleteSupervisor(id);
    }
}

