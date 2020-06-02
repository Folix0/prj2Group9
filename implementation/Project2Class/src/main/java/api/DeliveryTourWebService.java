package api;

import dataservicesPersistence.AccountantDAO;
import dataservicesPersistence.DAOlite;
import dataservicesPersistence.DeliveryTourDAO;
import entities.Accountant;
import entities.DeliveryTour;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;
import java.util.Optional;


@Path("myresource")
public class DeliveryTourWebService {

        @Context
        UriInfo info;

        private DAOlite<DeliveryTour, Integer> db = new DeliveryTourDAO();

        /**
         * http://localhost:8080/webapi/myresource/deliverytour
         */

        @GET
        @Path("deliverytour")
        @Produces(MediaType.APPLICATION_JSON)
        public Collection<DeliveryTour> readAll() {
            Collection<DeliveryTour> result = db.getAll();
            return result;
        }

        /**
         * http://localhost:8080/webapi/myresource/deliverytour/1
         */
        @GET
        @Path("deliverytour/{id}")
        @Produces({MediaType.APPLICATION_JSON})
        public Response find(@PathParam("id") Integer id) {
            Optional<DeliveryTour> result = db.get(id);

            if (result == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            } else {
                return Response.status(Response.Status.OK).entity(result).build();
            }
        }


        @POST
        @Path("deliverytour")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response save(DeliveryTour deliveryTour) {
            System.out.println(deliveryTour);
            Optional<Integer> result = db.save(deliveryTour);
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

