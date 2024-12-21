package org.ajls.sussyActionLogger;

import org.ajls.lib.advanced.HaxhMap;
import org.ajls.lib.references.Time;
import org.ajls.sussyActionLogger.util.TImeU;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class MyListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

    }
    public static HaxhMap<UUID, Integer> player_attackTimeStamp= new HaxhMap<>();
    public static HaxhMap<UUID, Integer> player_placeTimeStamp= new HaxhMap<>();
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        UUID playerUUID = player.getUniqueId();
        EquipmentSlot hand = event.getHand();
        Action action = event.getAction();
        if (hand == EquipmentSlot.HAND) {
            if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
//                for (UUID uuid : player_attackTimeStamp.keySet()) {
//
//                }
                HashSet<Integer> times = (HashSet<Integer>) player_attackTimeStamp.getValues(playerUUID);
                int cps = 0;
                if (times != null) {
                    times = (HashSet<Integer>) times.clone();
                    cps = times.size();
                    for (int time : times) {
                        if (time < Time.getTime() - 20) {
                            player_attackTimeStamp.remove(playerUUID, time);
                        }
                    }
                }
//                player_attackTimeStamp.put()
                player_attackTimeStamp.put(playerUUID, Time.getTime());
//                int cps = times.size();
                if (cps >= 15) {
                    Configuration configuration = SussyActionLogger.getPlugin().getConfig();
                    if (configuration.get("log.guilty." + playerName + ".ac") == null) {
                        configuration.createSection("log.guilty." + playerName + ".ac");
                    }
                    configuration.set("log.guilty." + playerName + ".ac." + TImeU.getTime(), cps);
                    SussyActionLogger.getPlugin().saveConfig();
                    Bukkit.getLogger().warning("\n##########\n" + playerName + " cps :" + cps + "\n##########");
                }
                else if (cps >= 10) {
                    Configuration configuration = SussyActionLogger.getPlugin().getConfig();
                    if (configuration.get("log.sussy." + playerName + ".ac") == null) {
                        configuration.createSection("log.sussy." + playerName + ".ac");
                    }
                    configuration.set("log.sussy." + playerName + ".ac." + TImeU.getTime(), cps);
                    SussyActionLogger.getPlugin().saveConfig();
                    Bukkit.getLogger().warning(playerName + " cps :" + cps);
                }
            }
            else if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                HashSet<Integer> times = (HashSet<Integer>) player_placeTimeStamp.getValues(playerUUID);
                int cps = 0;
                if (times != null) {
                    times = (HashSet<Integer>) times.clone();
                    cps = times.size();
                    for (int time : times) {
                        if (time < Time.getTime() - 20) {
                            player_placeTimeStamp.remove(playerUUID, time);
                        }
                    }
                }
//                player_attackTimeStamp.put()
                player_placeTimeStamp.put(playerUUID, Time.getTime());
//                int cps = times.size();
                if (cps >= 15) {
                    Configuration configuration = SussyActionLogger.getPlugin().getConfig();
                    if (configuration.get("log.guilty." + playerName + ".rac") == null) {
                        configuration.createSection("log.guilty." + playerName + ".rac");
                    }
                    configuration.set("log.guilty." + playerName + ".rac." + TImeU.getTime(), cps);
                    SussyActionLogger.getPlugin().saveConfig();
                    Bukkit.getLogger().warning("\n##########\n" + playerName + " rcps :" + cps + "\n##########");
                }
                else if (cps >= 10) {
                    Configuration configuration = SussyActionLogger.getPlugin().getConfig();
                    if (configuration.get("log.sussy." + playerName + ".rac") == null) {
                        configuration.createSection("log.sussy." + playerName + ".rac");
                    }
                    configuration.set("log.sussy." + playerName + ".rac." + TImeU.getTime(), cps);
                    SussyActionLogger.getPlugin().saveConfig();
                    Bukkit.getLogger().warning(playerName + " rcps :" + cps);
                }
            }
        }
    }
}
