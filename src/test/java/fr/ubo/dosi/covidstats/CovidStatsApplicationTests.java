package fr.ubo.dosi.covidstats;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.ubo.dosi.covidstats.controllers.CovidStatController;
import fr.ubo.dosi.covidstats.dao.CovidDataRepository;
import fr.ubo.dosi.covidstats.db.PaysCSVDB;
import fr.ubo.dosi.covidstats.entities.CovidInfo;

@SpringBootTest
class CovidStatsApplicationTests 
{
	@Autowired
	CovidDataRepository dataDAO;

	@Autowired
	CovidStatController controller;
	
	@Test
	public void contextLoads() throws Exception
	{
		assertThat(dataDAO).isNotNull();
		assertThat(controller).isNotNull();
	}
	
	@Test
	void checkSingleInstance()
	{
		PaysCSVDB db = PaysCSVDB.getInstance();
		PaysCSVDB db2 = PaysCSVDB.getInstance();
		
		assertEquals(db2.hashCode(), db.hashCode());
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
	
	@Test
	public void checkAllPaysData()
	{
		assertTrue(dataDAO.findAllByPays("maroc").get(0).getPays().contains("Maroc"));
	}
	
	@Test void checkAllPaysData_Empty()
	{
		assertThat(dataDAO.findAllByPays("NANANAN")).isEmpty();
	}
	
	@Test void checkPaysDataWithDate_Null()
	{
		assertThat(dataDAO.findAllByPaysAndDate("NANANAN","not a date")).isNull();
	}
	
	@Test void checktodayPaysData_Null()
	{
		assertThat(dataDAO.findAllByPaysforToday("NANANAN")).isNull();
	}
	
	@Test
	public void checkPaysDataWithDate()
	{
		CovidInfo v = dataDAO.findAllByPaysAndDate("france", "2022-01-31");
		assertTrue(v.getDate().equals("2022-01-31") && v.getPays().equals("France"));
	}
	
	@Test
	public void checktodayPaysData()
	{
		CovidInfo v = dataDAO.findAllByPaysforToday("france");
		LocalDate d = LocalDate.now();
		assertTrue(v.getDate().equals(d.toString()) && v.getPays().equals("France"));
		
		assertTrue(v.toString().contains("France"));
	}
}
