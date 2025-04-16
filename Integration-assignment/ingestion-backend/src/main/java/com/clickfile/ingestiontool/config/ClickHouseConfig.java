import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class ClickHouseConfig {

    private static final String URL = "jdbc:clickhouse://localhost:8123/default";
    private static final String USER = "default";
    private static final String PASSWORD = "";

    @Bean
    public Connection clickHouseConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
