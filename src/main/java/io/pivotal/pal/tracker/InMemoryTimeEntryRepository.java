package io.pivotal.pal.tracker;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.List;

@ComponentScan
public class InMemoryTimeEntryRepository implements TimeEntryRepository{
    private List<TimeEntry> timeEntryList;

    public InMemoryTimeEntryRepository(){

    }

    public InMemoryTimeEntryRepository(TimeEntryRepository timeEntryRepository) {
    }

    public TimeEntry create(TimeEntry timeEntry){
        timeEntryList.add(timeEntry);
        return timeEntry;
    }

    public TimeEntry find(long num){
        return new TimeEntry();
    }

    public TimeEntry update(TimeEntry oldTimeEntry, TimeEntry newTimeEntry){
        return newTimeEntry;
    }

    public TimeEntry update(long timeEntryId, TimeEntry newTimeEntry){
        return newTimeEntry;
    }

    public void delete(long num){
    }

    public List<TimeEntry> list(){
        return timeEntryList;
    }

}
