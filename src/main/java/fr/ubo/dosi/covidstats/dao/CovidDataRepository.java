package fr.ubo.dosi.covidstats.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.ubo.dosi.covidstats.entities.CovidInfo;

@Repository
public interface CovidDataRepository
{
	public List<CovidInfo> findAllByPays(String pays);
	
	public CovidInfo findAllByPaysAndDate(String pays, String date);
	
	public CovidInfo findAllByPaysforToday(String pays);
}
