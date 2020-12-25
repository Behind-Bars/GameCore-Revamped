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

public class WarpCMD extends Command {

	private static String name = "warp";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public WarpCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;

		if (Main.getPlayerHandler(player).getRank() < 10) {
			player.sendMessage(Main.getColorHandler().noPermission);
		}else if (!(args.length == 2)) {
			player.sendMessage(Main.getColorHandler().usage + "/warp set <warp>");
		} else {
			Main.getWarpHandler().setWarp(args[1].toUpperCase(), player);

			if (args[0].equalsIgnoreCase("del")) {
				//0 = off || 1 = on
				if (Main.getPlayerHandler(player).getRank() < 10) {
					player.sendMessage(Main.getColorHandler().noPermission);
				}else if (!(args.length == 2)) {
					player.sendMessage(Main.getColorHandler().usage + "/delwarp <warp>");
				}else {
					Main.getWarpHandler().delWarp(args[1].toUpperCase(), player);
				}
			}else if (args[0].equalsIgnoreCase("list")) {
				//0 = off || 1 = on
				if (Main.getPlayerHandler(player).getRank() < 10) {
					player.sendMessage(Main.getColorHandler().noPermission);
				}else {
					Main.getWarpHandler().getList(player);
				}
			}else {
				//0 = off || 1 = on
				if (Main.getPlayerHandler(player).getRank() < 10) {
					player.sendMessage(Main.getColorHandler().noPermission);
				}else if (!(args.length == 1)) {
					player.sendMessage(Main.getColorHandler().usage + "/warp <warp>");
				}

				Main.getWarpHandler().warp(args[1].toUpperCase(), player);
			}
		}
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
