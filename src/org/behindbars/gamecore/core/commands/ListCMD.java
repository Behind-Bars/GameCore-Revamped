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

public class ListCMD extends Command {

	private static String name = "";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public ListCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		StringBuilder str = new StringBuilder();
        for(Player ps : Bukkit.getOnlinePlayers()){
            if(str.length() > 0){
                str.append(", ");
            }
            str.append(Main.getPlayerHandler(ps).rankToColorString()+ ps.getName() + "§7");
        }
        player.sendMessage(Main.getColorHandler().main + "Online Players: " + Main.getColorHandler().message  + Bukkit.getServer().getOnlinePlayers().size());
        player.sendMessage(str.toString());
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		return list;
	}

}
