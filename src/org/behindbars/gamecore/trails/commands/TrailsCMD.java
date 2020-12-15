/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.trails.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.behindbars.gamecore.Main;
import org.behindbars.gamecore.trails.TrailsGUI;
import org.behindbars.gamecore.trails.util.ParticleData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class TrailsCMD extends Command {

	private static String name = "";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public TrailsCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if(args.length == 0) {
			if(Main.getPlayerHandler(player).getDonateTrails() == 0) {
				player.sendMessage(Main.getColorHandler().noPermission);
			}else {
				TrailsGUI menu = new TrailsGUI();
				menu.openInventory(player);
			}
		}else if(args.length == 1) {
			if(args[1].equalsIgnoreCase("remove")) {
				if(Main.getPlayerHandler(player).getDonateTrails() == 0) {
					player.sendMessage(Main.getColorHandler().noPermission);
				}else {
					ParticleData p = new ParticleData(player.getUniqueId());
					if(p.hasID()) {
						p.endTask();
					}
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