package com.ahmed.backend.controller;

import com.ahmed.backend.model.Intern;
import com.ahmed.backend.payload.request.InternRequest;
import com.ahmed.backend.service.InternService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interns")
public class InternController {

    private final InternService internService;

    public InternController(InternService internService) {
        this.internService = internService;
    }

    @GetMapping
    public List<Intern> getAllInterns() {
        return internService.getAllInterns();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Intern> getInternById(@PathVariable Long id) {
        return internService.getInternById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/supervisor/{supervisorId}")
    public List<Intern> getInternsBySupervisorId(@PathVariable Long supervisorId) {
        return internService.getInternsBySupervisorId(supervisorId);
    }

    @PostMapping
    public Intern createIntern(@RequestBody InternRequest request) {
        Intern intern = new Intern();
        intern.setName(request.getName());
        intern.setEmail(request.getEmail());
        intern.setDepartment(request.getDepartment());
        
        return internService.createIntern(intern, request.getSupervisorId());
    }

    @PutMapping("/{id}")
    public Intern updateIntern(@PathVariable Long id, @RequestBody InternRequest request) {
        Intern intern = new Intern();
        intern.setName(request.getName());
        intern.setEmail(request.getEmail());
        intern.setDepartment(request.getDepartment());
        
        return internService.updateIntern(id, intern, request.getSupervisorId());
    }

    @DeleteMapping("/{id}")
    public void deleteIntern(@PathVariable Long id) {
        internService.deleteIntern(id);
    }
}

