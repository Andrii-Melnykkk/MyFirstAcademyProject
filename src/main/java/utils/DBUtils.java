package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;


public class DBUtils {

    private static Connection dbConnection = null;
    private static final Logger LOGGER = Logger.getLogger(DBUtils.class);

    private DBUtils() {
    }

    public static Connection getConnection() {
        if (dbConnection != null) {
            return dbConnection;
        } else {

            try {
                InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("database.properties");
                Properties properties = new Properties();
                assert inputStream != null;
                properties.load(inputStream);
                String connectionURL = properties.getProperty("connectionURL");
                String userName = properties.getProperty("userName");
                String password = properties.getProperty("password");
                String dbDriver = properties.getProperty("dbDriver");

                Class.forName(dbDriver);
                dbConnection = DriverManager.getConnection(connectionURL, userName, password);
            } catch (IOException e) {
                LOGGER.error("Fail to load DB properties", e);

            } catch (ClassNotFoundException e) {
                LOGGER.error("PostgreSQL JDBC Driver is not found", e);
            } catch (SQLException e) {
                LOGGER.error("Connection Failed", e);
            }
            return dbConnection;
        }
    }

    public static void closeQuietly(Connection connection) {
        try {
            connection.close();
        } catch (Exception e) {
            LOGGER.error("Fail to close connection", e);

        }
    }

    public static void rollbackQuietly(Connection connection) {
        try {
            connection.rollback();
        } catch (Exception e) {
            LOGGER.error("Fail to rollback connection", e);
        }
    }

}




