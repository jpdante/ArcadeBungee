package com.xgames178.ArcadeLobby.Events;

import com.xgames178.ArcadeLobby.Menus.GadgetMenu;
import com.xgames178.ArcadeLobby.Menus.MiniGamesMenu;
import com.xgames178.ArcadeLobby.Utils.UtilItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by jpdante on 04/05/2017.
 */
public class onPlayerInteract implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(event.getItem()==null)return;
        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(UtilItem.isEqualItem(event.getItem(), Material.NETHER_STAR, "§aGadgets Menu")) {
                event.setCancelled(true);
                event.getPlayer().openInventory(GadgetMenu.getInventory());
            } else if(UtilItem.isEqualItem(event.getItem(), Material.COMPASS, "§aMiniGames Menu")) {
                event.setCancelled(true);
                event.getPlayer().openInventory(MiniGamesMenu.getInventory());
            }
        }
    }
}
