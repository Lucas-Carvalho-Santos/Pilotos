/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lucas.pilotos.pilotos.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.pilotos.pilotos.model.*;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByName(String name);
}

