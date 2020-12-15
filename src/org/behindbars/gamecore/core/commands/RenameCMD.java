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
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenameCMD extends Command {

	private static String name = "rename";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public RenameCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if(Main.getPlayerHandler(player).getRank() < 10) {
            player.sendMessage(Main.getColorHandler().noPermission);
        }else if(args.length != 2) {
            player.sendMessage(Main.getColorHandler().usage + args[0] + " <name>");
        }else {
        	ItemStack inhand = player.getInventory().getItemInMainHand();
	        if(player.getInventory().getItemInMainHand().getType() == Material.AIR) {
	            player.sendMessage(Main.getColorHandler().error + "You do not have an item in your hand!");
	        }else {
		        ItemMeta im = inhand.getItemMeta();
		        im.setDisplayName(args[1].replaceAll("_", " "));
		        inhand.setItemMeta(im);
		        player.getInventory().setItemInMainHand(inhand);
	        }
    	}
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		return list;
	}

}