package com.xgames178.ArcadeLobby.Gadget.Gadgets;

import com.xgames178.ArcadeLobby.Gadget.GadgetManager;
import com.xgames178.ArcadeLobby.Gadget.Types.GadgetType;
import com.xgames178.ArcadeLobby.Gadget.Types.ParticleGadget;
import com.xgames178.ArcadeLobby.Updater.UpdateType;
import com.xgames178.ArcadeLobby.Updater.event.UpdateEvent;
import com.xgames178.ArcadeLobby.Utils.UtilParticle;
import com.xgames178.ArcadeLobby.Utils.UtilRandom;
import com.xgames178.ArcadeLobby.Utils.UtilServer;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.util.Vector;

/**
 * Created by jpdante on 05/05/2017.
 */
public class ParticleShield extends ParticleGadget {
    public ParticleShield(GadgetManager manager) {
        super(manager, GadgetType.Particle, "ParticleShield");
    }

    @EventHandler
    public void playParticle(UpdateEvent event) {
        if (event.getType() != UpdateType.TICK) return;
        for (Player player : _active) {
            if (!shouldDisplay(player)) continue;
            if (Manager.isMoving(player)) {
                UtilParticle.PlayParticle(EnumParticle.CRIT_MAGIC, player.getLocation().add(0, 1, 0), 0.2f, 0.2f, 0.2f, 0, 4, UtilParticle.ViewDist.NORMAL, UtilServer.getPlayers());
            } else {
                int radius = 3;
                int particles = 50;
                boolean sphere = false;
                Vector v = new Vector();
                for (int i = 0; i < particles; i++) {
                    Vector vector = UtilRandom.getRandomVector().multiply(radius);
                    if (!sphere) {
                        vector.setY(Math.abs(vector.getY()));
                    }
                    UtilParticle.PlayParticle(EnumParticle.CRIT_MAGIC, player.getLocation().add(vector), 0f, 0f, 0f, 0, 1, UtilParticle.ViewDist.NORMAL, UtilServer.getPlayers());
                }
            }
        }
    }
}
