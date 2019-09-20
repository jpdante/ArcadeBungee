package com.xgames178.ArcadeLobby.Events;

import com.xgames178.ArcadeLobby.Player.PlayerProfile;
import com.xgames178.ArcadeLobby.Utils.Cache;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by jpdante on 02/05/2017.
 */
public class onPlayerQuit implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        PlayerProfile profile = Cache.profiles.get(event.getPlayer());
        profile.ExitPlayerProfile();
    }
}
