package com.xgames178.ArcadeLobby.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

/**
 * Created by jpdante on 04/05/2017.
 */
public class onInventoryMove implements Listener {
    @EventHandler
    public void onInventoryMove(InventoryMoveItemEvent event) {
        event.setCancelled(true);
    }
}
