package com.sbear.firstapp.rowmappers;

import com.sbear.firstapp.model.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

// REDUNDANT AFTER IMPLEMENTING JPA

public class ContactRowMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Contact contact = new Contact();
        contact.setContactId(resultSet.getInt("CONTACT_ID"));
        contact.setStatus(resultSet.getString("STATUS"));
        contact.setEmail(resultSet.getString("EMAIL"));
        contact.setMessage(resultSet.getString("MESSAGE"));
        contact.setSubject(resultSet.getString("SUBJECT"));
        contact.setMobileNum(resultSet.getString("MOBILE_NUM"));
        contact.setName(resultSet.getString("NAME"));
        contact.setCreatedBy(resultSet.getString("CREATED_BY"));
        contact.setCreatedAt(resultSet.getTimestamp("CREATED_AT").toLocalDateTime());

        if(resultSet.getTimestamp("UPDATED_AT") != null) {
            contact.setUpdatedBy(resultSet.getString("UPDATED_BY"));
            contact.setUpdatedAt(resultSet.getTimestamp("UPDATED_AT").toLocalDateTime());
        }

        return contact;
    }
}
