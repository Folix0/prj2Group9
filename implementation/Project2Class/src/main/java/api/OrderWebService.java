package api;
/*
import dataservicesPersistence.DAOlite;
import dataservicesPersistence.OrderDAO;
import entities.Order;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;
import java.util.Optional;

@Path("myresource")
public class OrderWebService {
    @Context
    UriInfo info;

    private DAOlite<Order, Integer> db = new OrderDAO();
*/
    /**
     * http://localhost:8080/webapi/myresource/aorder
     */
/*
    @GET
    @Path("aorder")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Order> readAll() {
        Collection<Order> result = db.getAll();
        return result;
    }
*/
    /**
     * http://localhost:8080/webapi/myresource/aorder/1
     */
/*
    @GET
    @Path("aorder/{orderid}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("orderid") Integer id) {
        Optional<Order> result = db.get(id);

        if (result == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).entity(result).build();
        }
    }


    @POST
    @Path("aorder")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Order order) {
        System.out.println(order);
        Optional<Integer> result = db.save(order);
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
*/
