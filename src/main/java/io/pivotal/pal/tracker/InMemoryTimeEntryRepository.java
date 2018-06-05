package io.pivotal.pal.tracker;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ComponentScan
public class InMemoryTimeEntryRepository implements TimeEntryRepository{
    private Map<Long, TimeEntry> timeEntryMap = new HashMap<>();
    private long counter=1;


    public InMemoryTimeEntryRepository(){

    }

    public InMemoryTimeEntryRepository(TimeEntryRepository timeEntryRepository) {
    }

    public TimeEntry create(TimeEntry timeEntry){
        //timeEntryMap.put(timeEntry.getId(), timeEntry);
        timeEntry.setId(counter);
        timeEntryMap.put(counter, timeEntry);
        counter++;
        return timeEntry;
    }

    public TimeEntry find(long num){
        return timeEntryMap.get(num);
    }

    public TimeEntry update(TimeEntry oldTimeEntry, TimeEntry newTimeEntry){
        timeEntryMap.put(oldTimeEntry.getId(),newTimeEntry);
        return newTimeEntry;
    }

    public TimeEntry update(long timeEntryId, TimeEntry newTimeEntry){
        TimeEntry timeEntryFound = timeEntryMap.get(timeEntryId);
        if(timeEntryFound == null)
            return null;
        newTimeEntry.setId(timeEntryId);
        timeEntryMap.put(timeEntryId, newTimeEntry);
        return newTimeEntry;
    }

    public void delete(long num){
        timeEntryMap.remove(num);
    }

    public List<TimeEntry> list(){
        System.out.println("timeEntryMap size: " +timeEntryMap.size());
        return new ArrayList<TimeEntry>(timeEntryMap.values());
    }

}
