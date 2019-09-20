package com.xgames178.ArcadeLobby.Menus;

import com.xgames178.ArcadeLobby.Utils.UtilItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

/**
 * Created by jpdante on 05/05/2017.
 */
public class MiniGamesMenu {
    private static Inventory menu;

    public MiniGamesMenu() {
        menu = Bukkit.getServer().createInventory(null, 54, "§lServers Menu");
        menu.setItem(10, UtilItem.getItemStack(Material.STAINED_GLASS, 1, (byte) 3, "§b§lEletric Floor", "§7Do not be stopped, run,", "§7or you will be electrocuted."));
    }

    public static Inventory getInventory() {
        return menu;
    }
}
