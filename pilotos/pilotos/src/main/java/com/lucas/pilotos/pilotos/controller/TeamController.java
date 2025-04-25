/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lucas.pilotos.pilotos.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.lucas.pilotos.pilotos.repository.*;
import com.lucas.pilotos.pilotos.model.*;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    
    private final TeamRepository teamRepository;
    private final PilotRepository pilotRepository;
    
    public TeamController(TeamRepository teamRepository, PilotRepository pilotRepository) {
        this.teamRepository = teamRepository;
        this.pilotRepository = pilotRepository;
    }
    
    @GetMapping
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
    
    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamRepository.save(team);
    }
    
    @GetMapping("/{teamId}/pilots")
    public List<Pilot> getTeamPilots(@PathVariable Long teamId) {
        return pilotRepository.findByTeamId(teamId);
    }
    
    @PostMapping("/{teamId}/pilots")
    public Pilot addPilotToTeam(@PathVariable Long teamId, @RequestBody Pilot pilot) {
        Team team = teamRepository.findById(teamId)
            .orElseThrow(() -> new RuntimeException("Team not found"));
        pilot.setTeam(team);
        return pilotRepository.save(pilot);
    }
}
