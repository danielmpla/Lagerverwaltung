package de.hszg.fei.lagerverwaltung;

import java.util.List;

import de.hszg.fei.lagerverwaltung.entity.*;
import de.hszg.fei.lagerverwaltung.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class LagerverwaltungApplication {
	
    @SuppressWarnings("deprecation")
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
	
	/*@Bean
	CommandLineRunner runner(RadRepository radRepository, FahrwerkRepository fahrwerkRepository, KarosserieRepository karosserieRepository, InnenausstattungRepository innenausstattungRepository, FahrzeugRepository fahrzeugRepository, LagerKarosserieRepository lagerKarosserieRepository){
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				Rad rad = new Rad("grün", 2, 0, 0, 0, "rad");
				Fahrwerk fahrwerk = new Fahrwerk("sportlich", 0, 0, 0, 0, "nice");
				Karosserie karosserie = new Karosserie("rund", 0, 0, 0, 0, "cool");
				Innenausstattung innenausstattung = new Innenausstattung("Luxus", 0, 0, 0, 0, "billig");
				
				rad = radRepository.save(rad);
				fahrwerk = fahrwerkRepository.save(fahrwerk);
				karosserie = karosserieRepository.save(karosserie);
				innenausstattung = innenausstattungRepository.save(innenausstattung); // funktioniert in dieser Konfiguration auch ohne Zuweisung

				Fahrzeug fahrzeug = new Fahrzeug(karosserie, fahrwerk, innenausstattungRepository.findAll().get(0), rad, 4, 1, 0);
				
				fahrzeugRepository.save(fahrzeug);
				
				List<Fahrzeug> fahrzeuge = fahrzeugRepository.findAll();
				
				for (Fahrzeug listFahrzeug : fahrzeuge) {
					System.out.println(listFahrzeug.getInnenausstattung().getId() + ": " + listFahrzeug.getInnenausstattung().getName());
				}

				LagerKarosserie lagerKarosserie = new LagerKarosserie(null, 1L, karosserie);
				lagerKarosserieRepository.save(lagerKarosserie);
			}
		};
	}*/

	public static void main(String[] args) {
		SpringApplication.run(LagerverwaltungApplication.class, args);
	}

}

