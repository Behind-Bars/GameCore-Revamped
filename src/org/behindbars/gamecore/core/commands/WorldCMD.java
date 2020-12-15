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
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class WorldCMD extends Command {

	private static String name = "world";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public WorldCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if(args[0].equalsIgnoreCase("create")) {
			if(Main.getPlayerHandler(player).getRank() < 10) {
				player.sendMessage(Main.getColorHandler().noPermission);
			}else if(args.length != 2) {
				player.sendMessage(Main.getColorHandler().usage + args[0] + " <world>");
			}else if (Bukkit.getWorld(args[1]) != null) {
				player.sendMessage(Main.getColorHandler().main + "World: " + Main.getColorHandler().message + " World already made!");
			}else {
				WorldCreator worldCreator = new WorldCreator(args[1]);
				worldCreator.generateStructures(false);
				worldCreator.type(WorldType.FLAT);
	
				Bukkit.getServer().createWorld(worldCreator);
				new BukkitRunnable() {
					@Override
					public void run() {
						World world = Bukkit.getServer().getWorld(args[1]);//get the world
						List<Entity> entList = world.getEntities();//get all entities in the world
						for(Entity current : entList){//loop through the list
							if (!(current instanceof Player || current.isCustomNameVisible() || current instanceof ItemFrame)){//make sure we aren't deleting mobs/players
								current.remove();//remove it
							}
						}
					}
				}.runTaskLater(Main.getInstance(), 4500);
				Bukkit.getServer().broadcastMessage("complete");
			}
		}else if(args[0].equalsIgnoreCase("tp")) {
			if(Main.getPlayerHandler(player).getRank() < 10) {
				player.sendMessage(Main.getColorHandler().noPermission);
			}else if(args.length != 2) {
				player.sendMessage(Main.getColorHandler().usage + args[1] + " <world>");
			}else {
				Location loc = new Location(Bukkit.getWorld(args[2]), 1, 1, 1);
				player.sendMessage(Main.getColorHandler().main + "World: " + "You have been teleported to world " + args[2] + "!");
				player.teleport(loc);
			}
		}else if(args[0].equalsIgnoreCase("list")) {
			List<String> wList = new ArrayList<>();
			Bukkit.getWorlds().forEach((w) -> wList.add(Main.getColorHandler().message+ w.getName().toString() + "§7"));
			player.sendMessage(Main.getColorHandler().message + String.join(",", wList));
		}
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		return list;
	}

}
