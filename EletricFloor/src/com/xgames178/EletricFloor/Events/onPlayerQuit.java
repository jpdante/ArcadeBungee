package com.xgames178.EletricFloor.Events;

import com.xgames178.EletricFloor.Player.PlayerProfile;
import com.xgames178.EletricFloor.Utils.Cache;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by jpdante on 02/05/2017.
 */
public class onPlayerQuit implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        PlayerProfile profile = Cache.profiles.get(event.getPlayer());
        profile.ExitPlayerProfile();
    }
}
