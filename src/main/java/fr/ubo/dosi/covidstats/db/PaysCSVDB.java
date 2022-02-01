package fr.ubo.dosi.covidstats.db;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import fr.ubo.dosi.covidstats.entities.CovidInfo;

/**
 * @author AymaneBOUJELLABA & FerdaousAKKAR
 * 
 * Cette class nous permet de lire le fichier csv et récupérer les données sous forme d'un ArrayList<CovidInfo>
 */

public class PaysCSVDB
{
	private static PaysCSVDB instance = null;
	private ArrayList<CovidInfo> data = new ArrayList<>();
	
	private PaysCSVDB()
	{
		data = ((ArrayList<CovidInfo>)loadObjectList(CovidInfo.class, "data.csv"));
	}
	
	/**
	 * 
	 * @return l'instance de la classe Singelton PaysCSVDB
	 */
	public static PaysCSVDB getInstance()
	{
		if(instance == null)
			instance = new PaysCSVDB();
		
		return instance;
	}
	
	/**
	 * fonction qui permet de lire un fichier csv et retourner la liste en focntion d'un type
	 * 
	 * @param <T>
	 * @param type 
	 * @param fileName
	 * @return List<T>
	 */
	private <T> List<T> loadObjectList(Class<T> type, String fileName)
	{
		try
	    {
	    	CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
	        CsvMapper mapper = new CsvMapper();
	        File file = new ClassPathResource(fileName).getFile();
	        MappingIterator<T> readValues = mapper.reader(type).with(bootstrapSchema).readValues(file);
	        return readValues.readAll();
	    } catch (Exception e)
	    {
	        System.err.println("Error occurred while loading object list from file " + fileName +" exception : "+e);
	        return Collections.emptyList();
	    }
	}

	/**
	 * @return the data
	 */
	public ArrayList<CovidInfo> getData() {
		return data;
	}
}