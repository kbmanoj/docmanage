package com.docmanage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.docmanage.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>{

}
