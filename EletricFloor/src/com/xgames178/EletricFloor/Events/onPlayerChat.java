package com.xgames178.EletricFloor.Events;

import com.xgames178.EletricFloor.Player.PlayerProfile;
import com.xgames178.EletricFloor.Utils.Cache;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by jpdante on 02/05/2017.
 */
public class onPlayerChat implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        PlayerProfile profile = Cache.profiles.get(event.getPlayer());
        if(profile.profileData.vip == 0) {
            event.setFormat(ChatColor.DARK_AQUA+ event.getPlayer().getName() + " §7›› " + event.getMessage());
        } else if(profile.profileData.vip == 1) {
            event.setFormat("§a[VIP] " + event.getPlayer().getName() + " §7›› " + ChatColor.GREEN + event.getMessage().replace("&", "§"));
        } else if(profile.profileData.vip == 2) {
            event.setFormat("§a[VIP§6+§a] " + event.getPlayer().getName() + " §7›› " + ChatColor.GREEN + event.getMessage().replace("&", "§"));
        } else if(profile.profileData.vip == 3) {
            event.setFormat("§b[MVP] " + event.getPlayer().getName() + " §7›› " + ChatColor.AQUA + event.getMessage().replace("&", "§"));
        }
    }

}
