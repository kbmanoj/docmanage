package com.docmanage.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.docmanage.model.Customer;
import com.docmanage.model.Document;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
@PropertySource("classpath:application-test.properties")
public class DocumentRepositoryTest {

	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	public void testgetDocument() {
		populateData();
		Document dbDocument = documentRepository.findById(1L).get();
		assertThat(dbDocument.getName()).isEqualTo("testdoc");
		
		customerRepository.deleteAll();
		assertThat(documentRepository.findAll()).isEmpty();	
	}
	
	public void populateData() {
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
		
		customerRepository.save(customer);
	}
}
