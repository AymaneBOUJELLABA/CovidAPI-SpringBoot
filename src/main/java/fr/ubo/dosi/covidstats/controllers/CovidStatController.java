package fr.ubo.dosi.covidstats.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.ubo.dosi.covidstats.dao.CovidDataRepository;
import fr.ubo.dosi.covidstats.db.PaysCSVDB;
import fr.ubo.dosi.covidstats.entities.CovidInfo;


/**
 * 
 * @author FAYE
 * 
 * Ce controlleur permet d'exposer 3 endpoints
 * <i> GET /api/oneCountryData</i>
 * <i> GET /api/oneCountryDataWithDate</i>
 * <i> GET /api/todayCountryData</i>
 *
 */
@RestController
@RequestMapping("/api")
public class CovidStatController
{
	
	private static Logger logger = LogManager.getLogger(PaysCSVDB.class);
	
	@Autowired
	CovidDataRepository dataDAO;
	
	/**
	 * GET
	 * @param CountryName : le nom du pays
	 * @return List<CovidInfo> : la liste des données de ce pays
	 */
	@GetMapping("/oneCountryData")
	public List<CovidInfo> allDataParPays(@RequestParam String CountryName)
	{
		//initialiser la liste du résultat
		List<CovidInfo> r = new ArrayList<CovidInfo>();
		logger.info("");
		try
		{
			//faire l'appèle au dao
			r = dataDAO.findAllByPays(CountryName);
			
		}catch(Exception e)
		{
			System.out.println("Erreur : " + e);
		}
		
		return r;
	}
	
	/**
	 * GET
	 * @param CountryName : le nom du pays
	 * @param date : la date sous forme de yyyy-mm-dd
	 * @return les informations de cette date pour ce pays
	 */
	@GetMapping("/oneCountryDataWithDate")
	public CovidInfo oneCountryWithDate(@RequestParam String CountryName, @RequestParam String date)
	{
		CovidInfo r = new CovidInfo();
		try
		{
			LocalDate d = LocalDate.parse(date);
			
			r = dataDAO.findAllByPaysAndDate(CountryName, d.toString());
		}catch(Exception e)
		{
			System.out.println("Erreur : " + e);
		}
		
		return r;
	}
	
	/**
	 * GET 
	 * @param CountryName : le nom de ce pays
	 * @return les informations du jours actuel pour ce pays
	 */
	@GetMapping("/todayCountryData")
	public CovidInfo todayCountryData(@RequestParam String CountryName)
	{
		CovidInfo r = new CovidInfo();
		try
		{
			LocalDate d = LocalDate.now();
			
			r = dataDAO.findAllByPaysAndDate(CountryName, d.toString());
		}catch(Exception e)
		{
			System.out.println("Erreur : " + e);
		}
		
		return r;
	}
}
