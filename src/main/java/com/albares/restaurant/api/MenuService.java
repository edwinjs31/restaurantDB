package com.albares.restaurant.api;

import com.albares.menuqr.domain.Dish;
import com.albares.menuqr.domain.Menu;
import com.albares.restaurant.db.Db;
import com.albares.restaurant.utils.Response;
import com.albares.restaurant.utils.ResponseCodes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.sql.SQLException;

@Path("/menu")
public class MenuService {

    @GET
    @Path("/{QR}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDishes(@PathParam("QR") Integer QR) {
        Response r = new Response();
        Dish dish = new Dish();
        try {
            dish.setType(QR);
            Db myDb = new Db();

            myDb.connect();
            Menu menu = dish.select_DB(myDb);
            myDb.disconnect();
            r.setDishes(menu.getDishes());
            r.setResponseCode(ResponseCodes.OK);
        } catch (SQLException e) {
            r.setResponseCode(ResponseCodes.ERROR);

        }
        return r;
    }
}
