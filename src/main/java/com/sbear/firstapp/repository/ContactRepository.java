package com.sbear.firstapp.repository;

import com.sbear.firstapp.model.Contact;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    // Abstract method for some custom queries
    // if you follow the naming convention spring will generate queries on runtime based upon method name
    // MIND-BLOWING STUFF
    // Reference: https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    List<Contact> findByStatus(String status);
    Optional<Contact> findByContactIdAndStatus(int contactId, String status);

//    @Query("SELECT c FROM Contact c WHERE c.status = :status")  //JDQL Query
    @Query(value = "SELECT * FROM contact_msg c WHERE c.status = :status", nativeQuery = true)
    Page<Contact> findByStatusWithQuery(@Param("status") String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2")
    int updateStatusById(String status, int id);

    // Example of Named Queries

    Page<Contact> findOpenMsgs(@Param("status") String status, Pageable pageable);

    @Transactional
    @Modifying
    int updateMsgStatus(String status, int id);
}
