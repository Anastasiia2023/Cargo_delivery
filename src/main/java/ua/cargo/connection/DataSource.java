package ua.cargo.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static ua.cargo.connection.ConnectionConstants.*;


public class DataSource {
    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;

    private static final Logger logger = LogManager.getLogger(DataSource.class);

    static {
        Properties properties = getProperties();
        config.setJdbcUrl(properties.getProperty(URL_PROPERTY));
        config.setUsername(properties.getProperty(USER_NAME));
        config.setPassword(properties.getProperty(PASSWORD));
        config.setDriverClassName(properties.getProperty(DRIVER));
        config.addDataSourceProperty(CACHE_PREPARED_STATEMENT, properties.getProperty(CACHE_PREPARED_STATEMENT));
        config.addDataSourceProperty(CACHE_SIZE, properties.getProperty(CACHE_SIZE));
        config.addDataSourceProperty(CACHE_SQL_LIMIT, properties.getProperty(CACHE_SQL_LIMIT));
        ds = new HikariDataSource(config);
    }

    private DataSource() {}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        String connectionFile = "connection.properties";
        try (InputStream resource = DataSource.class.getClassLoader().getResourceAsStream(connectionFile)){
            properties.load(resource);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return properties;
    }
}