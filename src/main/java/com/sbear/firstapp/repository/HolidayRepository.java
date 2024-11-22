package com.sbear.firstapp.repository;

import com.sbear.firstapp.model.Holiday;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Look at previous commits for Revision of different concepts that have been removed for newer ones

@Repository
public interface HolidayRepository extends CrudRepository<Holiday, String> {

}
