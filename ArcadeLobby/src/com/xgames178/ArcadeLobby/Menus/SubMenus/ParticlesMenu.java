package com.xgames178.ArcadeLobby.Menus.SubMenus;

import com.xgames178.ArcadeLobby.Player.ProfileData;
import com.xgames178.ArcadeLobby.Utils.UtilItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

/**
 * Created by jpdante on 04/05/2017.
 */
public class ParticlesMenu {
    private static Inventory inventory;

    public ParticlesMenu(ProfileData profileData) {
        inventory = Bukkit.getServer().createInventory(null, 54, "§lParticles");
        inventory.setItem(31, UtilItem.getItemStack(Material.REDSTONE,1, "§cDisable Particles"));
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void updateInventory(ProfileData profileData) {
        if(profileData.current_particle != 1) inventory.setItem(10, UtilItem.getItemStack(Material.INK_SACK,1, (byte) 8, "§bParticle Helix"));
        else inventory.setItem(10, UtilItem.getItemStack(Material.INK_SACK,1, (byte) 10, "§bParticle Helix"));
        if(profileData.current_particle != 2) inventory.setItem(11, UtilItem.getItemStack(Material.INK_SACK,1, (byte) 8, "§bParticle Rain"));
        else inventory.setItem(11, UtilItem.getItemStack(Material.INK_SACK,1, (byte) 10, "§bParticle Rain"));
        if(profileData.current_particle != 3) inventory.setItem(12, UtilItem.getItemStack(Material.INK_SACK,1, (byte) 8, "§bParticle Fire Rings"));
        else inventory.setItem(12, UtilItem.getItemStack(Material.INK_SACK,1, (byte) 10, "§bParticle Fire Rings"));
        if(profileData.current_particle != 4) inventory.setItem(13, UtilItem.getItemStack(Material.INK_SACK,1, (byte) 8, "§bParticle Ground Helix"));
        else inventory.setItem(13, UtilItem.getItemStack(Material.INK_SACK,1, (byte) 10, "§bParticle Ground Helix"));
        if(profileData.current_particle != 5) inventory.setItem(14, UtilItem.getItemStack(Material.INK_SACK,1, (byte) 8, "§bParticle Shield"));
        else inventory.setItem(14, UtilItem.getItemStack(Material.INK_SACK,1, (byte) 10, "§bParticle Shield"));
    }
}
