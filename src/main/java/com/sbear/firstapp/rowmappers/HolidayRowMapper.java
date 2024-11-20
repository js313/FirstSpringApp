package com.sbear.firstapp.rowmappers;

import com.sbear.firstapp.model.Holiday;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HolidayRowMapper implements RowMapper<Holiday> {

    @Override
    public Holiday mapRow(ResultSet rs, int rowNum) throws SQLException {
        Holiday holiday = new Holiday();
        holiday.setDay(rs.getString("DAY"));
        holiday.setReason(rs.getString("REASON"));
        holiday.setType(Holiday.Type.valueOf(rs.getString("TYPE")));
        holiday.setCreatedBy(rs.getString("CREATED_BY"));
        holiday.setCreatedAt(rs.getTimestamp("CREATED_AT").toLocalDateTime());

        if(rs.getTimestamp("UPDATED_AT") != null) {
            holiday.setUpdatedBy(rs.getString("UPDATED_BY"));
            holiday.setUpdatedAt(rs.getTimestamp("UPDATED_AT").toLocalDateTime());
        }

        return holiday;
    }
}
