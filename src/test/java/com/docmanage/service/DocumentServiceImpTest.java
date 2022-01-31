package com.docmanage.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.docmanage.dao.CustomerRepository;
import com.docmanage.dao.DocumentRepository;
import com.docmanage.model.Customer;
import com.docmanage.model.Document;
import com.docmanage.service.impl.DocumentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DocumentServiceImpTest {

	@InjectMocks
	private DocumentServiceImpl documentService;

	@Mock
	private DocumentRepository documentRepository;

	@Mock
	private CustomerRepository customerRepository;

	@Test
	public void fetchAllCustomerDocuments() {
		Customer customer = populateCustomer();
		Customer customerTwo = populateCustomer();
		customerTwo.setName("David");
		customerTwo.setAddress("London");
		List<Customer> list = new ArrayList<>();
		list.add(customer);
		list.add(customerTwo);

		when(customerRepository.findAll()).thenReturn(list);

		List<Customer> returnListOfCustomers = documentService.fetchAllCustomerDocuments();

		assertEquals(2, returnListOfCustomers.size());
		verify(customerRepository, times(1)).findAll();
	}

	@Test
	public void testAddDocument() {
		Customer customer = populateCustomer();
		documentService.addDocument(customer);
		verify(customerRepository, times(1)).save(customer);
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
