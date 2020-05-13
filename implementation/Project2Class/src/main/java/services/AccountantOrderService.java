/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import businessLogic.AccountantOrder;
import dataservicesPersistence.AccountantOrderDAOlite;
import dataservicesPersistence.DAOlite;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class AccountantOrderService {

    private DAOlite<AccountantOrder> db = new AccountantOrderDAOlite("localhost");


    public AccountantOrder getAccountantOrderById(Integer orderId) {
        // get presidents from the real database! Note, no null check!
        AccountantOrder accountantOrder = db.get(orderId);
        return accountantOrder;

    }

    public List<AccountantOrder> getAllAccountantOrders() {
        // usage without database
        AccountantOrder p1 = new AccountantOrder(1, 200.5, "test", 4577, "sdfgh", Date.valueOf("2002.12.12"), true, "sdfgzhu", 25.25);
        AccountantOrder p2 = new AccountantOrder(2, 200.5, "test", 4577, "sdfgh", Date.valueOf("2000.12.12"), true, "sdfgzhu", 25.25);

        List<AccountantOrder> accountantorders = new ArrayList<>();
        accountantorders.add(p1);
        accountantorders.add(p2);

        return accountantorders;

    }
}


