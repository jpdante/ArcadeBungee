package com.xgames178.EletricFloor.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by jpdante on 04/05/2017.
 */
public class onInventoryClick implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        //event.setCancelled(true);
        //if(event.getCurrentItem()==null) return;
        //Player player = (Player) event.getWhoClicked();
    }
}
