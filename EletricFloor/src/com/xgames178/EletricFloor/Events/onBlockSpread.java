package com.xgames178.EletricFloor.Events;

import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockSpreadEvent;

/**
 * Created by jpdante on 02/05/2017.
 */
public class onBlockSpread implements Listener {
    public void onBlockSpread(BlockSpreadEvent event) {
        event.setCancelled(true);
    }
}
