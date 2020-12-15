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
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CraftCMD extends Command {

	private static String name = "craft";
	private static String description = "Craft command for donators";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public CraftCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if(sender instanceof ConsoleCommandSender) return true;
		Player player = (Player)sender;

		if(Main.getPlayerHandler(player).getDonateFeed() == 0) {
			player.sendMessage(Main.getColorHandler().noPermission);
		}else {
			player.openWorkbench(null, true);
		}
		
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		return list;
	}

}
