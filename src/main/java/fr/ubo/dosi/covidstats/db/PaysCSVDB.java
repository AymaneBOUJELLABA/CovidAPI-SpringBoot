package fr.ubo.dosi.covidstats.db;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import ch.qos.logback.classic.Logger;

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
	
	public <T> List<T> loadObjectList(Class<T> type, String fileName)
	{
	    try
	    {
	        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
	        CsvMapper mapper = new CsvMapper();
	        File file = new ClassPathResource(fileName).getFile();
	        MappingIterator<T> readValues = 
	          mapper.reader(type).with(bootstrapSchema).readValues(file);
	        return readValues.readAll();
	    } catch (Exception e)
	    {
	        System.err.println("Error occurred while loading object list from file " + fileName + e);
	        return Collections.emptyList();
	    }
	}

}
