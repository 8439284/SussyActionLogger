package org.ajls.sussyActionLogger;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.ajls.sussyActionLogger.command.S;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static org.ajls.sussyActionLogger.Logger.isBanned;

public final class SussyActionLogger extends JavaPlugin {
    public static SussyActionLogger getPlugin() {
        return plugin;
    }

    static SussyActionLogger plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        getServer().getPluginManager().registerEvents(new MyListener(), this);
        getCommand("s").setExecutor(new S());
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener(
                new PacketAdapter(this, ListenerPriority.LOWEST, PacketType.Play.Client.ARM_ANIMATION) {
                    @Override
                    public void onPacketReceiving(PacketEvent event) {
                        if (event.getPacketType() == PacketType.Play.Client.ARM_ANIMATION) {
                            Player player = event.getPlayer();
//                            handleLeftClick(player);
                            Logger.logCps(player);
                        }
                    }
                }
        );
        protocolManager.addPacketListener(
                new PacketAdapter(this, ListenerPriority.LOWEST, PacketType.Play.Client.USE_ENTITY, PacketType.Play.Client.USE_ITEM) {  //, PacketType.Play.Client.BLOCK_PLACE
                    @Override
                    public void onPacketReceiving(PacketEvent event) {
//                        if (event.getPacketType() == PacketType.Play.Client.ARM_ANIMATION) {
//                            Player player = event.getPlayer();
////                            handleLeftClick(player);
//                            Logger.logCps(player);
//                        }
                        Player player = event.getPlayer();
                        Logger.logRCps(player);
                        if (isBanned(player, Logger.player_placeBanTimeStamp)) {
                            event.setCancelled(true);
                        }

                    }
                }
        );

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
