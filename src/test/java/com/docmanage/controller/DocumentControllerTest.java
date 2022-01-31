package com.docmanage.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.docmanage.model.Customer;
import com.docmanage.model.Document;
import com.docmanage.service.DocumentService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DocumentController.class)
public class DocumentControllerTest {

	@MockBean
	private DocumentService documentService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void checkAllCustomerDocuments() throws Exception {
		Customer customer = populateCustomer();
		Customer customerTwo = populateCustomer();
		customer.setName("Dan");
		List<Customer> list = new ArrayList<>();
		list.add(customer);
		list.add(customerTwo);
		Mockito.when(documentService.fetchAllCustomerDocuments())
		.thenReturn(list);
		
		mockMvc.perform(get("http://localhost:8080/document/all")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(2)))
		.andExpect(jsonPath("$.[0].name", Matchers.is("Dan")))
		.andExpect(jsonPath("$.[0].documents",Matchers.hasSize(1)))
		.andExpect(jsonPath("$.[0].documents[0].name",Matchers.is("testdoc")))
		.andExpect(jsonPath("$.[0].documents[0].size",Matchers.is(123.0)))
		.andExpect(jsonPath("$.[0].documents[0].type",Matchers.is("pdf")));
	}
	
	@Test
	public void checkAllCustomerDetailsByCustomerId() throws Exception {
		Customer customer = populateCustomer();
		Mockito.when(documentService.getAllCustomerdetails(1L))
		.thenReturn(customer);
		
		mockMvc.perform(get("http://localhost:8080/customer/1")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", Matchers.is("Manoj")))
        .andExpect(jsonPath("$.address", Matchers.is("Nottingham")))
		.andExpect(jsonPath("$.documents",Matchers.hasSize(1)))
		.andExpect(jsonPath("$.documents[0].name",Matchers.is("testdoc")))
		.andExpect(jsonPath("$.documents[0].size",Matchers.is(123.0)))
		.andExpect(jsonPath("$.documents[0].type",Matchers.is("pdf")));
	}
	
	private Customer populateCustomer() {
		Customer customer = new Customer();
		customer.setAddress("Nottingham");
		customer.setContact("0123456789");
		customer.setName("Manoj");	
		List<Document> docList = new ArrayList<>();
		Document document = new Document();
		document.setCategory("testcategory");
		document.setCustomer(customer);
		document.setName("testdoc");
		document.setResolution("high");
		document.setSize(123L);
		document.setType("pdf");
		docList.add(document);
		customer.setDocuments(docList);
		return customer;
	}
}
