package com.xgames178.EletricFloor.Player;

import com.xgames178.EletricFloor.Database.Callback;
import com.xgames178.EletricFloor.Database.Tasks.LoadProfileTask;
import com.xgames178.EletricFloor.Database.Tasks.SaveProfileTask;
import com.xgames178.EletricFloor.Main;
import com.xgames178.EletricFloor.Utils.Cache;
import com.xgames178.EletricFloor.Utils.UtilTextBottom;
import com.xgames178.EletricFloor.Utils.UtilTextMiddle;
import com.xgames178.EletricFloor.Utils.UtilTextTab;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

/**
 * Created by jpdante on 02/05/2017.
 */
public class PlayerProfile {
    public ProfileData profileData = null;
    public Player player;

    public PlayerProfile(Player playerclass) {
        player = playerclass;
        Main.getInstance().getDataManager().addTask(new LoadProfileTask(player.getUniqueId(), new Callback<ProfileData>() {
            @Override
            public void onComplete(ProfileData pD) {
                profileData = pD;
                EndLoad();
            }
        }));
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
        UtilTextBottom.displayProgress("Carregando Perfil... ", 0, player);
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        player.setFlying(false);
        player.setAllowFlight(false);
        player.teleport(new Location(Bukkit.getServer().getWorld("world"), 536.0d, 63.0d, -85.5d, 93.4f, 8.0f));
        for (PotionEffect effect : player.getActivePotionEffects()) player.removePotionEffect(effect.getType());
        UtilTextTab.display("§a§lFight§c§lCraft §b§lArcade", "     §6§lloja.fightcraftbr.com     ", player);
        UtilTextMiddle.display("§b§lEletric Floor", "§7Corra, ou seja eletrocutado!", 10, 35, 10, player);
    }

    public void EndLoad() {
        UtilTextBottom.displayProgress("Carregando Perfil... ", 100, player);
    }

    public void PlayerQuit() {}
}
