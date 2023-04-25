package com.nbp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class NbpApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testAvgExchangeRateResponse() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/avgRate/gbp/2023-04-25")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json("{\"avgRate\":5.1958}"));
	}

	@Test
	public void testAvgExchangeRateInvalidCode() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/avgRate/gbpp/2023-04-25")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

	@Test
	public void testAvgExchangeRateInvalidDate() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/avgRate/gbp/25-04-2023")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

	@Test
	public void testMaxBidAskDifferenceResponse() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/maxDiff/gbp/10")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json("{\"maxDifference\":0.1062000000000003}"));
	}

	@Test
	public void testMaxBidAskDifferenceInvalidCode() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/maxDiff/gbpp/10")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

	@Test
	public void testMaxBidAskDifferenceInvalidN() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/maxDiff/gbp/1000")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andExpect(MockMvcResultMatchers.content().string("The number of quotations should be between 1 and 255"));
	}

	@Test
	public void testMinMaxExchangeRateResponse() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/maxDiff/gbp/10")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json("{\"maxDifference\":0.1062000000000003}"));
	}

	@Test
	public void testMinMaxExchangeRateInvalidCode() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/maxDiff/gbpp/10")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

	@Test
	public void testMinMaxExchangeRateInvalidN() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/maxDiff/gbp/1000")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andExpect(MockMvcResultMatchers.content().string("The number of quotations should be between 1 and 255"));
	}
}