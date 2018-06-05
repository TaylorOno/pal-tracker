package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class TimeEntryController {
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(TimeEntry timeEntry) {
        ResponseEntity response = new ResponseEntity(HttpStatus.ACCEPTED);
        return  response;
    }
    @GetMapping("/time-entries/{num}")
    @RequestMapping()
    public ResponseEntity read(@PathVariable("num") long num) {
        ResponseEntity response = new ResponseEntity(HttpStatus.ACCEPTED);
        return  response;
    }
    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list(){
        ResponseEntity response = new ResponseEntity(HttpStatus.ACCEPTED);
        return  response;
    }
    @PutMapping("time-entries/{num}" )
    public ResponseEntity update(@PathVariable("num") long num, TimeEntry timeEntry){
        ResponseEntity response = new ResponseEntity(HttpStatus.ACCEPTED);
        return  response;
    }
    @DeleteMapping("/time-entries/{num}")
    public ResponseEntity delete(@PathVariable("num") long num){
        ResponseEntity response = new ResponseEntity(HttpStatus.ACCEPTED);
        return  response;
    }
}
