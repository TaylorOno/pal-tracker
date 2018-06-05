package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class TimeEntryController {
    TimeEntryRepository timeEntryRepository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;

    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntry) {

        TimeEntry timeEntryCreated = timeEntryRepository.create(timeEntry);
        ResponseEntity<TimeEntry> response = new ResponseEntity(timeEntryCreated, HttpStatus.CREATED);
        return  response;
    }
    @GetMapping("/time-entries/{num}")
     public ResponseEntity read(@PathVariable("num") long num) {
        TimeEntry timeEntry = timeEntryRepository.find(num);
        ResponseEntity<TimeEntry>  response;
        if(timeEntry == null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
            return response;
        }
        response = new ResponseEntity(timeEntry, HttpStatus.OK);
        return  response;
    }
    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list(){

        List<TimeEntry> timeEntries = timeEntryRepository.list();
        ResponseEntity<List<TimeEntry>> response = new ResponseEntity<>(timeEntries,HttpStatus.OK);
        return  response;
    }
    @PutMapping("time-entries/{num}" )
    public ResponseEntity update(@PathVariable("num") long num, @RequestBody TimeEntry timeEntry){
        ResponseEntity<TimeEntry> response;
        TimeEntry timeEntryUpdated = timeEntryRepository.update(num, timeEntry);
        if(timeEntryUpdated == null) {
            response = new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        else
        {
            response = new ResponseEntity(timeEntryUpdated, HttpStatus.OK);
        }
        return  response;
    }
    @DeleteMapping("/time-entries/{num}")
    public ResponseEntity delete(@PathVariable("num") long num){
        timeEntryRepository.delete(num);
        ResponseEntity response = new ResponseEntity(HttpStatus.NO_CONTENT);
        return  response;
    }
}
