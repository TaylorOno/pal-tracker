package io.pivotal.pal.tracker;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class JdbcTimeEntryRepository implements TimeEntryRepository {
    JdbcTemplate myJdbcTemplate;

    public JdbcTimeEntryRepository(DataSource dataSource) {
        this.myJdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        /*
        myJdbcTemplate.update("INSERT INTO time_entries (id, project_id, user_id, date, hours) VALUES (?,?,?,?,?)",
                generatedKeyHolder.getKey(),
                timeEntry.getProjectId(),
                timeEntry.getUserId() ,
                Date.valueOf(timeEntry.getDate()),
                timeEntry.getHours());
        return find(generatedKeyHolder.getKey().longValue());*/

        myJdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO time_entries (project_id, user_id, date, hours) " +
                            "VALUES (?, ?, ?, ?)",
                    RETURN_GENERATED_KEYS
            );

            statement.setLong(1, timeEntry.getProjectId());
            statement.setLong(2, timeEntry.getUserId());
            statement.setDate(3, Date.valueOf(timeEntry.getDate()));
            statement.setInt(4, timeEntry.getHours());

            return statement;
        }, generatedKeyHolder);
        return find(generatedKeyHolder.getKey().longValue());
    }

    @Override
    public TimeEntry find(long num) {
        return myJdbcTemplate.query("Select * from time_entries where id = ?", new Object[]{num}, extractor);
    }

    @Override
    public List<TimeEntry> list() {
        return (List)myJdbcTemplate.query("Select * from time_entries", mapper);
    }

    @Override
    public TimeEntry update(long num, TimeEntry timeEntry) {
        myJdbcTemplate.update("Update time_entries set project_id=?,  user_id=?, date=?, hours=? where id=?",
                timeEntry.getProjectId(),
                timeEntry.getUserId() ,
                Date.valueOf(timeEntry.getDate()),
                timeEntry.getHours(),
                num);
        return find(num);
    }

    @Override
    public void delete(long num) {
        myJdbcTemplate.update("Delete from time_entries where id=?", num);
    }

    private final RowMapper<TimeEntry> mapper = (rs, rowNum) -> new TimeEntry(
            rs.getLong("id"),
            rs.getLong("project_id"),
            rs.getLong("user_id"),
            rs.getDate("date").toLocalDate(),
            rs.getInt("hours")
    );

    private final ResultSetExtractor<TimeEntry> extractor =
            (rs) -> rs.next() ? mapper.mapRow(rs, 1) : null;
}
