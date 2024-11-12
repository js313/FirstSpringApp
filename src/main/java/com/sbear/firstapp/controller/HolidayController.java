package com.sbear.firstapp.controller;

import com.sbear.firstapp.model.Holiday;
import com.sbear.firstapp.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidayController {
    HolidayService holidayService;

    @Autowired
    HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @RequestMapping("/holidays")
    public String DisplayHolidays(Model model) {
        List<Holiday> holidays = holidayService.getHolidayList();

        Holiday.Type[] types = Holiday.Type.values();

        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(),
                    (holidays.stream().filter(holiday -> holiday.type().equals(type)).collect(Collectors.toList())));
        }

        return "holidays.html";
    }
}
