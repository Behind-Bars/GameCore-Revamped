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
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerHeadCMD extends Command {

	private static String name = "playerhead";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public PlayerHeadCMD() {
		super(name, description, usageMessage, aliases);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if(Main.getPlayerHandler(player).getRank() < 10) {
            player.sendMessage(Main.getColorHandler().noPermission);
        }else if(args.length != 2) {
            player.sendMessage(Main.getColorHandler().usage + args[0] + " <player>");
        }else {
	        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
	        SkullMeta sm = (SkullMeta) skull.getItemMeta();
	        sm.setOwningPlayer(Bukkit.getOfflinePlayer(args[1]));
	        skull.setItemMeta(sm);
	
	        player.getInventory().addItem(skull);
        }
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		return list;
	}

}