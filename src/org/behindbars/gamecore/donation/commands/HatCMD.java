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
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class HatCMD extends Command {

	private static String name = "hat";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public HatCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if(Main.getPlayerHandler(player).getDonateHat() == 0) {
            player.sendMessage(Main.getColorHandler().noPermission);
        }else if(player.getInventory().getItemInMainHand().getType() == Material.AIR) {
            player.sendMessage(Main.getColorHandler().donation + "You do not have an item in your hand!");
        }else if(player.getInventory().getHelmet() == null) {
            player.getInventory().setHelmet(player.getInventory().getItemInMainHand());
            player.getInventory().remove(player.getInventory().getItemInMainHand());
        }else {
        	player.sendMessage(Main.getColorHandler().donation + "You must take off your current hat!");
        }
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		return list;
	}

}
