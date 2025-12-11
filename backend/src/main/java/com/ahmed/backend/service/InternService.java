package com.ahmed.backend.service;

import com.ahmed.backend.model.Intern;
import com.ahmed.backend.model.Supervisor;
import com.ahmed.backend.repository.InternRepository;
import com.ahmed.backend.repository.SupervisorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternService {

    private final InternRepository internRepository;
    private final SupervisorRepository supervisorRepository;

    public InternService(InternRepository internRepository, SupervisorRepository supervisorRepository) {
        this.internRepository = internRepository;
        this.supervisorRepository = supervisorRepository;
    }

    public List<Intern> getAllInterns() {
        return internRepository.findAll();
    }

    public Optional<Intern> getInternById(Long id) {
        return internRepository.findById(id);
    }

    public List<Intern> getInternsBySupervisorId(Long supervisorId) {
        return internRepository.findBySupervisorId(supervisorId);
    }

    public Intern createIntern(Intern intern, Long supervisorId) {
        Supervisor supervisor = supervisorRepository.findById(supervisorId)
                .orElseThrow(() -> new IllegalArgumentException("Supervisor not found"));
        intern.setSupervisor(supervisor);
        return internRepository.save(intern);
    }

    public Intern updateIntern(Long id, Intern intern, Long supervisorId) {
        Intern existingIntern = internRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Intern not found"));

        existingIntern.setName(intern.getName());
        existingIntern.setEmail(intern.getEmail());
        existingIntern.setDepartment(intern.getDepartment());

        if (supervisorId != null) {
            Supervisor supervisor = supervisorRepository.findById(supervisorId)
                    .orElseThrow(() -> new IllegalArgumentException("Supervisor not found"));
            existingIntern.setSupervisor(supervisor);
        }

        return internRepository.save(existingIntern);
    }

    public void deleteIntern(Long id) {
        internRepository.deleteById(id);
    }
}

