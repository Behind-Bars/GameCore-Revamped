/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.events;

import org.behindbars.gamecore.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import net.md_5.bungee.api.ChatColor;

public class PlayerChats implements Listener {
	
	@EventHandler
	public void onCmd(PlayerCommandPreprocessEvent e) {
		System.out.println(e.getMessage().split(" ")[0]);
		if(e.getMessage().split(" ")[0].equalsIgnoreCase("/me")) e.setCancelled(true);
		if(e.getMessage().split(" ")[0].equalsIgnoreCase("/minecraft:me")) e.setCancelled(true);
	}

	String guardChat = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_BLUE + "Guard Chat" + ChatColor.DARK_GRAY + "] ";
	String modChat = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "Mod Chat" + ChatColor.DARK_GRAY + "] ";


	@EventHandler
	public void chat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		String message = event.getMessage();

		if(Main.getPlayerHandler(player).getAcceptRules() == 0) {
			event.getRecipients().remove(player);
			event.setCancelled(true);
			return;
		}else if(Main.getPlayerHandler(player).getRank() > 5 && message.startsWith("#")) {
			event.setCancelled(true);
			for(Player online : Bukkit.getOnlinePlayers()) {
				if(Main.getPlayerHandler(online).getRank() > 5) {
					online.sendMessage(guardChat + Main.getColorHandler().message + player.getName() + Main.getColorHandler().main + ": " + Main.getColorHandler().message
							+ event.getMessage().replaceFirst("#", ""));
				}
			}
			return;
		}else if(Main.getPlayerHandler(player).getRank() > 7 && (message.startsWith("@") || message.startsWith("!"))) {
			event.setCancelled(true);
			for(Player online : Bukkit.getOnlinePlayers()) {
				if(Main.getPlayerHandler(online).getRank() > 7) {
					online.sendMessage(modChat + Main.getColorHandler().message + player.getName() + Main.getColorHandler().main + ": " + Main.getColorHandler().message
							+ event.getMessage().replaceFirst("!", ""));
				}
			}
			return;
		}

		if(player.getWorld().getName().equalsIgnoreCase("SMP")) {
			if(Main.getPlayerHandler(player).getRank() < 8) {
				event.setFormat(ChatColor.DARK_GRAY + "[SMP] " + ChatColor.WHITE + Main.getPlayerHandler(player).getNickname() + ChatColor.GRAY + ": " + event.getMessage().replaceFirst("%", "%%"));
				return;
			}
			event.setFormat(Main.getPlayerHandler(player).rankToString() + Main.getPlayerHandler(player).getNickname() + ChatColor.GRAY + ": " + event.getMessage().replaceFirst("%", "%%"));
			return;
		}
		
		long elapsedTime = System.currentTimeMillis() - Main.getPlayerHandler(player).getMuteDate();
		long timeUntilReuse = ((24*60*60*1000) - elapsedTime);
		
		if(timeUntilReuse > 0) {
			event.setCancelled(true);
			player.sendMessage(Main.getColorHandler().coolDown + Main.getTimeformatHandler().formatTime(timeUntilReuse));
			player.sendMessage(Main.getColorHandler().main+ "Mute Information: " +Main.getColorHandler().message + Main.getPlayerHandler(player).getMutedReason());
			return;
		}else if(Main.getPlayerHandler(player).getFilter() == 0) {
			String[] badWords = {"fuck", "shit", "nigger", "cunt", "bitch", "gay", "chink", "dyke", "pussy", "penis", "sex", "dick", "porn", "nigga", "ass", "whore", "tit", "cum", "wanker", "prick"};

		/*	for(String word : badWords) {
				StringBuilder string = new StringBuilder();
				for (int x = 0; x < word.length(); x++) {
					string.append("*");
				}
				message = message.replaceAll(word, string.toString());
			} */
			event.setFormat(Main.getPlayerHandler(player).rankToString() + Main.getPlayerHandler(player).getNickname() + ChatColor.GRAY + ": " + message.replaceFirst("%", "%%"));
			return;
		}
		event.setFormat(Main.getPlayerHandler(player).rankToString() + Main.getPlayerHandler(player).getNickname() + ChatColor.GRAY + ": " + event.getMessage().replaceFirst("%", "%%"));
		return;
	}

}
