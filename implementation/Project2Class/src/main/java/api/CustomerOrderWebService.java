package api;

import dataservicesPersistence.CustomerOrderDAO;
import dataservicesPersistence.DAOlite;
import entities.CustomerOrder;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;
import java.util.Optional;

@Path("myresource")
public class CustomerOrderWebService {


    @Context
    UriInfo info;

    private DAOlite<CustomerOrder, Integer> db = new CustomerOrderDAO();

    /**
     * http://localhost:8080/webapi/myresource/customerorder
     */

    @GET
    @Path("customerorder")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CustomerOrder> readAll() {
        Collection<CustomerOrder> result = db.getAll();
        return result;
    }

    /**
     * http://localhost:8080/webapi/myresource/customerorder/1
     */
    @GET
    @Path("customerorder/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Integer id) {
        Optional<CustomerOrder> result = db.get(id);

        if (result == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).entity(result).build();
        }
    }


    @POST
    @Path("customerorder")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(CustomerOrder order) {
        System.out.println(order);
        Optional<Integer> result = db.save(order);
        System.out.println(result);
        if (result.isPresent()) {
            System.out.println("1");
            return Response.status(Response.Status.OK).build();
        } else {
            System.out.println("2");
            return Response.status(Response.Status.GONE).build();
        }
    }

    @POST
    @Path("customerorder/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(CustomerOrder order) {
        System.out.println(order);
        db.update(order);
    }

    @POST
    @Path("customerorder/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(CustomerOrder order){
        System.out.println(order);
        db.delete(order);
    }
}


