package api;

import dataservicesPersistence.DAOlite;
import dataservicesPersistence.AccountantOrderDAO;
import entities.AccountantOrder;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;
import java.util.Optional;



@Path("myresource")
public class AccountantOrderWebService {

    @Context
    UriInfo info;

    private DAOlite<AccountantOrder, Integer> db = new AccountantOrderDAO();

    /**
     * http://localhost:8080/webapi/myresource/accountantorder
     */

    @GET
    @Path("accountantorder")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<AccountantOrder> readAll() {
        Collection<AccountantOrder> result = db.getAll();
        return result;
    }

    /**
     * http://localhost:8080/webapi/myresource/accountantorder/1
     */
    @GET
    @Path("accountantorder/{orderid}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("orderid") Integer orderId) {
        Optional<AccountantOrder> result = db.get(orderId);

        if (result == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).entity(result).build();
        }
    }


    @POST
    @Path("accountantorder")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(AccountantOrder accountantOrder) {
        System.out.println(accountantOrder);
        Optional<Integer> result = db.save(accountantOrder);
        System.out.println(result);
        if(result.isPresent()){
            System.out.println("1");
            return Response.status(Response.Status.OK).build();
        }else {
            System.out.println("2");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
