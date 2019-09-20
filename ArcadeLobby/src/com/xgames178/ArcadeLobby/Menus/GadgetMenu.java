package com.xgames178.ArcadeLobby.Menus;

import com.xgames178.ArcadeLobby.Utils.UtilItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

/**
 * Created by jpdante on 04/05/2017.
 */
public class GadgetMenu {
    private static Inventory menu;

    public GadgetMenu() {
        menu = Bukkit.getServer().createInventory(null, 54, "§lGadget Menu");
        menu.setItem(10, UtilItem.getItemStack(Material.FIREWORK,1, "§6§lParticles", "§7Use particles to be swag!"));
    }

    public static Inventory getInventory() {
        return menu;
    }
}
