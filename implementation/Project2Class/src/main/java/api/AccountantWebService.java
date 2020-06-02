package api;

import dataservicesPersistence.AccountantDAO;
import dataservicesPersistence.AccountantOrderDAO;
import dataservicesPersistence.DAOlite;
import entities.Accountant;
import entities.AccountantOrder;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;
import java.util.Optional;



@Path("myresource")
public class AccountantWebService {


    @Context
    UriInfo info;

    private DAOlite<Accountant, Integer> db = new AccountantDAO();

    /**
     * http://localhost:8080/webapi/myresource/accountant
     */

    @GET
    @Path("accountant")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Accountant> readAll() {
        Collection<Accountant> result = db.getAll();
        return result;
    }

    /**
     * http://localhost:8080/webapi/myresource/accountant/1
     */
    @GET
    @Path("accountant/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Integer id) {
        Optional<Accountant> result = db.get(id);

        if (result == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).entity(result).build();
        }
    }


    @POST
    @Path("accountant")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Accountant accountant) {
        System.out.println(accountant);
        Optional<Integer> result = db.save(accountant);
        System.out.println(result);
        if (result.isPresent()) {
            System.out.println("1");
            return Response.status(Response.Status.OK).build();
        } else {
            System.out.println("2");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}

