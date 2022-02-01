package fr.ubo.dosi.covidstats.db;

public class PaysCSVDB
{
	private static PaysCSVDB instance = null;
	
	private PaysCSVDB()
	{
		
	}
	
	public static PaysCSVDB getInstance()
	{
		if(instance == null)
			instance = new PaysCSVDB();
		
		return instance;
	}

}
