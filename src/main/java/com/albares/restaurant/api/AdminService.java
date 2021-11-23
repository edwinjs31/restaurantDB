package com.albares.restaurant.api;

import com.albares.menuqr.domain.Dish;
import com.albares.restaurant.db.Db;
import com.albares.restaurant.utils.Response;
import com.albares.restaurant.utils.ResponseCodes;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@Path("/admin")
public class AdminService {

    @POST
    @Path("/addDish")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDish(Dish newDish) {
        Response r = new Response();
        try {
            Db myDb = new Db();
            myDb.connect();
            newDish.insertAndGetId_DB(myDb);
            myDb.disconnect();
            r.setResponseCode(ResponseCodes.OK);
        } catch (NoSuchAlgorithmException | SQLException e) {
            r.setResponseCode(ResponseCodes.ERROR);
        }
        return r;
    }
    
    
}
