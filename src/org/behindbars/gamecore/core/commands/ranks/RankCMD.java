/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.commands.ranks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.behindbars.gamecore.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class RankCMD extends Command {

	private static String name = "rank";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public RankCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if(args[0].equalsIgnoreCase("list")) {
			player.sendMessage(Main.getColorHandler().main + "Charlie: " + Main.getColorHandler().message + "Free!");
			player.sendMessage(Main.getColorHandler().main + "Bravo: " + Main.getColorHandler().message + "10000!");
			player.sendMessage(Main.getColorHandler().main + "Alpha: " + Main.getColorHandler().message + "20000!");
			player.sendMessage(Main.getColorHandler().main + "Elite: " + Main.getColorHandler().message + "50000!");
			player.sendMessage(Main.getColorHandler().main + "Free: " + Main.getColorHandler().message + "100000!");
		}else if (args[0].equalsIgnoreCase("set")) {
			if(Main.getPlayerHandler(player).getRank() < 10) {
				player.sendMessage(Main.getColorHandler().noPermission);
			} else {
				Player target = Bukkit.getPlayerExact(args[1]);
	
				if(args.length != 3) {
					player.sendMessage(Main.getColorHandler().usage + args[0] + " <player> <rank>");
				}else {
	
					try {
						int num = Integer.parseInt(args[2]);
			
						if(num < 1 || num > 10) {
							player.sendMessage(Main.getColorHandler().error + "Must be numbers 1-10");
						} else {
							Main.getPlayerHandler(target).setRank(num);
							player.sendMessage(Main.getColorHandler().main + "Rank: " + Main.getColorHandler().message + "Set " + target.getName() + "'s rank to " + args[2]);
							target.sendMessage(Main.getColorHandler().main + "Rank: " + Main.getColorHandler().message + "has been updated to "
									+ Main.getPlayerHandler(target).rankToString());
							
							target.setCustomName(Main.getPlayerHandler(target).rankToString() + Main.getPlayerHandler(target).getNickname());
							target.setPlayerListName(Main.getPlayerHandler(target).rankToString() + Main.getPlayerHandler(target).getNickname());
						}
					} catch (NumberFormatException ex){
						player.sendMessage(Main.getColorHandler().nonNumber);
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