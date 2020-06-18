/*package dataservicesPersistence;

import api.WorkOrderPlanWebService;
import entities.WorkOrderPlan;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

import entities.CustomerOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * class in order to test the implementation of the dao
 * <p>
 * use mockito framework -> otherwise integrate real db or write much code
 * implementing all the interfaces
 * -> simulate db and interfaces
 * <p>
 * test all CRUD methods
 */

//annotation attaches a runner with the test class
// to initialize the test data
/*@RunWith(MockitoJUnitRunner.class)
public class WorkOrderPlanDAOTest {


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

    private WorkOrderPlan workOrderPlan;


    //create and inject the mock object
    @InjectMocks
    WorkOrderPlanDAO workOrderPlanDAO = new WorkOrderPlanDAO();


    @Before
    public void setUp() throws Exception {
        workOrderPlanDAO = mock(WorkOrderPlanDAO.class);
        when(connection.createStatement()).thenReturn(statement);
        when(dataSource.getConnection()).thenReturn(connection);
    }


    /**
     * test the method get with a specific id,
     * i.e. retrieve a workorder plan by id
     */
 /*   @Test
    public void get() throws SQLException {
        workOrderPlan = new WorkOrderPlan(1, 1, "RTZ6",
                "ASD2", "REW3", 33000.00,
                true, true, "Road2",
                "1234", LocalDate.of(2020, 06, 28));


        //Mockito.when(resultSet.getInt(workOrderPlan.getId())).thenReturn(1);
        Mockito.when(resultSet.getInt(workOrderPlan.getOrderId())).thenReturn(1);
        Mockito.when(resultSet.getInt(workOrderPlan.getDriverId())).thenReturn(1);
        Mockito.when(resultSet.getString(workOrderPlan.getTrailerLicencePlate()))
                .thenReturn("RTZ6");
        Mockito.when(resultSet.getString(workOrderPlan.getTruckLicencePlate()))
                .thenReturn("ASD2");
        Mockito.when(resultSet.getString(workOrderPlan.getTrailerPickupLocation()))
                .thenReturn("REW3");

        Mockito.when(resultSet.getDouble(String.valueOf(workOrderPlan.getLoadAmount()))).thenReturn(33000.00);
        Mockito.when(resultSet.getBoolean(String.valueOf(workOrderPlan.isHazardousLiquid())))
                .thenReturn(true);
        Mockito.when(resultSet.getBoolean(String.valueOf(workOrderPlan.isTrailerCleaned())))
                .thenReturn(true);

        Mockito.when(resultSet.getString(workOrderPlan.getDeliveryAddress()))
                        .thenReturn("Road2");
        Mockito.when(resultSet.getString(workOrderPlan.getDeliveryPostcode())).
                thenReturn("1234");

        Mockito.when(resultSet.getDate(String.valueOf(workOrderPlan.getDeliveryDate())))
                .thenReturn(Date.valueOf(LocalDate.of(2020, 06, 28)));

        Optional<WorkOrderPlan> workOrderPlanOptional = Optional.of(workOrderPlan);
        assertTrue(workOrderPlanOptional.isPresent());

        Assert.assertEquals(1, workOrderPlan.getOrderId());
        Assert.assertEquals(1, workOrderPlan.getDriverId());
        Assert.assertEquals("RTZ6", workOrderPlan.getTrailerLicencePlate());
        Assert.assertEquals("ASD2", workOrderPlan.getTruckLicencePlate());
        Assert.assertEquals("REW3", workOrderPlan.getTrailerPickupLocation());
        //Assert.assertEquals(33000.00, workOrderPlan.getLoadAmount());
        assertThat(workOrderPlan.getLoadAmount()).isEqualTo(33000.00);
        Assert.assertTrue(workOrderPlan.isHazardousLiquid());
        Assert.assertTrue(workOrderPlan.isTrailerCleaned());
        Assert.assertEquals("Road2", workOrderPlan.getDeliveryAddress());
        Assert.assertEquals("1234", workOrderPlan.getDeliveryPostcode());
        Assert.assertEquals(LocalDate.of(2020, 06, 28), workOrderPlan.getDeliveryDate());

        //Stream<WorkOrderPlan> stream = Stream.of()
        //when the result is not empty the sql stmt should be executed

       /* workOrderPlanDAO.get(workOrderPlan.getOrderId()).isPresent();
        OngoingStubbing<Boolean> booleanOngoingStubbing =
                when(workOrderPlanDAO.get(workOrderPlan.getOrderId()).isPresent())
                .thenReturn(resultSet.getInt(workOrderPlan.getOrderId()));*/

 /*   }

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