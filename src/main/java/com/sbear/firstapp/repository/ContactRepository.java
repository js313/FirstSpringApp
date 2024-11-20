package com.sbear.firstapp.repository;

import com.sbear.firstapp.model.Contact;
import com.sbear.firstapp.rowmappers.ContactRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ContactRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveContactMessage(Contact contact) {
        String sql = "INSERT INTO CONTACT_MSG (NAME,MOBILE_NUM,EMAIL,SUBJECT,MESSAGE,STATUS,CREATED_AT,CREATED_BY) VALUES (?,?,?,?,?,?,?,?)";

        return jdbcTemplate.update(sql, contact.getName(), contact.getMobileNum(), contact.getEmail(), contact.getSubject(), contact.getMessage(), contact.getStatus(), contact.getCreatedAt(), contact.getCreatedBy());
    }

    public List<Contact> findMsgsWithStatus(String status) {
        String sql = "SELECT * FROM CONTACT_MSG WHERE STATUS = ?";
        // Anonymous class syntax, 'PreparedStatementSetter' is an interface(functional interface) and like lambda function, we are implementing it when needed!!!
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, status);
            }
        };
        return jdbcTemplate.query(sql, preparedStatementSetter, new ContactRowMapper());
    }

    public int updateMsgStatus(int contactId, String status, String updatedBy) {
        String sql = "UPDATE CONTACT_MSG SET STATUS = ?, UPDATED_BY = ?, UPDATED_AT = ? WHERE CONTACT_ID = ?";
        // preferable to use prepared statements
        return jdbcTemplate.update(sql, status, updatedBy, LocalDateTime.now(), contactId);
    }
}
