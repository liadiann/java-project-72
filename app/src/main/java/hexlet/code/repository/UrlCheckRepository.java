package hexlet.code.repository;

import hexlet.code.model.UrlCheck;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UrlCheckRepository extends BaseRepository {
    public static void save(UrlCheck urlCheck) throws SQLException {
        var sql = "INSERT INTO url_checks (url_id, status_code, h1, title, description, created_at) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (var conn = dataSource.getConnection();
            var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, urlCheck.getUrlId());
            preparedStatement.setInt(2, urlCheck.getStatusCode());
            preparedStatement.setString(3, urlCheck.getH1());
            preparedStatement.setString(4, urlCheck.getTitle());
            preparedStatement.setString(5, urlCheck.getDescription());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                urlCheck.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static List<UrlCheck> findUrlChecksForUrlId(Integer urlId) throws SQLException {
        var sql = "SELECT * FROM url_checks WHERE url_id = ?";
        try (var conn = dataSource.getConnection();
            var statement = conn.prepareStatement(sql)) {
            statement.setInt(1, urlId);
            var resultSet = statement.executeQuery();
            var result = new ArrayList<UrlCheck>();
            while (resultSet.next()) {
                var urlCheck = new UrlCheck(urlId);
                var id = resultSet.getInt("id");
                var statusCode = resultSet.getInt("status_code");
                var title = resultSet.getString("title");
                var h1 = resultSet.getString("h1");
                var description = resultSet.getString("description");
                var createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                urlCheck.setId(id);
                urlCheck.setStatusCode(statusCode);
                urlCheck.setTitle(title);
                urlCheck.setH1(h1);
                urlCheck.setDescription(description);
                urlCheck.setCreatedAt(createdAt);
                result.add(urlCheck);
            }
            return result;
        }
    }

    public static Map<Integer, UrlCheck> findChecksWithLastCreatedAt() throws SQLException {
        var sql = "SELECT url_id, MAX(created_at), MAX(status_code) FROM url_checks GROUP BY url_id";
        try (var conn = dataSource.getConnection();
            var statement = conn.prepareStatement(sql)) {
            var resultSet = statement.executeQuery();
            var result = new HashMap<Integer, UrlCheck>();
            while (resultSet.next()) {
                var createdAt = resultSet.getTimestamp("MAX(created_at)").toLocalDateTime();
                var statusCode = resultSet.getInt("MAX(status_code)");
                var urlId = resultSet.getInt("url_id");
                var urlCheck = new UrlCheck(urlId);
                urlCheck.setCreatedAt(createdAt);
                urlCheck.setStatusCode(statusCode);
                result.put(urlId, urlCheck);
            }
            return result;
        }
    }
}
