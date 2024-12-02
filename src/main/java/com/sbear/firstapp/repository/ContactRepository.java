package com.sbear.firstapp.repository;

import com.sbear.firstapp.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    // Abstract method for some custom queries
    // if you follow the naming convention spring will generate queries on runtime based upon method name
    // MIND-BLOWING STUFF
    // Reference: https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    List<Contact> findByStatus(String status);

    Page<Contact> findByStatus(String status, Pageable pageable);
}
