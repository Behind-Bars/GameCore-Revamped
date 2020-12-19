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
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class ContrabandCMD extends Command {

	private static String name = "contraban";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("cb");

	public ContrabandCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if (Main.getPlayerHandler(player).getRank() > 6) {
            player.sendMessage(Main.getColorHandler().noPermission);
        }else if (args.length < 3) {
            player.sendMessage(Main.getColorHandler().usage + args[0] + " <player> <sword/bow>");
        }else {
	        Player target = Bukkit.getPlayerExact(args[1]);
	        if (target == null) {
	            player.sendMessage(Main.getColorHandler().offlinePlayer + "This player is offline!");
	        }else {	
		        if (args[2].equalsIgnoreCase("sword")) {
		        	sendDropMessage(player, target, "sword");
		        }else if (args[2].equalsIgnoreCase("bow")) {
		        	sendDropMessage(player, target, "Bow");
		        }else {
		        	player.sendMessage(Main.getColorHandler().usage + args[0] + " <player> <sword/bow>");
		        }
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

	public void sendDropMessage(Player player, Player p, String msg) {
        new BukkitRunnable() {
        	int seconds = 5;
            @Override
            public void run() {
            	if(seconds <= 0) cancel();
            	if(seconds == 5) p.sendMessage(Main.getColorHandler().main + "Contraban: " + Main.getColorHandler().message + "You must drop your sword to "  + player.getName() + " or you will be jailed!");
                p.sendMessage(Main.getColorHandler().main + "Contraban: " + Main.getColorHandler().message + seconds + " seconds to drop your " 
                		+ ChatColor.RED + msg  +Main.getColorHandler().message+ " to "+ChatColor.YELLOW + player.getName() );
                player.sendMessage(Main.getColorHandler().main + "Contraban: " + Main.getColorHandler().message + p.getName() + " Has " + seconds + " seconds to drop their " + ChatColor.RED + msg);
                seconds--;
            }
        }.runTaskTimer(Main.getInstance(), 0, 20);
    }
}