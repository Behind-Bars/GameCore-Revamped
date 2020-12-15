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
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;

public class ClearLagCMD extends Command {

	private static String name = "clearlag";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public ClearLagCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if(Main.getPlayerHandler(player).getRank() < 8) {
			player.sendMessage(Main.getColorHandler().noPermission);
		}else {
			World world = player.getWorld();//get the world
			List<Entity> entList = world.getEntities();//get all entities in the world
			player.sendMessage(Main.getColorHandler().main + "Lag: " + Main.getColorHandler().message + "Clearing entities!");
			for(Entity current : entList){//loop through the list
				if (!(current instanceof Player || current.isCustomNameVisible() || current instanceof ItemFrame)){//make sure we aren't deleting mobs/players
					current.remove();//remove it
				}
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
