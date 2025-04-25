package com.lucas.pilotos.pilotos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.lucas.pilotos.pilotos.model.*;
import com.lucas.pilotos.pilotos.repository.*;

@SpringBootApplication
public class PilotosApplication {

    public static void main(String[] args) {
            SpringApplication.run(PilotosApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(TeamRepository teamRepository, PilotRepository pilotRepository) {
        return args -> {
            // Clear existing data
            pilotRepository.deleteAll();
            teamRepository.deleteAll();

            // Create teams
            Team mercedes = new Team(null, "Mercedes", "Brackley, UK", 1970, null);
            Team redbull = new Team(null, "Red Bull", "Milton Keynes, UK", 2005, null);

            teamRepository.save(mercedes);
            teamRepository.save(redbull);

            // Create pilots
            Pilot pilot1 = new Pilot(null, "Lewis", "Hamilton", 44, 103, mercedes);
            Pilot pilot2 = new Pilot(null, "George", "Russell", 63, 1, mercedes);
            Pilot pilot3 = new Pilot(null, "Max", "Verstappen", 1, 54, redbull);
            Pilot pilot4 = new Pilot(null, "Sergio", "Perez", 11, 6, redbull);

            pilotRepository.save(pilot1);
            pilotRepository.save(pilot2);
            pilotRepository.save(pilot3);
            pilotRepository.save(pilot4);
        };
    }

}
