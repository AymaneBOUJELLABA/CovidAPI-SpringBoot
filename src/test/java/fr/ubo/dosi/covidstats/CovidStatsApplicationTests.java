package fr.ubo.dosi.covidstats;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fr.ubo.dosi.covidstats.db.PaysCSVDB;

@SpringBootTest
class CovidStatsApplicationTests {

	@Test
	void contextLoads()
	{
		
	}
	
	/**
	 * un test qui permet de vérifier que la liste chargé des données csv n'est pas vide
	 */
	@Test
	public void checkDataLoading()
	{
		PaysCSVDB db = PaysCSVDB.getInstance();
		assertTrue(!db.getData().isEmpty());
	}
	
	
	

}
