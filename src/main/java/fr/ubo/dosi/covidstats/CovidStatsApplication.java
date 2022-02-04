package fr.ubo.dosi.covidstats;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import fr.ubo.dosi.covidstats.db.PaysCSVDB;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class CovidStatsApplication
{
	private static Logger logger = LogManager.getLogger(PaysCSVDB.class);

	public static void main(String[] args)
	{
		SpringApplication.run(CovidStatsApplication.class, args);
	}
	
	
	@Scheduled(fixedRate = 36000000)
	public void reloadDataFromCSV()
	{
		
		logger.info("La rechargement de donnée !!");
		PaysCSVDB db = PaysCSVDB.getInstance();
		db.reloadData();
		logger.info("Fin de Rechargement de donnée !!");
	}

}
