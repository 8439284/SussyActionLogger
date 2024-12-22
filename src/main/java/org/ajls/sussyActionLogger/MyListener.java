package org.ajls.sussyActionLogger;

import org.ajls.lib.advanced.HashMapInteger;
import org.ajls.lib.advanced.HaxhMap;
import org.ajls.lib.advanced.hashMap.HaxhMapTime;
import org.ajls.lib.advanced.hashMap.HaxhMapTimes;
import org.ajls.lib.references.Time;
import org.ajls.sussyActionLogger.util.TImeU;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import static org.ajls.sussyActionLogger.Logger.*;

public class MyListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        UUID playerUUID = player.getUniqueId();
        EquipmentSlot hand = event.getHand();
        Action action = event.getAction();
        if (hand == EquipmentSlot.HAND) {
            if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
//                Logger.logCps(player);
                if (player_attackBanTimeStamp.get(playerUUID) > Time.getTime()) {
                    event.setCancelled(true);
                }

            }
            else if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
//                Logger.logRCps(player);
                if (player_placeBanTimeStamp.get(playerUUID) > Time.getTime()) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            UUID playerUUID = player.getUniqueId();
//            logCps(player);
            if (player_attackBanTimeStamp.biggerThanTime(playerUUID)) {
                event.setDamage(0.0000001);
            }
        }
    }
}
