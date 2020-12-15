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

public class DisposeCMD extends Command {

	private static String name = "dispose";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("trash");

	public DisposeCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender)
			return true;
		Player player = (Player) sender;
		
		if(Main.getPlayerHandler(player).getDonateFeed() == 0) {
            player.sendMessage(Main.getColorHandler().noPermission);
        }else {
        	player.openInventory(Bukkit.createInventory(null, 54, Main.getColorHandler().main + " Trash"));
        }
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		return list;
	}

}
