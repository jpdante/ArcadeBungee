package com.xgames178.EletricFloor.MiniGame;

import com.xgames178.EletricFloor.Updater.UpdateType;
import com.xgames178.EletricFloor.Updater.event.UpdateEvent;
import com.xgames178.EletricFloor.Utils.*;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jpdante on 05/05/2017.
 */
public class EletricFloor implements Listener {
    private boolean Started = false;
    private boolean Stoped = false;
    private int StartTimer = 120;
    private int StopTimer = 10;
    private List<Player> MiniGamePlayers;
    private List<Player> InGamePlayers;
    private NautHashMap<Player, Long> _lastMove = new NautHashMap<Player, Long>();
    private Player winner;

    public EletricFloor() {
        MiniGamePlayers = new ArrayList<>();
        InGamePlayers = new ArrayList<>();
    }

    public void preStartMiniGame() {
        Bukkit.broadcastMessage("§6§lGame pre started");
        for(Player player : MiniGamePlayers) {
            player.teleport(new Location(Bukkit.getServer().getWorld("world"), 232.0d, 70.0d, 180.0d));
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999, -5));
            player.playSound(player.getLocation(), Sound.PORTAL, 1f,1f);
            InGamePlayers.add(player);
        }
    }

    public void startMiniGame() {
        if(MiniGamePlayers.size() < 2) {
            StartTimer += 120;
            for(Player player : MiniGamePlayers) {
                teleportPlayer(player);
            }
            InGamePlayers.clear();
            Bukkit.getServer().broadcastMessage("§f[§b§lEletricFloor§f] §cTimer cancelado pois há menos de 2 jogadores.");
            return;
        }
        Started = true;
        for(Player player : MiniGamePlayers) {
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 1f,1f);
        }
        Bukkit.getServer().broadcastMessage("§f[§b§lEletricFloor§f] §aA partida começou!");
    }

    public void stopMiniGame() {
        Started = false;
        Stoped = true;
    }

    public void restartMiniGame() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.kickPlayer("§bA partida terminou!");
        }
        MiniGamePlayers.clear();
        InGamePlayers.clear();
        Started = false;
        Stoped = false;
        StartTimer = 300;
        StopTimer = 10;
        Location location1 = new Location(Bukkit.getServer().getWorld("world"), 197.0d, 66.0d, 166.0d);
        Location location2 = new Location(Bukkit.getServer().getWorld("world"), 262.0d, 66.0d, 195.0d);
        for(Block block : getBlocksBetweenPoints(location1, location2)) {
            block.setTypeIdAndData(95, (byte) 0, true);
        }
    }

    public void removePlayer(Player player) {
        for(Player mplayer : MiniGamePlayers) {
            mplayer.hidePlayer(player);
        }
        Bukkit.getServer().broadcastMessage("§f[§b§lEletricFloor§f] §7" + player.getName() + " foi §celiminado§7!");
        InGamePlayers.remove(player);
        if(InGamePlayers.size() == 1) {
            Started = false;
            Stoped = true;
            for(Player winner : InGamePlayers) {
                setWinner(winner);
            }
            InGamePlayers.clear();
            stopMiniGame();
        }
        player.playSound(player.getLocation(), Sound.ANVIL_LAND, 0.3f,1f);
        for (PotionEffect effect : player.getActivePotionEffects()) player.removePotionEffect(effect.getType());
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 50, 99));
        player.setAllowFlight(true);
        player.setFlying(true);
    }

    public void setWinner(Player player) {
        Bukkit.getServer().broadcastMessage("§f[§b§lEletricFloor§f] §b§l" + player.getName() + "§a é o vencedor!");
        for(Player oplayers : Bukkit.getOnlinePlayers()) {
            for(Player oplayers2 : Bukkit.getOnlinePlayers()) {
                oplayers.showPlayer(oplayers2);
            }
        }
        player.setAllowFlight(true);
        player.setFlying(true);
        UtilTextMiddle.display(player.getName(), "é o vencedor!", 10, 50, 10, MiniGamePlayers);
        winner = player;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPrePlayerJoin(AsyncPlayerPreLoginEvent event) {
        if(Started || Stoped) {
            event.setKickMessage("§4O jogo já começou!");
            event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Bukkit.getServer().broadcastMessage("§f[§b§lEletricFloor§f] §e" + event.getPlayer().getName() + " entrou na partida!");
        MiniGamePlayers.add(event.getPlayer());
        if(MiniGamePlayers.size() > 1 && StartTimer > 60) {
            StartTimer = 60;
            Bukkit.getServer().broadcastMessage("§f[§b§lEletricFloor§f] §6O tempo foi reduzido para §a60 §6segundos.");
            return;
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMove(PlayerMoveEvent event) {
        if(event.getFrom().getBlockX() != event.getTo().getBlockX() || event.getFrom().getBlockZ() != event.getTo().getBlockZ()) {
            if (Started) {
                if (InGamePlayers.contains(event.getPlayer())) {
                    setBlock(event.getPlayer());
                }
            }
        }
        if (UtilMath.offset(event.getFrom(), event.getTo()) <= 0) return;
        _lastMove.put(event.getPlayer(), System.currentTimeMillis());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        MiniGamePlayers.remove(event.getPlayer());
        InGamePlayers.remove(event.getPlayer());
        Bukkit.getServer().broadcastMessage("§f[§b§lEletricFloor§f] §7" + event.getPlayer().getName() + " saiu na partida!");
    }

    public static void teleportPlayer(Player player) {
        player.teleport(new Location(Bukkit.getServer().getWorld("world"), 536.0d, 63.0d, -85.5d, 93.4f, 8.0f));
    }

    @Deprecated
    public void setBlock(Player player) {
        Block block = player.getLocation().subtract(0, 1, 0).getBlock();
        if(block.getType() != Material.STAINED_GLASS) return;
        if (block.getData() == (byte) 14) {
            removePlayer(player);
        } else if (block.getData() == (byte) 10) {
            block.setTypeIdAndData(95, (byte)14, true);
        } else if (block.getData() == (byte) 3) {
            block.setTypeIdAndData(95, (byte)10, true);
        } else if (block.getData() == (byte) 0) {
            block.setTypeIdAndData(95, (byte)3, true);
        }
    }

    public boolean isMoving(Player player) {
        if (!_lastMove.containsKey(player)) return false;
        return !UtilTime.elapsed(_lastMove.get(player), 500);
    }

    @EventHandler
    public void onUpdate2Sec(UpdateEvent event) {
        if(event.getType() != UpdateType.TWOSEC) return;
        if(winner != null) {
            UtilFirework.randomFirework(winner.getLocation().add(2,0,0));
            UtilFirework.randomFirework(winner.getLocation().add(0,0,2));
            UtilFirework.randomFirework(winner.getLocation().subtract(2, 0 ,0));
            UtilFirework.randomFirework(winner.getLocation().subtract(0, 0 ,2));
        }
    }

    @EventHandler
    public void onUpdateSec(UpdateEvent event) {
        if(event.getType() != UpdateType.SEC) return;
        if(Stoped) {
            for(Player player : MiniGamePlayers) {
                player.setLevel(StopTimer);
            }
            if(StopTimer < 1) {
                restartMiniGame();
                return;
            }
            StopTimer--;
            return;
        }
        if(!Started) {
            for(Player player : MiniGamePlayers) {
                player.setLevel(StartTimer);
            }
            if(StartTimer == 6) {
                if(MiniGamePlayers.size() < 2) {
                    StartTimer += 60;
                    Bukkit.getServer().broadcastMessage("§f[§b§lEletricFloor§f] §cNão há jogadores suficientes!");
                    return;
                }
                preStartMiniGame();
            }
            if(StartTimer < 1) {
                startMiniGame();
                return;
            }
            if(StartTimer < 6) {
                UtilTextMiddle.display("§a" +  StartTimer, 10, 10, 10, MiniGamePlayers);
                for(Player player : MiniGamePlayers) {
                    player.playSound(player.getLocation(), Sound.NOTE_PLING,1f, 1f);
                }
                Bukkit.getServer().broadcastMessage("§f[§b§lEletricFloor§f] §6Iniciando em §a" + StartTimer + " §6segundos.");
            }
            StartTimer--;
            return;
        }
    }

    @EventHandler
    public void onUpdateFast(UpdateEvent event) {
        if(event.getType() != UpdateType.FASTER) return;
        if(Started) {
            for(Player player : InGamePlayers) {
                if(!isMoving(player)) {
                    setBlock(player);
                }
            }
        }
    }

    public static List<Block> getBlocksBetweenPoints(Location l1, Location l2) {
        List<Block> blocks = new ArrayList<Block>();
        int topBlockX = (l1.getBlockX() < l2.getBlockX() ? l2.getBlockX() : l1.getBlockX());
        int bottomBlockX = (l1.getBlockX() > l2.getBlockX() ? l2.getBlockX() : l1.getBlockX());
        int topBlockY = (l1.getBlockY() < l2.getBlockY() ? l2.getBlockY() : l1.getBlockY());
        int bottomBlockY = (l1.getBlockY() > l2.getBlockY() ? l2.getBlockY() : l1.getBlockY());
        int topBlockZ = (l1.getBlockZ() < l2.getBlockZ() ? l2.getBlockZ() : l1.getBlockZ());
        int bottomBlockZ = (l1.getBlockZ() > l2.getBlockZ() ? l2.getBlockZ() : l1.getBlockZ());
        for(int x = bottomBlockX; x <= topBlockX; x++) {
            for(int y = bottomBlockY; y <= topBlockY; y++) {
                for(int z = bottomBlockZ; z <= topBlockZ; z++) {
                    Block block = l1.getWorld().getBlockAt(x, y, z);
                    blocks.add(block);
                }
            }
        }
        return blocks;
    }
}
