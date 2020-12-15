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
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class RankupCMD extends Command {

	private static String name = "rankup";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public RankupCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if(Main.getPlayerHandler(player).getRank() >= 5) {
			player.sendMessage(Main.getColorHandler().main + "Rank: " + Main.getColorHandler().message + "Your rank can not rankup!");
		}else if(Main.getPlayerHandler(player).getRank() == 1) {
			if(Main.getPlayerHandler(player).getMoney() < 10000) {
				player.sendMessage(Main.getColorHandler().main + "Rank: " + Main.getColorHandler().message + "You do not have enough money to rankup!");
			}else {
				Main.getPlayerHandler(player).removeMoney(10000);
				Main.getPlayerHandler(player).setRank(2);
			}
		}else if(Main.getPlayerHandler(player).getRank() == 2) {
			if(Main.getPlayerHandler(player).getMoney() < 20000) {
				player.sendMessage(Main.getColorHandler().main + "Rank: " + Main.getColorHandler().message + "You do not have enough money to rankup!");
			} else {
				Main.getPlayerHandler(player).removeMoney(20000);
				Main.getPlayerHandler(player).setRank(3);
			}
		}else if(Main.getPlayerHandler(player).getRank() == 3) {
			if(Main.getPlayerHandler(player).getMoney() < 50000) {
				player.sendMessage(Main.getColorHandler().main + "Rank: " + Main.getColorHandler().message + "You do not have enough money to rankup!");
			}else {
				Main.getPlayerHandler(player).removeMoney(50000);
				Main.getPlayerHandler(player).setRank(4);
			}
		}else if(Main.getPlayerHandler(player).getRank() == 4) {
			if(Main.getPlayerHandler(player).getMoney() < 100000) {
				player.sendMessage(Main.getColorHandler().main + "Rank: " + Main.getColorHandler().message + "You do not have enough money to rankup!");
			}else {
				Main.getPlayerHandler(player).removeMoney(100000);
				Main.getPlayerHandler(player).setRank(5);
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