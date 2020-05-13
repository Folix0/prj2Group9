package org.prj2.services;
import org.prj2.dataservices.DAOLite;
import org.prj2.dataservices.PostgresDAOLite;
import org.prj2.model.AccountantOrder;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AccOrderService {
    private DAOLite<AccountantOrder> db = new PostgresDAOLite("localhost");


    public AccountantOrder getAccountantOrderById(Integer orderId) {
        AccountantOrder accOrder = db.get(orderId);
        return accOrder;

    }

    public List<AccountantOrder> getAllAccountantOrders() {

        AccountantOrder p1 = new AccountantOrder(1, 200.5, "test", 4577, "sdfgh", Date.valueOf("2002.12.12"), true, "sdfgzhu", 25.25);
        AccountantOrder p2 = new AccountantOrder(2, 200.5, "test", 4577, "sdfgh", Date.valueOf("2000.12.12"), true, "sdfgzhu", 25.25);

        List<AccountantOrder> accountantorders = new ArrayList<>();
        accountantorders.add(p1);
        accountantorders.add(p2);

        return accountantorders;

    }
}


