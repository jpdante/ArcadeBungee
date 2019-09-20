package com.xgames178.ArcadeLobby.Player;

import com.xgames178.ArcadeLobby.Database.Callback;
import com.xgames178.ArcadeLobby.Database.Tasks.LoadProfileTask;
import com.xgames178.ArcadeLobby.Database.Tasks.SaveProfileTask;
import com.xgames178.ArcadeLobby.Events.onInventoryClick;
import com.xgames178.ArcadeLobby.Main;
import com.xgames178.ArcadeLobby.Menus.SubMenus.ParticlesMenu;
import com.xgames178.ArcadeLobby.Utils.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;

/**
 * Created by jpdante on 02/05/2017.
 */
public class PlayerProfile {
    public ProfileData profileData = null;
    public Player player;
    public ParticlesMenu particlesMenu;

    public PlayerProfile(Player playerclass) {
        player = playerclass;
        Main.getInstance().getDataManager().addTask(new LoadProfileTask(player.getUniqueId(), new Callback<ProfileData>() {
            @Override
            public void onComplete(ProfileData pD) {
                profileData = pD;
                EndLoad();
            }
        }));
        particlesMenu = new ParticlesMenu(profileData);
    }

    public void ExitPlayerProfile() {
        Main.getInstance().getDataManager().addTask(new SaveProfileTask(profileData, new Callback<Integer>() {
            @Override
            public void onComplete(Integer i) {
                Cache.profiles.remove(player);
            }
        }));
    }

    public void PlayerJoin() {
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        player.setFlying(false);
        player.setAllowFlight(true);
        player.teleport(new Location(Bukkit.getServer().getWorld("world"), -1097.0d, 92.0d, 1228.0d, -90.6f, -0.6f));
        player.getInventory().setItem(0, UtilItem.getItemStack(Material.COMPASS,1, "§aMiniGames Menu"));
        player.getInventory().setItem(4, UtilItem.getItemStack(Material.NETHER_STAR,1, "§aGadgets Menu"));
        player.getInventory().setItem(8, UtilItem.getItemStack(Material.DRAGON_EGG,1, "§aMy Profile"));
        UtilTextTab.display("§a§lFight§c§lCraft §b§lArcade", "     §6§lloja.fightcraftbr.com     ", player);
        UtilTextMiddle.display("§a§lBem-Vindo ao", "§aFight§cCraft §b§lArcade", 10, 25, 10, player);
    }

    public void LoadComplete() {
        if(profileData.current_particle != 0) {
            onInventoryClick.selectParticle(this, (profileData.current_particle - 1));
        }
        UtilTextBottom.displayProgress("Loading Profile... ", 100, player);
    }

    public void EndLoad() {
        try {
            Method loadComplete = this.getClass().getMethod("LoadComplete", null);
            loadComplete.invoke(this, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void PlayerQuit() {

    }
}
