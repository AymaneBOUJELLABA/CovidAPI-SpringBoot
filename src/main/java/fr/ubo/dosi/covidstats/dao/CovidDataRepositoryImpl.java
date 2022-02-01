package fr.ubo.dosi.covidstats.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.ubo.dosi.covidstats.db.PaysCSVDB;
import fr.ubo.dosi.covidstats.entities.CovidInfo;

public class CovidDataRepositoryImpl implements CovidDataRepository {

	private PaysCSVDB db = PaysCSVDB.getInstance();
	
	@Override
	public List<CovidInfo> findAllByPays(String pays)
	{
		ArrayList<CovidInfo> result = new ArrayList<CovidInfo>();
		
		for(CovidInfo c : db.getData())
		{
			if(c.getPays().equals(pays))
				result.add(c);
		}
		
		return result;
	}

	@Override
	public CovidInfo findAllByPaysAndDate(String pays, String date)
	{
		for(CovidInfo c : db.getData())
		{
			if(c.getPays().equals(pays) && c.getDate().equals(date))
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
			if(c.getPays().equals(pays) && c.getDate().equals(d))
				return c;
		}
		
		return null;
	}

}
