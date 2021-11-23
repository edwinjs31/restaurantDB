package com.albares.menuqr.domain;

import com.albares.restaurant.db.Db;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dish {

    private Integer id;
    private String name;
    private Integer price;
    private Integer type;

    public Dish() {
    }

    public Dish(Integer id, String name, Integer price, Integer type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    //INSERT
    public int insertAndGetId_DB(Db myDb) throws SQLException, NoSuchAlgorithmException {
        PreparedStatement ps = myDb.prepareStatement(
                "INSERT INTO dishes (name, price, type) VALUES (?, ?, ?) returning id;");

        ps.setString(1, this.getName());
        ps.setInt(2, this.getPrice());
        ps.setInt(3, this.getType());
        ResultSet rs = myDb.executeQuery(ps);
        rs.next();
        this.setId(rs.getInt(1));
        return this.getId();
    }

    //SELECT
    public Menu select_DB(Db myDb) throws SQLException {
        Menu menu = new Menu();
        PreparedStatement ps = myDb.prepareStatement("SELECT id, name, price, type FROM dishes WHERE type=?;");
        ps.setInt(1, this.getType());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            this.setId(rs.getInt("id"));
            this.setName(rs.getString("name"));
            this.setPrice(rs.getInt("price"));
            this.setType(rs.getInt("type"));
            Dish newDish = new Dish(this.getId(), this.getName(), this.getPrice(), this.getType());
            menu.addDish(newDish);

        }
        return menu;
    }

    /*
    public void update_DB(Db myDb) throws SQLException {

        PreparedStatement ps = myDb.prepareStatement(
                "UPDATE dishes SET name = ?, price=?, type=? WHERE id = ?;");

        ps.setString(1, this.getName());
        ps.setInt(2, this.price);
        ps.setInt(3, this.getType());
        ps.setInt(4, this.getId());
        ps.executeUpdate();
    }

    public void delete_DB(Db myDb) throws SQLException {

        PreparedStatement ps = myDb.prepareStatement("DELETE FROM dishes WHERE id = ?;");
        ps.setInt(1, this.getId());
        ps.executeUpdate();
    }
    */

}
