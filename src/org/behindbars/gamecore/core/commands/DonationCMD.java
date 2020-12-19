/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.behindbars.gamecore.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class DonationCMD extends Command {

	private static String name = "donations";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("donate");

	public DonationCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		player.sendMessage(Main.getColorHandler().donation + "LINK");
		player.sendMessage(Main.getColorHandler().main + "1-Hat " + Main.getColorHandler().message + "Put any item as a hat!");
		player.sendMessage(Main.getColorHandler().main + "2-Pvp " + Main.getColorHandler().message + "Disable/Enable pvp!");
		player.sendMessage(Main.getColorHandler().main + "3-Nickname " + Main.getColorHandler().message + "Set a custom nickname for display!!");
		player.sendMessage(Main.getColorHandler().main + "4-Feed " + Main.getColorHandler().message + "Never run out of hunger again!!");
		player.sendMessage(Main.getColorHandler().main + "5-Bail " + Main.getColorHandler().message + "Bail out of jail (costs 5000)!!");
		player.sendMessage(Main.getColorHandler().main + "6-Namecolor " + Main.getColorHandler().message + "Change your namecolor!!");
		player.sendMessage(Main.getColorHandler().main + "7-Spawn " + Main.getColorHandler().message + "Teleport to Spawn!!");
		player.sendMessage(Main.getColorHandler().main + "8-Stats " + Main.getColorHandler().message + "View stats, totals, redeem rewards, and more!!");
		player.sendMessage(Main.getColorHandler().main + "9-Trails " + Main.getColorHandler().message + "Have a particle trail!!");
		player.sendMessage(Main.getColorHandler().main + "10-Disco Armor " + Main.getColorHandler().message + "Have disco armor!!");
		//TODO
		player.sendMessage(Main.getColorHandler().main + "11-Pet " + Main.getColorHandler().message + "Have a baby pet follow you around!!");
		player.sendMessage(Main.getColorHandler().main + "12-Trash " + Main.getColorHandler().message + "Opens a trash GUI!!");
		player.sendMessage(Main.getColorHandler().main + "13-Craft " + Main.getColorHandler().message + "Craft anywhere you want!!");
		player.sendMessage(Main.getColorHandler().main + "13-Player Vault " + Main.getColorHandler().message + "Access your player vault whenever!!!");
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		if (sender instanceof ConsoleCommandSender) return list;
		// Player player = (Player) sender;
		return list;
	}

}