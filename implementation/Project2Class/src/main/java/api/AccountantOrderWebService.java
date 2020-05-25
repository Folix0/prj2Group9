package api;

import dataservicesPersistence.DAOlite;
import dataservicesPersistence.JdbcAccountantOrderDao;
import entities.AccountantOrder;
/*
import services.AccountantOrderService;
/*
import dataservicesPersistence.AccountantOrderDAOlite;*/

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Path("myresource")
public class AccountantOrderWebService {

    @Context
    UriInfo info;

    private DAOlite<AccountantOrder,Integer> db = new JdbcAccountantOrderDao();

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
    @Path( "accountantorder/{orderid}" )
    @Produces( { MediaType.APPLICATION_JSON } )
    public Response find( @PathParam( "orderid" ) Integer orderId ) {
        Collection<AccountantOrder> result = db.getAll();

        if ( result == null ) {
            return Response.status( Response.Status.NOT_FOUND ).build();
        } else {
            return Response.status( Response.Status.OK ).entity( result ).build();
        }
    }

}



/*
@Path("myresource")
public class AccountantOrderWebService {

    @Context
    UriInfo info;

    private DAOlite<AccountantOrder> db = new AccountantOrderDAOlite("localhost");
    private AccountantOrderService accountantOrderService = new AccountantOrderService();

    /**
    * http://localhost:8080/webapi/myresource/accountantorder
    */
/*
    @GET
    @Path("accountantorder")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AccountantOrder> readAll() {
        List<AccountantOrder> result = db.getAll();
        return result;
    }

    /**
     * http://localhost:8080/webapi/myresource/accountantorder/1
     */
/*
    @GET
    @Path( "accountantorder/{orderid}" )
    @Produces( { MediaType.APPLICATION_JSON } )
    public Response find( @PathParam( "orderid" ) Integer orderId ) {

        AccountantOrder accountantOrder = new AccountantOrder( 1, 20000.0, "AStreet 2", 5912, "Badjas",
               LocalDate.now(), false, "pietjepuk@fontys.nl", 4568.45);


        if ( accountantOrder == null ) {
            return Response.status( Response.Status.NOT_FOUND ).build();
        } else {
            return Response.status( Response.Status.OK ).entity( accountantOrder ).build();
        }
    }

}*/