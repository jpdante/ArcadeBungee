package com.xgames178.ArcadeLobby.Modules;

import com.xgames178.ArcadeLobby.Updater.UpdateType;
import com.xgames178.ArcadeLobby.Updater.event.UpdateEvent;
import com.xgames178.ArcadeLobby.Utils.UtilAction;
import com.xgames178.ArcadeLobby.Utils.UtilBlock;
import com.xgames178.ArcadeLobby.Utils.UtilEnt;
import com.xgames178.ArcadeLobby.Utils.UtilServer;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

/**
 * Created by jpdante on 03/05/2017.
 */
public class JumpModule implements Listener {
    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) return;
        event.setCancelled(true);
        player.setFlying(false);
        player.setAllowFlight(false);
        UtilAction.velocity(player, 1.4, 0.2, 1, true);
        player.playSound(player.getLocation(), Sound.PISTON_EXTEND, 1f, 1f);
    }

    @EventHandler
    public void FlightUpdate(UpdateEvent event) {
        if (event.getType() != UpdateType.TICK) return;
        for (Player player : UtilServer.getPlayers()) {
            if (player.getGameMode() == GameMode.CREATIVE) continue;
            if (UtilEnt.isGrounded(player) || UtilBlock.solid(player.getLocation().getBlock().getRelative(BlockFace.DOWN))) {
                player.setAllowFlight(true);
                player.setFlying(false);
            }
        }
    }
}
