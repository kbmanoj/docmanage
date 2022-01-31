package com.docmanage.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.docmanage.model.Customer;
import com.docmanage.model.Document;
import com.docmanage.service.DocumentService;

@RestController
public class DocumentController {

	public static final Logger logger = LoggerFactory.getLogger(DocumentController.class);

	@Autowired
	private DocumentService documentService;

	@PostMapping("/document")
	public ResponseEntity<Customer> addDocument(@Valid @RequestBody Customer customer) {
		logger.debug("controller :: called for addDocument :: " + customer);
		Customer custCreated = documentService.addDocument(customer);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(custCreated.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/document/{id}")
	public ResponseEntity<Document> getDocuments(@PathVariable Long id) {
		logger.debug("Controller :: getDocuments called for Id :: " + id);
		Document document = documentService.getDocument(id);
		return ResponseEntity.ok(document);
	}

	@GetMapping("document/all")
	public ResponseEntity<List<Customer>> getAllCustomerDocuments() {
		logger.debug("Controller :: getAllCustomerDocuments called ");
		List<Customer> customerDocuments = documentService.fetchAllCustomerDocuments();
		return ResponseEntity.ok(customerDocuments);
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getAllCustomerDetailsByCustomerId(@PathVariable Long id) {
		Customer customerDetails = documentService.getAllCustomerdetails(id);
		return ResponseEntity.ok(customerDetails);
	}

}
