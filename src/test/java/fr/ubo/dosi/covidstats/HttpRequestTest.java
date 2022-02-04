package fr.ubo.dosi.covidstats;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class HttpRequestTest
{
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void shouldReturnNotEmptyListAllPaysData() throws Exception
	{
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/oneCountryData?CountryName=maroc"))
								.andExpect(status().isOk())
								.andExpect(MockMvcResultMatchers.jsonPath("$[0].pays").value("Maroc"));
	}
	
	@Test
	public void shouldReturnNotNullResultPaysWithDate() throws Exception
	{
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/oneCountryDataWithDate?CountryName=maroc&date=2022-01-31"))
								.andExpect(status().isOk())
								.andExpect(MockMvcResultMatchers.jsonPath("$[0].pays").value("Maroc"))
								.andExpect(MockMvcResultMatchers.jsonPath("$[0].date").value("2022-01-31"));
	}
	
	@Test
	public void shouldReturnNotNullResultForToday() throws Exception
	{
		LocalDate d = LocalDate.now();
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/todayCountryData?CountryName=maroc"))
								.andExpect(status().isOk())
								.andExpect(MockMvcResultMatchers.jsonPath("$[0].date").value(d.toString()))
								.andExpect(MockMvcResultMatchers.jsonPath("$[0].pays").value("Maroc"));
	}
}
