package fr.ubo.dosi.covidstats.entities;

import java.util.HashMap;
import java.util.Objects;

/**
 * 
 * @author Aymane BOUJELLABA & Ferdaous AKKAR
 * 
 * Cette class est considéré comme clé composé du fichier CSV
 *
 */
public class PaysDate
{
	private String pays;
	private String date;
	
	
	public PaysDate(String pays, String date)
	{
		super();
		this.pays = pays;
		this.date = date;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(date, pays);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaysDate other = (PaysDate) obj;
		return Objects.equals(date, other.date) && Objects.equals(pays, other.pays);
	}
	
	@Override
	public String toString()
	{
		return "PaysDate [pays=" + pays + ", date=" + date + "] --- hashCode : "+ hashCode();
	}
	public static void main(String[] args)
	{
		PaysDate t = new PaysDate("maroc", "29/04/1999");
		System.out.println(t);
		
		HashMap<PaysDate, String> map = new HashMap<PaysDate, String>();
		
		map.put(t, "valid");
		
		
		PaysDate m = new PaysDate("maroc", "29/04/1999");
		System.out.println(m);
		System.out.println(map.get(m));	}
}
