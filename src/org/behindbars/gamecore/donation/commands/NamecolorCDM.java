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
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class NamecolorCDM extends Command {

	private static String name = "namecolor";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public NamecolorCDM() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;

		if(Main.getPlayerHandler(player).getDonateNameColor() == 0) {
			player.sendMessage(Main.getColorHandler().noPermission);
		}else if(args.length != 1) {
			player.sendMessage(Main.getColorHandler().usage + "namecolor <color>");
			player.sendMessage(Main.getColorHandler().donation 
					+ ChatColor.translateAlternateColorCodes('&', 
							"Available name colors: &2DarkGreen &6Gold &8DarkGray &aGreen &cRed &eYellow &1DarkBlue &3DarkAqua &5Purple &7Gray &9Blue &bAqua &dPink &fWhite"));
		}else {
			for(ChatColor color : ChatColor.values()) {
				if(color.name().replace("_", "").equalsIgnoreCase(args[0])) {
					Main.getPlayerHandler(player).setNickname(color + player.getName());
					player.sendMessage(Main.getColorHandler().donation + "Namecolor set!");
				}
			}
		}
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		if(sender instanceof ConsoleCommandSender) return list;
		Player player = (Player)sender;
		
		if(args.length == 1 && Main.getPlayerHandler(player).getDonateNameColor() == 0) {
			for(ChatColor color : ChatColor.values()) {
				if(color != ChatColor.UNDERLINE || color != ChatColor.ITALIC || color != ChatColor.MAGIC || 
						color != ChatColor.STRIKETHROUGH || color != ChatColor.RESET || color != ChatColor.BOLD) {
					String name = "";
					for(String namepart : color.name().split("_")) {
						name += namepart.substring(0, 1) + namepart.substring(1).toLowerCase();
					}
					list.add(name);
				}
			}
		}
 		return list;
	}

}
