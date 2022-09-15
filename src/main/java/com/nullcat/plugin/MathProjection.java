package com.nullcat.plugin;

import org.bukkit.plugin.java.JavaPlugin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class MathProjection extends JavaPlugin {

    private Database database;

    @Override
    public void onEnable() {
        // Plugin startup logic
        database = new Database();
        try {
            database.connect();

            //query insert
            PreparedStatement ps = database.getConnection().prepareStatement("INSERT INTO table (C1,C2,C3) VALUES (?,?,?);");
            ps.setString(1, "hey");
            ps.setString(2, "hey");
            ps.setString(3, "hey");
            ps.executeUpdate();

            //update: only changes first data for that person.
            PreparedStatement ps2 = database.getConnection().prepareStatement("UPDATE table SET column = ? WHERE column2 = ?;");
            ps2.setString(1, "hey");
            ps2.setString(2, "hey");
            ps2.executeUpdate();

            //delete a statement
            PreparedStatement ps3 = database.getConnection().prepareStatement("DELETE FROM table WHERE column = ?;");
            ps3.setString(1, "hey");

            //retrieve info (SELECT <column name / * >)
            PreparedStatement ps4 = database.getConnection().prepareStatement("SELECT X1,Y1,Z1 FROM table WHERE column = ?;");
            ResultSet rs = ps4.executeQuery();

            //next() moves to the next records.
            while(rs.next()){
                System.out.println(rs.getString("TYPE"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        database.disconnect();

    }
}
