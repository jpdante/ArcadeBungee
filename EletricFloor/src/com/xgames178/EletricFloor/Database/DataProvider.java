package com.xgames178.EletricFloor.Database;

import com.xgames178.EletricFloor.Player.ProfileData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

/**
 * Created by jpdante on 02/05/2017.
 */
public class DataProvider {
    private Database database;
    private Statement statement;

    public DataProvider(Database database) {
        this.database = database;
        try {
            this.statement = database.connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ProfileData getProfileData(UUID uuid) {
        ResultSet res = null;
        try {
            res = this.statement.executeQuery("SELECT * FROM `arcade_players` WHERE `uuid`= '" + uuid.toString() + "';");
            ProfileData profileData = new ProfileData();
            if(res.next()) {
                profileData.uuid = uuid;
                profileData.vip = res.getInt("vip");
                profileData.current_particle = res.getInt("currentparticle");
            } else {
                //this.statement.executeUpdate("INSERT INTO `arcade_players` (`uuid`, `vip`) VALUES ('" + uuid.toString() + "', '0');");
                profileData.uuid = uuid;
                profileData.vip = 0;
            }
            return profileData;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int setProfileData(ProfileData profileData) {
        try {
            this.statement.executeUpdate("UPDATE `arcade_players` SET `currentparticle`='" + profileData.current_particle + "' WHERE `uuid`='" + profileData.uuid.toString() + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
