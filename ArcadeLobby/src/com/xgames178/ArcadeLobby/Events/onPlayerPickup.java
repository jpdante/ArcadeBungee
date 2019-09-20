package com.xgames178.ArcadeLobby.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * Created by jpdante on 02/05/2017.
 */
public class onPlayerPickup implements Listener {
    @EventHandler
    public void onPlayerPickup(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }
}
