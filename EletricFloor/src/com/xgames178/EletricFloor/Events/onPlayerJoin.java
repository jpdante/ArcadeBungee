package com.xgames178.EletricFloor.Events;

import com.xgames178.EletricFloor.Player.PlayerProfile;
import com.xgames178.EletricFloor.Utils.Cache;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by jpdante on 02/05/2017.
 */
public class onPlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        PlayerProfile pf = new PlayerProfile(event.getPlayer());
        Cache.profiles.put(event.getPlayer(), pf);
        pf.PlayerJoin();
    }
}
