/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.commands.token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.behindbars.gamecore.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class TokenEconomyCMD extends Command {

	private static String name = "tokeneconomy";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("tokeneco");

	public TokenEconomyCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if(Main.getPlayerHandler(player).getRank() < 10) {
			player.sendMessage(Main.getColorHandler().noPermission);
		}else if(args.length != 4) {
			player.sendMessage(Main.getColorHandler().usage + args[0] + " add/remove <player> <amount>");
		}else {
			Player target = Bukkit.getPlayer(args[2]);
			if(target == null) {
				player.sendMessage(Main.getColorHandler().offlinePlayer + "This player is offline!");
			}else {
				try {
					int num = Integer.parseInt(args[3]);
		
					if(args[1].equalsIgnoreCase("add")) {
						player.sendMessage(Main.getColorHandler().main + "Token: " + Main.getColorHandler().message + "Added " + String.valueOf(args[3]) + " to " + target.getName());
						Main.getPlayerHandler(target).addToken(Integer.parseInt(args[3]));
					}else if(args[1].equalsIgnoreCase("remove")) {
						if(num > Main.getPlayerHandler(target).getToken()) {
							player.sendMessage(Main.getColorHandler().main + "Token: " + Main.getColorHandler().message + target.getName() + " does not have that many tokens to remove!");
						}else {
							player.sendMessage(Main.getColorHandler().main + "Token: " + Main.getColorHandler().message + "Removed " + String.valueOf(args[3]) + " to " + target.getName());
							Main.getPlayerHandler(target).removeToken(Integer.parseInt(args[3]));
						}
					}
				} catch (NumberFormatException ex){
					player.sendMessage(Main.getColorHandler().nonNumber);
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