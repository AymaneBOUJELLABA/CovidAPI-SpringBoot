package fr.ubo.dosi.covidstats.entities;

/**
 * 
 * @author AymaneBOUJELLABA & FerdaousAKKAR
 *
 */
public class CovidInfo
{
	// Date,Pays,Infections,Deces,Guerisons,TauxDeces,TauxGuerison,TauxInfection

	private String date;
	private String pays;
	private Integer infections;
	private Integer deces;
	private Integer guerisons;
	private double tauxDeces;
	private double tauxGuerison;
	private double tauxInfection;
	/**
	 * 
	 */
	public CovidInfo()
	{
		super();
	}

	/**
	 * 
	 * @param date
	 * @param pays
	 * @param infections
	 * @param deces
	 * @param guerisons
	 * @param tauxDeces
	 * @param tauxGuerison
	 * @param tauxInfection
	 */
	public CovidInfo(String date, String pays, Integer infections, Integer deces, Integer guerisons, double tauxDeces,
			double tauxGuerison, double tauxInfection)
	{
		super();
		this.date = date;
		this.pays = pays;
		this.infections = infections;
		this.deces = deces;
		this.guerisons = guerisons;
		this.tauxDeces = tauxDeces;
		this.tauxGuerison = tauxGuerison;
		this.tauxInfection = tauxInfection;
	}
	
	public String getDate() 
	{
		return date;
	}
	
	public void setDate(String date) 
	{
		this.date = date;
	}
	
	public String getPays() 
	{
		return pays;
	}
	
	public void setPays(String pays) 
	{
		this.pays = pays;
	}
	
	public Integer getInfections() 
	{
		return infections;
	}
	
	public void setInfections(Integer infections)
	{
		this.infections = infections;
	}
	
	public Integer getDeces() 
	{
		return deces;
	}
	
	public void setDeces(Integer deces) 
	{
		this.deces = deces;
	}
	
	public Integer getGuerisons() 
	{
		return guerisons;
	}
	
	public void setGuerisons(Integer guerisons)
	{
		this.guerisons = guerisons;
	}
	
	public double getTauxDeces() 
	{
		return tauxDeces;
	}
	
	public void setTauxDeces(double tauxDeces)
	{
		this.tauxDeces = tauxDeces;
	}
	
	public double getTauxGuerison() 
	{
		return tauxGuerison;
	}
	
	public void setTauxGuerison(double tauxGuerison) 
	{
		this.tauxGuerison = tauxGuerison;
	}
	
	public double getTauxInfection() 
	{
		return tauxInfection;
	}
	
	public void setTauxInfection(double tauxInfection) 
	{
		this.tauxInfection = tauxInfection;
	}

	@Override
	public String toString() {
		return "CovidInfo [date=" + date + ", pays=" + pays + ", infections=" + infections + ", tauxInfection="
				+ tauxInfection + "]";
	}
	
	
	
}
