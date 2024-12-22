package org.ajls.sussyActionLogger;

import org.ajls.lib.advanced.HashMapInteger;
import org.ajls.lib.advanced.HashMapIntegers;
import org.ajls.lib.advanced.hashMap.HaxhMapTimes;
import org.ajls.lib.references.Time;
import org.ajls.sussyActionLogger.util.TImeU;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Logger {
    public static String logM(Player player, String type, Object value, boolean guilty) {
        String warn1 = "\n##########\n";
        String warn2 = "\n##########";
        String playerName = player.getName();
        String sendMessage = playerName + " " + type + " :"  + value;
        if (guilty) {
            sendMessage = warn1 + sendMessage + warn2;
        }
        SussyActionLogger.getPlugin().getLogger().warning(sendMessage);
        return sendMessage;
    }

    public static void logF(Player player, String type, Object value, boolean guilty) {
        String playerName = player.getName();
        Configuration configuration = SussyActionLogger.getPlugin().getConfig();
        String guilt = "guilty";
        if (!guilty) {
            guilt = "sussy";
        }
        if (configuration.get("log." + guilt + "." + playerName + "." + type) == null) {
            configuration.createSection("log." + guilt + "." + playerName + "." + type);
        }
        configuration.set("log." + guilt + "." + playerName + "." + type + "." + TImeU.getTime(), value);
        SussyActionLogger.getPlugin().saveConfig();
    }

    public static void log(Player player, String type, Object value, boolean guilty) {
        logF(player, type, value, guilty);
        logM(player, type, value, guilty);
    }

    public static HaxhMapTimes<UUID> player_attackTimeStamp= new HaxhMapTimes<>();
    public static HaxhMapTimes<UUID> player_placeTimeStamp= new HaxhMapTimes<>();
    public static HashMapInteger<UUID> player_attackBanTimeStamp = new HashMapInteger<>();
    public static HashMapInteger<UUID> player_placeBanTimeStamp = new HashMapInteger<>();
    public static void logCps(Player player) {
        //                for (UUID uuid : player_attackTimeStamp.keySet()) {
//
//                }
//                player_attackTimeStamp.update(playerUUID, 20);
//                player_attackTimeStamp.put(playerUUID);
        UUID playerUUID = player.getUniqueId();
        player_attackTimeStamp.updateAndPut(playerUUID, 20);
        int cps = player_attackTimeStamp.count(playerUUID);
//        player.sendMessage("cps" + cps);

//                HashSet<Integer> times = (HashSet<Integer>) player_attackTimeStamp.getValues(playerUUID);
//                int cps = 0;
//                if (times != null) {
//                    times = (HashSet<Integer>) times.clone();
//                    cps = times.size();
//                    for (int time : times) {
//                        if (time < Time.getTime() - 20) {
//                            player_attackTimeStamp.remove(playerUUID, time);
//                        }
//                    }
//                }
////                player_attackTimeStamp.put()
//                player_attackTimeStamp.put(playerUUID, Time.getTime());
//                int cps = times.size();
        if (cps >= 15) {
//                    Configuration configuration = SussyActionLogger.getPlugin().getConfig();
//                    if (configuration.get("log.guilty." + playerName + ".ac") == null) {
//                        configuration.createSection("log.guilty." + playerName + ".ac");
//                    }
//                    configuration.set("log.guilty." + playerName + ".ac." + TImeU.getTime(), cps);
//                    SussyActionLogger.getPlugin().saveConfig();
//                    Bukkit.getLogger().warning("\n##########\n" + playerName + " cps :" + cps + "\n##########");
            Logger.log(player, "ac", cps, true);
            player_attackBanTimeStamp.putMax(playerUUID, Time.getTime() + cps * 2);
        }
        else if (cps >= 10) {
//                    Configuration configuration = SussyActionLogger.getPlugin().getConfig();
//                    if (configuration.get("log.sussy." + playerName + ".ac") == null) {
//                        configuration.createSection("log.sussy." + playerName + ".ac");
//                    }
//                    configuration.set("log.sussy." + playerName + ".ac." + TImeU.getTime(), cps);
//                    SussyActionLogger.getPlugin().saveConfig();
//                    Bukkit.getLogger().warning(playerName + " cps :" + cps);
            Logger.log(player, "ac", cps, false);
//            player_attackBanTimeStamp.putMax(playerUUID, Time.getTime() + cps);
        }
    }
    public static void logRCps(Player player) {
        UUID playerUUID = player.getUniqueId();
        player_placeTimeStamp.updateAndPut(playerUUID, 20);
        int cps = player_placeTimeStamp.count(playerUUID);
//        player.sendMessage("rcps" + cps);


//                HashSet<Integer> times = (HashSet<Integer>) player_placeTimeStamp.getValues(playerUUID);
//                int cps = 0;
//                if (times != null) {
//                    times = (HashSet<Integer>) times.clone();
//                    cps = times.size();
//                    for (int time : times) {
//                        if (time < Time.getTime() - 20) {
//                            player_placeTimeStamp.remove(playerUUID, time);
//                        }
//                    }
//                }
////                player_attackTimeStamp.put()
//                player_placeTimeStamp.put(playerUUID, Time.getTime());
//                int cps = times.size();
        if (cps >= 15) {
//                    Configuration configuration = SussyActionLogger.getPlugin().getConfig();
//                    if (configuration.get("log.guilty." + playerName + ".rac") == null) {
//                        configuration.createSection("log.guilty." + playerName + ".rac");
//                    }
//                    configuration.set("log.guilty." + playerName + ".rac." + TImeU.getTime(), cps);
//                    SussyActionLogger.getPlugin().saveConfig();
//                    Bukkit.getLogger().warning("\n##########\n" + playerName + " rcps :" + cps + "\n##########");
            Logger.log(player, "rac", cps, true);
            player_placeBanTimeStamp.putMax(playerUUID, Time.getTime() + cps);
        }
        else if (cps >= 10) {
//                    Configuration configuration = SussyActionLogger.getPlugin().getConfig();
//                    if (configuration.get("log.sussy." + playerName + ".rac") == null) {
//                        configuration.createSection("log.sussy." + playerName + ".rac");
//                    }
//                    configuration.set("log.sussy." + playerName + ".rac." + TImeU.getTime(), cps);
//                    SussyActionLogger.getPlugin().saveConfig();
//                    Bukkit.getLogger().warning(playerName + " rcps :" + cps);
            Logger.log(player, "rac", cps, false);
//            player_placeBanTimeStamp.putMax(playerUUID, Time.getTime() + cps/2);
        }

    }

    public static boolean isBanned(Player player, HashMapInteger<UUID> banTimerStamp) {
        return banTimerStamp.biggerThanTime(player.getUniqueId());

    }

}
