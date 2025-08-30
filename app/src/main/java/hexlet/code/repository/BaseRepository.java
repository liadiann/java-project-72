package hexlet.code.repository;

import com.zaxxer.hikari.HikariDataSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseRepository {
    public static HikariDataSource dataSource;

    public static String formatTime(LocalDateTime time) {
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return time.format(formatter);
    }
}
