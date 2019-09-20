package com.xgames178.EletricFloor.Database.Tasks;

import com.xgames178.EletricFloor.Database.Callback;
import com.xgames178.EletricFloor.Database.DataTask;
import com.xgames178.EletricFloor.Main;
import com.xgames178.EletricFloor.Player.ProfileData;
import org.bukkit.Bukkit;

import java.util.UUID;

/**
 * Created by jpdante on 02/05/2017.
 */
public class LoadProfileTask extends DataTask {
    private final UUID playerUUID;
    private final Callback<ProfileData> callback;

    public LoadProfileTask(UUID uuid, Callback<ProfileData> callback) {
        super(Priority.HIGH);
        this.playerUUID = uuid;
        this.callback = callback;
    }

    @Override
    public void run() {
        final ProfileData profileData = Main.getInstance().getDataProvider().getProfileData(playerUUID);
        Bukkit.getScheduler().runTask(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onComplete(profileData);
                }
            }
        });
    }
}
