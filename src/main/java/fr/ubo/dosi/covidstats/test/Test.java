package fr.ubo.dosi.covidstats.test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import fr.ubo.dosi.covidstats.db.PaysCSVDB;
import fr.ubo.dosi.covidstats.entities.CovidInfo;

public class Test
{

	public static void main(String[] args)
	{
		LocalDate t = LocalDate.now();

		System.out.println(t.toString());
	}

}
