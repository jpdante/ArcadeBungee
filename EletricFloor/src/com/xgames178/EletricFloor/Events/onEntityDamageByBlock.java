package com.xgames178.EletricFloor.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;

/**
 * Created by jpdante on 03/05/2017.
 */
public class onEntityDamageByBlock implements Listener {
    @EventHandler
    public void onEntityDamageByBlock(EntityDamageByBlockEvent event) {
        event.setCancelled(true);
    }
}
