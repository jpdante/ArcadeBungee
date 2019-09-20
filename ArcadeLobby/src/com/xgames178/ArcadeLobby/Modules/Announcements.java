package com.xgames178.ArcadeLobby.Modules;

import com.xgames178.ArcadeLobby.Updater.UpdateType;
import com.xgames178.ArcadeLobby.Updater.event.UpdateEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jpdante on 05/05/2017.
 */
public class Announcements implements Listener {
    private int step = 0;
    private List<String> announcements = new ArrayList<>();

    public Announcements() {
        announcements.add("Teste 1");
        announcements.add("Teste 2");
        announcements.add("Teste 3");
        announcements.add("Teste 4");
        announcements.add("Teste 5");
    }

    @EventHandler
    public void playParticle(UpdateEvent event) {
        if (event.getType() != UpdateType.MIN_02) return;
        if(step == announcements.size()) step = 0;
        Bukkit.getServer().broadcastMessage(announcements.get(step));
        step++;
    }
}
