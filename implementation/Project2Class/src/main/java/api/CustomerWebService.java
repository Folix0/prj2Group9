package api;
/*
import dataservicesPersistence.AccountantOrderDAO;
import dataservicesPersistence.CustomerDAO;
import dataservicesPersistence.DAOlite;
import entities.AccountantOrder;
import entities.Customer;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;
import java.util.Optional;

@Path("myresource")
public class CustomerWebService {
    @Context
    UriInfo info;

    private DAOlite<Customer, Integer> db = new CustomerDAO();

    /**
     * http://localhost:8080/webapi/myresource/customer
     */
/*
    @GET
    @Path("customer")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Customer> readAll() {
        Collection<Customer> result = db.getAll();
        return result;
    }*/

    /**
     * http://localhost:8080/webapi/myresource/customer/1
     *//*
    @GET
    @Path("customer/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Integer id) {
        Optional<Customer> result = db.get(id);

        if (result == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).entity(result).build();
        }
    }*/

/*
    @POST
    @Path("customer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Customer customer) {
        System.out.println(customer);
        Optional<Integer> result = db.save(customer);
        System.out.println(result);
        if(result.isPresent()){
            System.out.println("1");
            return Response.status(Response.Status.OK).build();
        }else {
            System.out.println("2");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}*/
