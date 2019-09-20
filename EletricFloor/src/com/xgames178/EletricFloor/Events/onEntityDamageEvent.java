package com.xgames178.EletricFloor.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Created by jpdante on 03/05/2017.
 */
public class onEntityDamageEvent implements Listener {
    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event) {
        event.setCancelled(true);
    }
}
