package com.xgames178.EletricFloor.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryInteractEvent;

/**
 * Created by jpdante on 04/05/2017.
 */
public class onInventoryInteract implements Listener {
    @EventHandler
    public void onInventoryInteract(InventoryInteractEvent event) {
        event.setCancelled(true);
    }
}
