package com.ahmed.backend.service;

import com.ahmed.backend.model.Supervisor;
import com.ahmed.backend.repository.SupervisorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupervisorService {

    private final SupervisorRepository supervisorRepository;

    public SupervisorService(SupervisorRepository supervisorRepository) {
        this.supervisorRepository = supervisorRepository;
    }

    public List<Supervisor> getAllSupervisors() {
        return supervisorRepository.findAll();
    }

    public Optional<Supervisor> getSupervisorById(Long id) {
        return supervisorRepository.findById(id);
    }

    public Supervisor createSupervisor(Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }

    public Supervisor updateSupervisor(Long id, Supervisor supervisor) {
        Supervisor existingSupervisor = supervisorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Supervisor not found"));

        existingSupervisor.setName(supervisor.getName());
        existingSupervisor.setEmail(supervisor.getEmail());
        existingSupervisor.setDepartment(supervisor.getDepartment());

        return supervisorRepository.save(existingSupervisor);
    }

    public void deleteSupervisor(Long id) {
        supervisorRepository.deleteById(id);
    }
}

