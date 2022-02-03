package fr.ubo.dosi.covidstats.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	
	@Override
	public List<CovidInfo> findAllByPays(String pays)
	{
		ArrayList<CovidInfo> result = new ArrayList<CovidInfo>();
		
		for(CovidInfo c : db.getData())
		{
			if(c.getPays().toLowerCase().contains(pays.toLowerCase()))
				result.add(c);
		}
		
		return result;
	}

	@Override
	public CovidInfo findAllByPaysAndDate(String pays, String date)
	{
		for(CovidInfo c : db.getData())
		{
			if(c.getPays().toLowerCase().contains(pays.toLowerCase()) && c.getDate().equals(date))
				return c;
		}
		
		return null;
	}

	@Override
	public CovidInfo findAllByPaysforToday(String pays)
	{
		LocalDate d = LocalDate.now();
		
		for(CovidInfo c : db.getData())
		{
			if(c.getPays().equals(pays) && c.getDate().equals(d.toString()))
				return c;
		}
		
		return null;
	}

}
