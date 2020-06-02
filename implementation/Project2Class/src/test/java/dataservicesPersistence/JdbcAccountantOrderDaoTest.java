package dataservicesPersistence;

import entities.AccountantOrder;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

public class JdbcAccountantOrderDaoTest {

    @Test
    public void get() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void save() {
        AccountantOrderDAO dao = new AccountantOrderDAO();
        Optional<Integer> save = dao.save(new AccountantOrder(3, 3, 3000.00, "Street123", 123456,
                "Pickup123", LocalDate.parse("2002-12-12"), true, "D@test", 250.00));
        assertTrue(save.isPresent());

    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}