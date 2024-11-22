package com.sbear.firstapp.repository;

import com.sbear.firstapp.model.Contact;
import com.sbear.firstapp.rowmappers.ContactRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {
    // Abstract method for some custom queries
    // if you follow the naming convention spring will generate queries on runtime based upon method name
    // MIND-BLOWING STUFF
    // Reference: https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    List<Contact> findByStatus(String status);
}
