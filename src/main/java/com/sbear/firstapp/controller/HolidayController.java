package com.sbear.firstapp.controller;

import com.sbear.firstapp.model.Holiday;
import com.sbear.firstapp.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidayController {
    HolidayService holidayService;
    public enum DisplayType {
        all, federal, festival
    }

    @Autowired
    HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @RequestMapping("/holidays")
    public String displayHolidays(Model model, @RequestParam(required = false) boolean festival, @RequestParam(required = false) boolean federal) {
        model.addAttribute("federal", federal);
        model.addAttribute("festival", festival);
        List<Holiday> holidays = holidayService.getHolidayList();

        Holiday.Type[] types = Holiday.Type.values();

        for (Holiday.Type type : types) {
            if((type == Holiday.Type.FEDERAL && !federal) || (type == Holiday.Type.FESTIVAL && !festival)) continue;
            model.addAttribute(type.toString(),
                    (holidays.stream().filter(holiday -> holiday.type().equals(type)).collect(Collectors.toList())));
        }

        return "holidays.html";
    }

    @GetMapping("/holidays/{display}")
    public String displayHolidays(Model model, @PathVariable DisplayType display) {
        model.addAttribute("federal", (display == DisplayType.all || display == DisplayType.federal));
        model.addAttribute("festival", (display == DisplayType.all || display == DisplayType.festival));
        List<Holiday> holidays = holidayService.getHolidayList();

        Holiday.Type[] types = Holiday.Type.values();

        for (Holiday.Type type : types) {
            if((type == Holiday.Type.FEDERAL && !(display == DisplayType.all || display == DisplayType.federal)) ||
                    (type == Holiday.Type.FESTIVAL && !(display == DisplayType.all || display == DisplayType.festival))) continue;
            model.addAttribute(type.toString(),
                    (holidays.stream().filter(holiday -> holiday.type().equals(type)).collect(Collectors.toList())));
        }

        return "holidays.html";
    }
}
