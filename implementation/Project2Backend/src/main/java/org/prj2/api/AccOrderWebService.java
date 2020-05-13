package org.prj2.api;
import org.prj2.dataservices.DAOLite;

import org.prj2.dataservices.PostgresDAOLite;
import org.prj2.model.AccountantOrder;
import org.prj2.services.AccOrderService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;



@Path("myresource")
public class AccOrderWebService {

    @Context
    UriInfo info;

    private DAOLite<AccountantOrder> db = new PostgresDAOLite("localhost");
    private AccOrderService accountantOrderService = new AccOrderService();

    /**
     * http://localhost:8080/webapi/myresource/accountantorder
     */

    @GET
    @Path("accountantorder")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AccountantOrder> readAll() {
        List<AccountantOrder> result = new ArrayList<>();

        result.add(new AccountantOrder(1, 3000.00 , "Street123", 123456,
                "Pickup123", Date.valueOf("2002.12.12"),true, "D@test", 250.00));

        return result;
    }

    /**
     * http://localhost:8080/webapi/myresource/accountantorder/1
     */

    @GET
    @Path("accountantorder/{orderid}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("orderid") Integer orderId) {
        AccountantOrder accountantOrder = accountantOrderService.getAccountantOrderById(orderId);
        if (accountantOrder==null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.status(Response.Status.OK).entity(accountantOrder).build();
    }
}

