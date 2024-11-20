package com.sbear.firstapp.repository;

import com.sbear.firstapp.model.Holiday;
import com.sbear.firstapp.rowmappers.HolidayRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HolidayRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    HolidayRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Holiday> getHolidayList() {
        String sql = "SELECT * FROM HOLIDAYS";

        return jdbcTemplate.query(sql, new HolidayRowMapper());
    }
}
