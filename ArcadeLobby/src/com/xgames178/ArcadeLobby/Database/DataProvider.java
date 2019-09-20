package com.xgames178.ArcadeLobby.Database;

import com.xgames178.ArcadeLobby.Player.ProfileData;
import org.bukkit.Bukkit;

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
                res = this.statement.executeQuery("SELECT * FROM `arcade_playerparticles` WHERE `uuid`= '" + uuid.toString() + "';");
                res.next();
                profileData.particle_1 = res.getBoolean("p1");
                profileData.particle_2 = res.getBoolean("p2");
                profileData.particle_3 = res.getBoolean("p3");
                profileData.particle_4 = res.getBoolean("p4");
                profileData.particle_5 = res.getBoolean("p5");
            } else {
                this.statement.executeUpdate("INSERT INTO `arcade_players` (`uuid`, `vip`) VALUES ('" + uuid.toString() + "', '0');");
                this.statement.executeUpdate("INSERT INTO `arcade_playerparticles` (`uuid`) VALUES ('" + uuid.toString() + "');");
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
