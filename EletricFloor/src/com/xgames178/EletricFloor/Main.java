package com.xgames178.EletricFloor;

import com.xgames178.EletricFloor.Database.DataManager;
import com.xgames178.EletricFloor.Database.DataProvider;
import com.xgames178.EletricFloor.Database.Database;
import com.xgames178.EletricFloor.Database.Mysql.MySQL;
import com.xgames178.EletricFloor.Database.Sqlite.SQLite;
import com.xgames178.EletricFloor.Events.*;
import com.xgames178.EletricFloor.MiniGame.EletricFloor;
import com.xgames178.EletricFloor.Updater.Updater;
import com.xgames178.EletricFloor.Utils.Cache;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

/**
 * Created by jpdante on 05/05/2017.
 */
public class Main extends JavaPlugin {
    private static Main instance;
    private static Plugin plugin;
    private static Database database;
    private static DataProvider dataProvider;
    private static DataManager dataManager;
    private static EletricFloor eletricFloor;

    @Override
    public void onEnable() {
        plugin = this;
        instance = this;
        Bukkit.getServer().setWhitelist(true);
        saveDefaultConfig();
        reloadConfig();
        if(getConfig().getString("database.type").equalsIgnoreCase("mysql")) {
            String host = getConfig().getString("database.host");
            String port = getConfig().getString("database.port");
            String dbname = getConfig().getString("database.database");
            String username = getConfig().getString("database.username");
            String password = getConfig().getString("database.password");
            database = new MySQL(host, port, dbname, username, password);
        } else {
            database = new SQLite("EletricFloor.db");
        }
        try {
            database.openConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            Bukkit.shutdown();
        }
        dataProvider = new DataProvider(database);
        dataManager = new DataManager(dataProvider);
        new Cache();
        new Updater(instance);
        eletricFloor = new EletricFloor();
        EnableEvents();
        Bukkit.getServer().setWhitelist(false);
    }

    public void EnableEvents() {
        Bukkit.getServer().getPluginManager().registerEvents(new onPlayerJoin(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new onInventoryInteract(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new onInventoryClick(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new onInventoryMove(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new onPlayerQuit(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new onPlayerChat(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new onBlockBreak(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new onBlockBurn(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new onBlockPlace(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new onBlockSpread(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new onFoodLevelChange(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new onPlayerDrop(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new onPlayerPickup(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new onWeatherChange(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new onPlayerMove(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new onEntityDamageByBlock(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new onEntityDamageByEntity(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new onEntityDamageEvent(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new onPlayerInteract(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(eletricFloor, instance);
    }

    @Override
    public void onDisable() {
        dataManager.shutown();
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static Main getInstance() {
        return instance;
    }

    public DataProvider getDataProvider() {
        return dataProvider;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public Database getDatabaseIO() {
        return database;
    }
}
