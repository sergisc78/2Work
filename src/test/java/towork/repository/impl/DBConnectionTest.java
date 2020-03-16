package towork.repository.impl;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionTest {
    Dbconnection dBConnection;
    Connection connection;
    private String connectionProperties = "db_test.properties";

    @Before
    public void setUp(){
        dBConnection = new Dbconnection(connectionProperties);
    }

    @After
    public void cleanUp() throws SQLException {
        if(connection != null){
            connection.close();   
        }        
    }

    @Test
    public void dbConnection() throws IOException, SQLException {   
        connection = dBConnection.getConnection();
        Assert.assertEquals("H2 JDBC Driver", connection.getMetaData().getDriverName());
        Assert.assertEquals("TOWORK_DB", connection.getCatalog());
    }
    
    @Test
    public void dbConnectionWrongDriver() throws IOException, SQLException {  
        dBConnection = new Dbconnection("db_wrong_driver.properties");
        connection = dBConnection.getConnection();
        Assert.assertNull(connection);
    }
}
