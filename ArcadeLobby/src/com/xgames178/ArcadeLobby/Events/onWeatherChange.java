package com.xgames178.ArcadeLobby.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * Created by jpdante on 02/05/2017.
 */
public class onWeatherChange implements Listener {
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        if(event.toWeatherState()) {
            event.setCancelled(true);
        }
    }
}
