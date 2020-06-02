package api;

import dataservicesPersistence.DAOlite;
import dataservicesPersistence.TrailerDAO;
import entities.Trailer;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;
import java.util.Optional;


@Path("myresource")
public class TrailerWebService {

    @Context
    UriInfo info;
    private DAOlite<Trailer, Integer> db = new TrailerDAO();

    /**
     * http://localhost:8080/webapi/myresource/customerorder
     */

    @GET
    @Path("trailer")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Trailer> readAll() {
        Collection<Trailer> result = db.getAll();
        return result;
    }

    /**
     * http://localhost:8080/webapi/myresource/customerorder/1
     */

    @GET
    @Path("trailer/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Integer id) {
        Optional<Trailer> result = db.get(id);

        if (result == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).entity(result).build();
        }
    }


    @POST
    @Path("trailer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Trailer trailer) {
        System.out.println(trailer);
        Optional<Integer> result = db.save(trailer);
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

