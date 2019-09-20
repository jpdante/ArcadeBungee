package com.xgames178.ArcadeLobby.Events;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.xgames178.ArcadeLobby.Gadget.Types.Gadget;
import com.xgames178.ArcadeLobby.Gadget.Types.GadgetType;
import com.xgames178.ArcadeLobby.Main;
import com.xgames178.ArcadeLobby.Menus.GadgetMenu;
import com.xgames178.ArcadeLobby.Menus.MiniGamesMenu;
import com.xgames178.ArcadeLobby.Player.PlayerProfile;
import com.xgames178.ArcadeLobby.Utils.Cache;
import com.xgames178.ArcadeLobby.Utils.UtilItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by jpdante on 04/05/2017.
 */
public class onInventoryClick implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getCurrentItem()==null) return;
        Player player = (Player) event.getWhoClicked();
        PlayerProfile playerProfile = Cache.profiles.get(player);
        if(event.getInventory().hashCode() == GadgetMenu.getInventory().hashCode()) {
            event.setCancelled(true);
            if(UtilItem.isEqualItem(event.getCurrentItem(), Material.FIREWORK, "§6§lParticles")) {
                player.playSound(player.getLocation(), Sound.NOTE_STICKS, 0.5f, 1f);
                event.getWhoClicked().closeInventory();
                playerProfile.particlesMenu.updateInventory(playerProfile.profileData);
                event.getWhoClicked().openInventory(playerProfile.particlesMenu.getInventory());
            }
        } else if (event.getInventory().hashCode() == playerProfile.particlesMenu.getInventory().hashCode()) {
            event.setCancelled(true);
            if(UtilItem.isEqualItem(event.getCurrentItem(), Material.REDSTONE, "§cDisable Particles")) disableParticle(playerProfile);
            else if(UtilItem.isEqualItem(event.getCurrentItem(), Material.INK_SACK, "§bParticle Helix")) selectParticle(playerProfile, 0);
            else if(UtilItem.isEqualItem(event.getCurrentItem(), Material.INK_SACK, "§bParticle Rain")) selectParticle(playerProfile, 1);
            else if(UtilItem.isEqualItem(event.getCurrentItem(), Material.INK_SACK, "§bParticle Fire Rings")) selectParticle(playerProfile, 2);
            else if(UtilItem.isEqualItem(event.getCurrentItem(), Material.INK_SACK, "§bParticle Ground Helix")) selectParticle(playerProfile, 3);
            else if(UtilItem.isEqualItem(event.getCurrentItem(), Material.INK_SACK, "§bParticle Shield")) selectParticle(playerProfile, 4);
        } else if (event.getInventory().hashCode() == MiniGamesMenu.getInventory().hashCode()) {
            event.setCancelled(true);
            if(UtilItem.isEqualItem(event.getCurrentItem(), Material.STAINED_GLASS, "§b§lEletric Floor")) {
                player.playSound(player.getLocation(), Sound.CAT_MEOW, 1f, 2f);
                event.getWhoClicked().closeInventory();
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF("eletricfloor");
                player.sendPluginMessage(Main.getPlugin(), "BungeeCord", out.toByteArray());
            }
        }
    }

    public static void disableParticle(PlayerProfile playerProfile) {
        playerProfile.player.playSound(playerProfile.player.getLocation(), Sound.NOTE_PLING, 0.3f, 1f);
        playerProfile.profileData.current_particle = 0;
        Main.getInstance().getGadgetManager().DisableAll(playerProfile.player);
        playerProfile.player.closeInventory();
    }

    public static void selectParticle(PlayerProfile playerProfile, int index) {
        playerProfile.player.playSound(playerProfile.player.getLocation(), Sound.NOTE_PLING, 1f, 2f);
        playerProfile.profileData.current_particle = (index + 1);
        Main.getInstance().getGadgetManager().DisableAll(playerProfile.player);
        Gadget gadget = Main.getInstance().getGadgetManager().getGadgets(GadgetType.Particle).get(index);
        gadget.GetActive().add(playerProfile.player);
        gadget.Enable(playerProfile.player);
        playerProfile.player.closeInventory();
    }
}
