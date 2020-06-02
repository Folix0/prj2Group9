package api;

import dataservicesPersistence.DAOlite;
import dataservicesPersistence.DriverDAO;
import dataservicesPersistence.TrailerDAO;
import entities.Driver;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;
import java.util.Optional;

@Path("myresource")
public class DriverWebService {

    @Context
    UriInfo info;
    private DAOlite<Driver, Integer> db = new DriverDAO();

    /**
     * http://localhost:8080/webapi/myresource/driver
     */

    @GET
    @Path("driver")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Driver> readAll() {
        Collection<Driver> result = db.getAll();
        return result;
    }

    /**
     * http://localhost:8080/webapi/myresource/driver/1
     */

    @GET
    @Path("driver/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Integer id) {
        Optional<Driver> result = db.get(id);

        if (result == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).entity(result).build();
        }
    }


    @POST
    @Path("driver")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Driver driver) {
        System.out.println(driver);
        Optional<Integer> result = db.save(driver);
        System.out.println(result);
        if (result.isPresent()) {
            System.out.println("1");
            return Response.status(Response.Status.OK).build();
        } else {
            System.out.println("2");
            return Response.status(Response.Status.GONE).build();
        }
    }
}

