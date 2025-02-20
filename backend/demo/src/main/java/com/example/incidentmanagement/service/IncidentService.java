package com.example.incidentmanagement.service;

import com.example.incidentmanagement.model.Incident;
import com.example.incidentmanagement.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    public Incident createIncident(Incident incident) {
        incident.setCreatedAt(LocalDateTime.now());
        incident.setUpdatedAt(LocalDateTime.now());
        return incidentRepository.save(incident);
    }

    public Optional<Incident> getIncidentById(Long id) {
        return incidentRepository.findById(id);
    }

    public Incident updateIncident(Long id, Incident updatedIncident) {
        return incidentRepository.findById(id).map(incident -> {
            incident.setTitle(updatedIncident.getTitle());
            incident.setDescription(updatedIncident.getDescription());
            incident.setStatus(updatedIncident.getStatus());
            incident.setUpdatedAt(LocalDateTime.now());
            return incidentRepository.save(incident);
        }).orElseThrow(() -> new RuntimeException("Incident not found"));
    }

    public void deleteIncident(Long id) {
        incidentRepository.deleteById(id);
    }
}
