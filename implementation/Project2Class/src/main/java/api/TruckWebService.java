package api;

import dataservicesPersistence.DAOlite;
import dataservicesPersistence.TrailerDAO;
import dataservicesPersistence.TruckDAO;
import entities.Trailer;
import entities.Truck;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;
import java.util.Optional;


@Path("myresource")
public class TruckWebService {

        @Context
        UriInfo info;
        private DAOlite<Truck, Integer> db = new TruckDAO();

        /**
         * http://localhost:8080/webapi/myresource/truck
         */

        @GET
        @Path("truck")
        @Produces(MediaType.APPLICATION_JSON)
        public Collection<Truck> readAll() {
            Collection<Truck> result = db.getAll();
            return result;
        }

        /**
         * http://localhost:8080/webapi/myresource/truck/1
         */

        @GET
        @Path("truck/{id}")
        @Produces({MediaType.APPLICATION_JSON})
        public Response find(@PathParam("id") Integer id) {
            Optional<Truck> result = db.get(id);

            if (result == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            } else {
                return Response.status(Response.Status.OK).entity(result).build();
            }
        }


        @POST
        @Path("truck")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response save(Truck truck) {
            System.out.println(truck);
            Optional<Integer> result = db.save(truck);
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


