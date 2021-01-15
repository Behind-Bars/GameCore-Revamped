/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
 */

package org.behindbars.gamecore.donation.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.behindbars.gamecore.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class NicknameCMD extends Command {

	private static String name = "nickname";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("nick");
	
	private final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

	public NicknameCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;

		if(Main.getPlayerHandler(player).getDonateNickname() == 0) {
			player.sendMessage(Main.getColorHandler().noPermission);
		}else if(args.length == 1) {
			player.sendMessage(Main.getColorHandler().usage + "/nickname <name>");
		}else if(args[0].contains("&k") || args[0].contains("&l") || args[0].contains("&m") || args[0].contains("&n") || args[0].contains("&o") || args[0].contains("&4")
				|| args[0].contains("&0")) {
			player.sendMessage(Main.getColorHandler().donation + "You can not use special characters, dark red, or black in your nickname!");
		}else if(args[0].contains("#")) {
			if(Main.getPlayerHandler(player).getRank() < 10) 
				player.sendMessage(Main.getColorHandler().donation + "You may not use Hex Codes!");
		}else if(Main.getPlayerHandler(player).getRank() < 10 && !ChatColor.stripColor(args[0].replaceAll("&","§")).equalsIgnoreCase(player.getName())) {
			player.sendMessage(Main.getColorHandler().donation + "You can use colors, but your name must remain the same!");
		}else {
			Main.getPlayerHandler(player).setNickname(format(args[0]));
	
			player.setCustomName(Main.getPlayerHandler(player).rankToString() + Main.getPlayerHandler(player).getNickname());
			player.setPlayerListName(Main.getPlayerHandler(player).rankToString() + Main.getPlayerHandler(player).getNickname());
			player.sendMessage(Main.getColorHandler().donation + "You changed your nickname to " + args[0].replaceAll("&","§"));
	

		}
		return true;
	}

	private String format(String msg) {
		if(Bukkit.getVersion().contains("0.16")) {

			Matcher match = pattern.matcher(msg);
			while (match.find()) {
				String color = msg.substring(match.start(), match.end());
				msg = msg.replace(color, ChatColor.of(color) + "");
				match = pattern.matcher(msg);
			}
		}
		return ChatColor.translateAlternateColorCodes('&', msg);
	}



	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		return list;
	}

}
