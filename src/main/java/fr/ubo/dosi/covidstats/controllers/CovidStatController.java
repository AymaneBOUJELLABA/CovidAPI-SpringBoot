package fr.ubo.dosi.covidstats.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.ubo.dosi.covidstats.dao.CovidDataRepository;
import fr.ubo.dosi.covidstats.entities.CovidInfo;

@RestController
@RequestMapping("/api")
public class CovidStatController
{
	@Autowired
	CovidDataRepository dataDAO;
	
	@GetMapping("/oneCountryData")
	public List<CovidInfo> allDataParPays(@RequestParam String CountryName)
	{
		List<CovidInfo> r = new ArrayList<CovidInfo>();
		
		try
		{
			r = dataDAO.findAllByPays(CountryName);
			
		}catch(Exception e)
		{
			System.out.println("Erreur : " + e);
		}
		
		return r;
	}

}
