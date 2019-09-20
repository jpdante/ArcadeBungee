package com.xgames178.EletricFloor.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;

/**
 * Created by jpdante on 02/05/2017.
 */
public class onBlockBurn implements Listener {
    @EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
        event.setCancelled(true);
    }
}
