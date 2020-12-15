/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.donation.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.behindbars.gamecore.Main;
import org.behindbars.gamecore.core.util.GUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class EventCMD extends Command {

	private static String name = "event";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public EventCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
        if(Main.getPlayerHandler(player).getRank() < 10) {
            player.sendMessage(Main.getColorHandler().noPermission);
        }else if(args.length != 2) {
            player.sendMessage(Main.getColorHandler().usage + args[0] + " <Landfall>");
        }else if(args[1].equalsIgnoreCase("landfall")) {
            GUI.eventLandFallInv(player);
        }
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		return list;
	}

}