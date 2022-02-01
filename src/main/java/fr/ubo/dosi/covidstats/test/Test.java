package fr.ubo.dosi.covidstats.test;

import java.util.ArrayList;

import fr.ubo.dosi.covidstats.db.PaysCSVDB;
import fr.ubo.dosi.covidstats.entities.CovidInfo;

public class Test {

	public static void main(String[] args)
	{
		PaysCSVDB db = PaysCSVDB.getInstance();
		
		ArrayList<CovidInfo> list = db.getData();
		
		for(CovidInfo c : list)
		{
			System.out.println(c);
		}
	}

}
