package fr.ubo.dosi.covidstats.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
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
	private static final String fileurl = "https://coronavirus.politologue.com/data/coronavirus/coronacsv.aspx?format=csv&t=pays";
	private static PaysCSVDB instance = null;
	private ArrayList<CovidInfo> data = new ArrayList<>();

	private PaysCSVDB()
	{
		reloadData();
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
	public void reloadData()
	{
		if(downloadFileFromUrl())
			data = (ArrayList<CovidInfo>) this.readCSVFile("data.csv");
	}
	/**
	 * fonction qui permet de lire un fichier csv et retourner la liste en fonction d'un type
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
	 * Fonction qui télécharge le fichier d'apres un lien
	 * @return
	 */
	private static boolean downloadFileFromUrl()
	{
		try
		{
			URL url = new URL(fileurl);
	        File file = new File("data.csv");
			FileUtils.copyURLToFile(url,file,60*1000,30*1000);
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}
	/**
	 * Fonction qui fait la lecture du fichier est retourner une liste qui contient les données
	 * @param fileName
	 * @return
	 */
	private List<CovidInfo> readCSVFile(String fileName)
	{
		List<CovidInfo> result = new ArrayList<CovidInfo>();
		try
		{
			//Récupérer le fichier
			File file = new ClassPathResource(fileName).getFile();
			//initialiser le buffer reader pour lire le contenue du fichier
			BufferedReader csvReader = new BufferedReader(new FileReader(file));
			//variable qui servira à lire les lines des fichiers
			String row = "";
			//counter pour éliminer les 8 premiers lignes
			short i = 0;
			
			//boucle tant que le EOF not atteint faire
			while((row = csvReader.readLine())!=null)
			{
				
				//skip les 8 premiers lignes
				i++;
				if(i<=8)
					continue;
				//Séparer les donnes
				String[] data = row.split(";");
				CovidInfo d = new CovidInfo(
						data[0],
						data[1],
						Integer.parseInt(data[2]),
						Integer.parseInt(data[3]),
						Integer.parseInt(data[4]),
						Double.parseDouble(data[5]),
						Double.parseDouble(data[6]),
						Double.parseDouble(data[7])
					);
				result.add(d);
			}
		}catch(Exception e)
		{
			System.err.println("Erreur : " + e);
		}
        
		return result;
	}
	/**
	 * @return the data
	 */
	public ArrayList<CovidInfo> getData()
	{
		return data;
	}
}