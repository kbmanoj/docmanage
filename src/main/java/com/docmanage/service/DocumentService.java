package com.docmanage.service;

import java.util.List;

import com.docmanage.model.Customer;
import com.docmanage.model.Document;

public interface DocumentService {

	public Customer addDocument(Customer document);

	public Document getDocument(Long documentId);

	public List<Customer> fetchAllCustomerDocuments();

	public Customer getAllCustomerdetails(Long customerId);

}
