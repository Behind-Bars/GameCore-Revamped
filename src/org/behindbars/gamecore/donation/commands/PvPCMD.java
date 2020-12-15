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

public class PvPCMD extends Command {

	private static String name = "pvp";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public PvPCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if(Main.getPlayerHandler(player).getDonatePvpStatus() == 0) {
			player.sendMessage(Main.getColorHandler().noPermission);
		}else if(Main.getPlayerHandler(player).getPvpStatus() == 0) {
			Main.getPlayerHandler(player).setPvpStatus(1);
			player.sendMessage(Main.getColorHandler().donation + "Turned PVP off!");
		}else if(Main.getPlayerHandler(player).getPvpStatus() == 1) {
			Main.getPlayerHandler(player).setPvpStatus(0);
			player.sendMessage(Main.getColorHandler().donation + "Turned PVP on!");
		}
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		return list;
	}

}
