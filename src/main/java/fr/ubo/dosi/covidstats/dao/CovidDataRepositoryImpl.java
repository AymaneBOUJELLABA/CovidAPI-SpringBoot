package fr.ubo.dosi.covidstats.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import fr.ubo.dosi.covidstats.db.PaysCSVDB;
import fr.ubo.dosi.covidstats.entities.CovidInfo;

/**
 * 
 * @author FAYE
 * 
 * Cette class est l'implementation du repository <a>CovidDataRepository</a>
 *
 * @see {@link fr.ubo.dosi.covidstats.dao.CovidDataRepository}
 */
@Repository
public class CovidDataRepositoryImpl implements CovidDataRepository {

	/**
	 * l'instance du PaysCSVDB
	 */
	private PaysCSVDB db = PaysCSVDB.getInstance();
	
	/**
	 * Le logger pour la class CovidDataRepositoryImpl
	 */
	private static Logger logger = LogManager.getLogger(CovidDataRepositoryImpl.class);
	
	/**
	 * La fonction findAllByPays qui permet de récupérer les information d'un pays
	 * @param pays : le pays donnée 
	 */
	@Override
	public List<CovidInfo> findAllByPays(String pays)
	{
		logger.info("Début de fonction findAllByPays pour récupérer les informations d'une pays");
		
		//initialisation de liste des résultats
		ArrayList<CovidInfo> result = new ArrayList<CovidInfo>();
		
		//boucle qui ajouter les données dans la liste
		for(CovidInfo c : db.getData())
		{
			if(c.getPays().toLowerCase().equals(pays.toLowerCase()))
				result.add(c);
			else
				continue;
		}
		
		logger.info("Fin de fonction findAllByPays pour récupérer les informations d'une pays");
		return result;
	}

	/**
	 * La fonction findAllByPaysAndDate qui permet de récupérer les information d'un pays dans une date donnée
	 * @param pays : le pays donnée
	 * @param date : la date donnée 
	 */
	@Override
	public CovidInfo findAllByPaysAndDate(String pays, String date)
	{
		logger.info("Début de fonction findAllByPaysAndDate pour récupérer les informations d'une pays dans une date donnée");
		logger.info("date : " + date + " Pays : " + pays);
		//boucle qui ajouter les données dans l'objet c
		CovidInfo r = null;
		for(CovidInfo c : db.getData())
		{
			if(c.getPays().toLowerCase().equals(pays.toLowerCase()) && c.getDate().equals(date))
				r = c;
			else
				continue;
		}
		
		logger.info("Fin de fonction findAllByPaysAndDate pour récupérer les informations d'une pays dans une date donnée");
		logger.info("(Repository ) Data Found : " + r);
		return r;
	}

	/**
	 * La fonction findAllByPaysforToday qui permet de récupérer les information d'un pays dans le jour actuelle
	 * @param pays : le pays donnée 
	 */
	@Override
	public CovidInfo findAllByPaysforToday(String pays)
	{
		pays = pays.toLowerCase();
		CovidInfo r = new CovidInfo();
		logger.info("Début de fonction findAllByPaysforToday pour récupérer les informations d'une pays pour le jour actuelle");
		
		//le variable pour récupérer la date de jour actuelle
		LocalDate d = LocalDate.now();
		
		//boucle qui ajouter les données dans l'objet c
		for(CovidInfo c : db.getData())
		{
			if(c.getPays().toLowerCase().equals(pays))
			{
				if(c.getDate().equals(d.toString()))
					return c;
				else
				{
					r.setPays(c.getPays());
					r.setDate(c.getDate());
				}
			}
			else
				continue;
		}
		r.setDeces(0);
		r.setGuerisons(0);
		r.setInfections(0);
		logger.info("Fin de fonction findAllByPaysforToday pour récupérer les informations d'une pays pour le jour actuelle");
		return r;
	}

}
