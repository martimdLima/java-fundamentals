package com.martimlima.javaprojects.jdbc.src.persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASS = "";
    private static final String DEFAULT_HOST = "localhost";
    private static final String DEFAULT_DB = "projectsDB";
    private static final String UTC = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private static final String CONNECTOR = "jdbc:mysql:";

    private String dbUrl;
    private String user;
    private String pass;
    private Connection connection;

    public ConnectionManager(String user, String pass, String host, String database) {
        this.user = user;
        this.pass = pass;
        this.dbUrl = CONNECTOR + "//" + host + "/" + database + UTC;
    }

    public ConnectionManager() {
        this(DEFAULT_USER, DEFAULT_PASS, DEFAULT_HOST, DEFAULT_DB);
    }

    public Connection getConnection() {

        try {

            if (connection == null) {
                connection = DriverManager.getConnection(dbUrl, user, pass);
            }

        } catch (SQLException ex) {
            System.out.println("Failure to connect to database : " + ex.getMessage());

        }

        return connection;

    }

    public void close() {

        try {

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Failure to close database connection: " + ex.getMessage());
        }
    }
}
