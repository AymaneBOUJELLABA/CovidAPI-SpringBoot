package fr.ubo.dosi.covidstats.dao;

import java.util.List;

import fr.ubo.dosi.covidstats.entities.CovidInfo;

/**
 * 
 * @author FAYE
 * 
 * Repository qui serve à récupérer les données du fichier CSV à partir de la classe <a>PaysCSVDB</a>
 *
 */
public interface CovidDataRepository
{
	/**
	 * 
	 * @param pays
	 * @return la liste des informations de ce pays
	 */
	public List<CovidInfo> findAllByPays(String pays);
	
	/**
	 * 
	 * @param pays
	 * @param date
	 * @return la liste des informations de ce pays pour cette date
	 */
	public CovidInfo findAllByPaysAndDate(String pays, String date);
	
	/**
	 * 
	 * @param pays
	 * @return les informations de ce pays pour le jour actuel
	 */
	public CovidInfo findAllByPaysforToday(String pays);
}
