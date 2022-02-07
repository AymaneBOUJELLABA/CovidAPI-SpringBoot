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
	/**
	 * Le logger pour le controller CovidStatController
	 */
	private static Logger logger = LogManager.getLogger(CovidStatController.class);
	
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
		logger.info("Lancement de controller oneCountryData !");
		try
		{
			//faire l'appèle au dao
			r = dataDAO.findAllByPays(CountryName);
			
		}catch(Exception e)
		{
			logger.error("Erreur dans le controller oneCountryData !"+e);
		}
		

		logger.info("Fin de controller oneCountryData !");
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
		//initialiser l'objet du résultat
		CovidInfo r = new CovidInfo();

		logger.info("Lancement de controller oneCountryDataWithDate !");
		try
		{
			//formater la date donnée
			LocalDate d = LocalDate.parse(date);
			
			//faire l'appel au DAO
			r = dataDAO.findAllByPaysAndDate(CountryName, d.toString());
		}catch(Exception e)
		{
			logger.error("Erreur dans controller oneCountryDataWithDate !"+e);
		}
		
		logger.info("Fin de controller oneCountryDataWithDate !");
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
		logger.info("Lancement de controller todayCountryData!");
		
		//initialiser l'objet du résultat
		CovidInfo r = new CovidInfo();
		try
		{
			//récupérer la date de jour actuelle
			LocalDate d = LocalDate.now();
			
			//faire l'appel au DAO
			r = dataDAO.findAllByPaysAndDate(CountryName, d.toString());
		}catch(Exception e)
		{
			logger.error("Erreur dans controller TodayCountryData!"+e);
		}
		
		logger.info("Fin de controller TodayCountryData!");
		return r;
	}
}
