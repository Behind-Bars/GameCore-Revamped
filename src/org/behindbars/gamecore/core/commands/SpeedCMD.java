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

public class SpeedCMD extends Command {

	private static String name = "speed";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public SpeedCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		if(args.length == 0) {
			player.sendMessage(Main.getColorHandler().usage + "/speed 1-10");
			return true;
		}
		if(Main.getPlayerHandler(player).getRank() < 10) {
			player.sendMessage(Main.getColorHandler().noPermission);
		} else {
			try {
				int speed = Integer.parseInt(args[0]);
				player.setFlySpeed((float)speed / 10.0f);
				player.setWalkSpeed((float)speed / 10.0f);
				player.sendMessage(Main.getColorHandler().main + "Speed: " + Main.getColorHandler().message + "Set to " + speed);
			} catch (IllegalArgumentException e) {
				player.sendMessage(Main.getColorHandler().usage + args[0] + " <1-10>");
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