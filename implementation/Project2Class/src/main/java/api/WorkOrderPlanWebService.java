package api;

import dataservicesPersistence.CustomerOrderDAO;
import dataservicesPersistence.DAOlite;
import dataservicesPersistence.WorkOrderPlanDAO;
import entities.CustomerOrder;
import entities.WorkOrderPlan;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;
import java.util.Optional;

@Path("myresource")
public class WorkOrderPlanWebService {

    @Context
    UriInfo info;

    private DAOlite<WorkOrderPlan, Integer> db = new WorkOrderPlanDAO();


    /**
     * http://localhost:8080/webapi/myresource/workorderplan
     */
    //from db back to frontend
    @GET
    @Path("workorderplan")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<WorkOrderPlan> readAll() {
        Collection<WorkOrderPlan> result = db.getAll();
        return result;
    }

    /**
     * http://localhost:8080/webapi/myresource/workorderplan/1
     */
    @GET
    @Path("workorderplan/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Integer id) {
        Optional<WorkOrderPlan> result = db.get(id);

        if (result == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).entity(result).build();
        }
    }




    @POST
    @Path("workorderplan")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(WorkOrderPlan workOrderPlan) {
        System.out.println(workOrderPlan);
        Optional<Integer> result = db.save(workOrderPlan);
        System.out.println(result);
        if (result.isPresent()) {
            System.out.println("1");
            return Response.status(Response.Status.OK).build();
        } else {
            System.out.println("2");

            //is gone the correct status? -> says that the requested resource is no longer available on
            //the server; resource is intentioanally unavaiable
            return Response.status(Response.Status.GONE).build();
        }
    }
}



