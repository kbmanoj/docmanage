package com.docmanage.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docmanage.dao.CustomerRepository;
import com.docmanage.dao.DocumentRepository;
import com.docmanage.model.Customer;
import com.docmanage.model.Document;
import com.docmanage.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {
	
	public static final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private DocumentRepository documentRepository;
	
	@Override
	public Customer addDocument(Customer document) {
		logger.debug("service call addDocument" );
		return customerRepository.save(document);
	}

	@Override
	public Document getDocument(Long documentId) {
		logger.debug("service call getDocument" );
		return documentRepository.findById(documentId).get();
	}

	@Override
	public List<Customer> fetchAllCustomerDocuments() {
		logger.debug("service call fetchAllCustomerDocuments" );
		return customerRepository.findAll();
	}

	@Override
	public Customer getAllCustomerdetails(Long customerId) {
		logger.debug("service call getAllCustomerdetails" );
		return customerRepository.findById(customerId).get();
	}

	
}
