package com.xgames178.ArcadeBungee.Commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.command.ConsoleCommandSender;

/**
 * Created by jpdante on 02/05/2017.
 */
public class StopCommand extends Command {

    public StopCommand() {
        super("stop");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if(commandSender instanceof ConsoleCommandSender) {
            commandSender.sendMessage(new ComponentBuilder("Stopping server...").color(ChatColor.GREEN).create());
        }
    }
}
