package fr.ubo.dosi.covidstats.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.FileUtils;

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
		boolean done = downloadFileFromUrl();
		if(done)
			data = (ArrayList<CovidInfo>) getRealInfos(this.readCSVFile("data.csv"));
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
			File file = new File(fileName);
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
				//Séparer les données
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
			System.err.println("Erreur downloadCSVfile: " + e);
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
	
	/**
	 * 
	 * @param input list des données extrait du fichier csv ordonnée par date
	 * @return la liste ordonnées par pays, avec les statistiques de chaque jour ( non cumulée )
	 */
	private List<CovidInfo> getRealInfos(List<CovidInfo> input)
	{
		// créer une nouvelle list vide
		List<CovidInfo> newList = new ArrayList<CovidInfo>();
		
		//trier l'input par pays
		input.sort(Comparator.comparing(CovidInfo::getPays));
	
		//vérifier si la liste n'est pas vide
		if(!input.isEmpty())
		{
			for(int i = 1; i < input.size(); i++)
			{
				//nouvelle valeur
				CovidInfo v = new CovidInfo();
				//next
				CovidInfo u1 = input.get(i);
				//current
				CovidInfo u2 = input.get(i-1);
				//ajout des données
					v.setDate(u2.getDate());
					v.setDeces(u2.getDeces() - u1.getDeces());
					v.setGuerisons(u2.getGuerisons() - u1.getGuerisons());
					v.setInfections(u2.getInfections() - u1.getInfections());
					v.setPays(u2.getPays());
					v.setTauxDeces(u2.getTauxDeces());
					v.setTauxGuerison(u2.getTauxGuerison());
					v.setTauxInfection(u2.getTauxInfection());
				newList.add(v);
				if(i == input.size()-1)
					newList.add(u1);
			}
		}
		return newList;
	}
	
	
	
	/**
	 * fonction qui permet de lire un fichier csv et retourner la liste en fonction d'un type
	 * 
	 * @param <T>
	 * @param type 
	 * @param fileName
	 * @return List<T>
	 */
	/*private <T> List<T> loadObjectList(Class<T> type, String fileName)
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
	}*/
}