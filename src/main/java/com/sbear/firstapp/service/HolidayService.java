package com.sbear.firstapp.service;

import com.sbear.firstapp.model.Holiday;
import com.sbear.firstapp.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class HolidayService {
    private final HolidayRepository holidayRepository;

    @Autowired
    public HolidayService(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    public List<Holiday> getHolidayList() {
        return holidayRepository.getHolidayList();
    }
}
