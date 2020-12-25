/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.behindbars.gamecore.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import net.md_5.bungee.api.ChatColor;

public class RedstoneLimit {

	private ConsoleCommandSender console;
	private int redstoneLimit;
	private final Map<UUID, Boolean> playersBypassing = new HashMap<UUID, Boolean>();
	private boolean restrict;
	private List<String> restrictedBlocks = new ArrayList<String>();

	public RedstoneLimit() {
		console = Bukkit.getConsoleSender();

		this.redstoneLimit = Main.getInstance().getConfig().getInt("redstone_limit");
		this.restrict = Main.getInstance().getConfig().getBoolean("restrict");
		this.restrictedBlocks = Main.getInstance().getConfig().getStringList("restricted");

		Main.getInstance().saveDefaultConfig();

		console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Enabled &cRedstoneLimit&3!"));
	}

	public void onDisable() {
		console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cDisabled &3RedstoneLimit&c!"));
	}

	public int getRedstoneLimit() {
		return this.redstoneLimit;
	}

	public void setRedstoneLimit(int limit) {
		this.redstoneLimit = limit;
		Main.getInstance().getConfig().set("redstone_limit", redstoneLimit);
		Main.getInstance().saveConfig();
	}


	public Boolean getPlayerBypass(UUID player) {
		if (this.playersBypassing.containsKey(player)) return this.playersBypassing.get(player);
		return false;
	}

	public void setPlayerBypass(UUID player, boolean bypass) {
		this.playersBypassing.put(player, bypass);
	}

	public boolean restrict() {
		return this.restrict;
	}

	public List<String> getRestrictedBlocks() {
		return this.restrictedBlocks;
	}

}
