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
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class SpawnCMD extends Command {

	private static String name = "spawn";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public SpawnCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if(Main.getPlayerHandler(player).getDonateSpawn() == 0) {
            player.sendMessage(Main.getColorHandler().noPermission);
        }else if(Main.getPlayerHandler(player).getJailTime() > 0) {
            player.sendMessage(Main.getColorHandler().error + "You can't /spawn in jail!");
        }else {
	        Location spawn = new Location(Bukkit.getWorld("world"), -0.5,125,-0.5);
	        player.teleport(spawn);
	        player.sendMessage(Main.getColorHandler().donation + "Teleporting to Spawn!");
	    }
        return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		return list;
	}

}