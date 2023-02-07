package com.ltp.gradesubmission;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.util.LinkedMultiValueMap;

@SpringBootTest
@AutoConfigureMockMvc
class GradeSubmissionApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertNotNull(mockMvc);
	}

	@Test
	public void testShowGradeForm() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/?id=123");
		mockMvc.perform(request).andExpectAll(
				status().is2xxSuccessful(),
				view().name("form"),
				model().attributeExists("grade"));
	}

	@Test
	public void testSuccessfulSubmission() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("name", "Jackson");
		params.add("subject", "Math");
		params.add("score", "A+");

		RequestBuilder request = MockMvcRequestBuilders.post("/handleSubmit").params(params);
		mockMvc.perform(request).andExpectAll(status().is3xxRedirection(), redirectedUrl("/grades"));
	}

	@Test
	public void testUnsuccessfulSubmission() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("name", "");
		params.add("subject", "Math");
		params.add("score", "A+");

		RequestBuilder request = MockMvcRequestBuilders.post("/handleSubmit").params(params);
		mockMvc.perform(request).andExpectAll(status().is2xxSuccessful(), view().name("form"));
	}

	@Test
	public void testGetGrades() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/grades");
		mockMvc.perform(request).andExpectAll(status().is2xxSuccessful(), view().name("grades"),
				model().attributeExists("grades"));
	}

}
