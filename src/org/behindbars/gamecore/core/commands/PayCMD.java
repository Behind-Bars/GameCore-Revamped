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
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class PayCMD extends Command {

	private static String name = "pay";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public PayCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if(args.length < 3) {
            player.sendMessage(Main.getColorHandler().usage + args[0] + " <player> <amount>");
        }else {
	        Player target = Bukkit.getPlayer(args[1]);
	        
	        if(target == null) {
	            player.sendMessage(Main.getColorHandler().offlinePlayer + "This player is offline!");
	        }else {
		        try {
		        	int num = Integer.parseInt(args[2]);
		
			        if(Main.getPlayerHandler(player).getMoney() < Integer.parseInt(args[2])) {
			            player.sendMessage(Main.getColorHandler().main + "Balance: " + Main.getColorHandler().message + "You do not have enough money!");
			        }else {
			        Main.getPlayerHandler(player).removeMoney(num);
			        Main.getPlayerHandler(target).addMoney(num);
			        player.sendMessage(Main.getColorHandler().main + "Balance: " + Main.getColorHandler().message + "You have payed " + num + " to " + target.getName() +"!");
			        target.sendMessage(Main.getColorHandler().main + "Balance: " + Main.getColorHandler().message + "You have been payed " + num + " by " + player.getName() +"!");
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