/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.economy.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.behindbars.gamecore.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class EconomyCMD extends Command {

	private static String name = "economy";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("eco");

	public EconomyCMD() {
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
	        Player target = Bukkit.getPlayerExact(args[1]);
	        if(target == null) {
	            player.sendMessage(Main.getColorHandler().offlinePlayer + "This player is offline!");
	        }else {
		        try {
		            int num = Integer.parseInt(args[2]);
		            
		            if(args[0].equalsIgnoreCase("add")) {
			            player.sendMessage(Main.getColorHandler().main + "Balance: " + Main.getColorHandler().message + "Added $" + String.valueOf(args[2]) + " to " + target.getName());
			            Main.getPlayerHandler(target).addMoney(Integer.parseInt(args[2]));
			        }else if(args[0].equalsIgnoreCase("remove")) {
			            if(num > Main.getPlayerHandler(target).getMoney()) {
			                player.sendMessage(Main.getColorHandler().main + "Balance: " + Main.getColorHandler().message + target.getName() 
			                	+ " does not have that much money to remove!");
			            }else {	
			            	player.sendMessage(Main.getColorHandler().main + "Balance: " + Main.getColorHandler().message 
			            			+ "Removed $" + String.valueOf(args[2]) + " to " + target.getName());
			            	
			            	Main.getPlayerHandler(target).removeMoney(Integer.parseInt(args[2]));
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
