package org.ajls.sussyActionLogger.command;

import org.ajls.lib.utils.CommandU;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class S implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        CommandU.runCommand(sender, "plugman reload SussyActionLogger");
        return true;
    }
}
