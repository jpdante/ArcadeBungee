package com.xgames178.EletricFloor.Database.Tasks;

import com.xgames178.EletricFloor.Database.Callback;
import com.xgames178.EletricFloor.Database.DataTask;
import com.xgames178.EletricFloor.Main;
import com.xgames178.EletricFloor.Player.ProfileData;
import org.bukkit.Bukkit;

/**
 * Created by jpdante on 05/05/2017.
 */
public class SaveProfileTask extends DataTask {
        private final ProfileData profileData;
        private final Callback<Integer> callback;

        public SaveProfileTask(ProfileData profileData, Callback<Integer> callback) {
            super(Priority.HIGH);
            this.profileData = profileData;
            this.callback = callback;
        }

        @Override
        public void run() {
            Main.getInstance().getDataProvider().setProfileData(this.profileData);
            Bukkit.getScheduler().runTask(Main.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    if (callback != null) {
                        callback.onComplete(1);
                    }
                }
            });
        }
    }