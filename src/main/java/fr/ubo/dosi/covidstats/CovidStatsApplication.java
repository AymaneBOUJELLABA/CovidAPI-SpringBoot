package fr.ubo.dosi.covidstats;

import java.time.LocalDateTime;

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

	public static void main(String[] args)
	{
		SpringApplication.run(CovidStatsApplication.class, args);
	}
	
	
	@Scheduled(fixedRate = 36000000)
	public void reloadDataFromCSV()
	{
		System.out.println(LocalDateTime.now() + "  : ----- Reloading data ---- ");
		PaysCSVDB db = PaysCSVDB.getInstance();
		db.reloadData();
	}

}
