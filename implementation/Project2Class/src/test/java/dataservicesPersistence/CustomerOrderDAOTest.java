/*package dataservicesPersistence;

import entities.CustomerOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * class in order to test the implementation of the dao
 *
 * use mockito framework -> otherwise integrate real db or write much code
 * implementing all the interfaces
 * -> simulate db and interfaces
 *
 * test all CRUD methods
 */

//annotation attaches a runner with the test class
// to initialize the test data
/*@RunWith(MockitoJUnitRunner.class)
public class CustomerOrderDAOTest {

    //Mocktio -> produce dummy objects, operations, and results
    // deals with no real database connections
    //work on e.g. servers, database connections

    @Mock
    private Connection connection;

    @Mock
    private Statement statement;

    @Mock
    private DataSource dataSource;

    @Mock
    private ResultSet resultSet;

    private CustomerOrder customerOrder;


    //create and inject the mock object
    @InjectMocks
    CustomerOrderDAO customerOrderDAO = new CustomerOrderDAO();



    @Before
    public void setUp() throws Exception {
        customerOrderDAO = mock(CustomerOrderDAO.class);
    }

    @Test
    public void get() {
        when(connection.createStatement(any(String.class))).thenReturn(statement);
    }

    @Test
    public void getAll() {
    }

    @Test
    public void save() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}*/