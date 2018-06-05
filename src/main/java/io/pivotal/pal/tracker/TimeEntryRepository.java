package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {

    TimeEntry create(TimeEntry timeEntry);

    TimeEntry find(long num);

    List<TimeEntry> list();

    TimeEntry update(long num, TimeEntry timeEntry);

    void delete(long num);
}
