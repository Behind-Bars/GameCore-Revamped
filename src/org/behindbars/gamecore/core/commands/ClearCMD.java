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

public class ClearCMD extends Command {

	private static String name = "";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public ClearCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender)
			return true;
		Player player = (Player) sender;
		
		if(Main.getPlayerHandler(player).getRank() < 6) {
            player.sendMessage(Main.getColorHandler().noPermission);
        }else {
	        player.getInventory().clear();
	        player.sendMessage(Main.getColorHandler().main + "Inventory: " + Main.getColorHandler().message + "Inventory cleared!");
	        Main.getPlayerHandler(player).setDonateDiscoArmorStatus(0);
        }
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		return list;
	}

}