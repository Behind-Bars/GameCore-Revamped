/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
 */

package org.behindbars.gamecore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.behindbars.gamecore.core.automation.Announcer;
import org.behindbars.gamecore.core.handlers.ColorHandler;
import org.behindbars.gamecore.core.handlers.CommandHandler;
import org.behindbars.gamecore.core.handlers.EventHandler;
import org.behindbars.gamecore.core.handlers.InfoHandler;
import org.behindbars.gamecore.core.handlers.MineHandler;
import org.behindbars.gamecore.core.handlers.PlayerHandler;
import org.behindbars.gamecore.core.handlers.WarpHandler;
import org.behindbars.gamecore.core.util.RedstoneLimit;
import org.behindbars.gamecore.core.util.TimeFormatHandler;
import org.behindbars.gamecore.smp.CommandWild;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.xenopyax.xenoapi.XenoAPI;

public class Main extends JavaPlugin {

	private static Main instance;
	private static Announcer announcer;

	private static CommandHandler commandHandler;
	private static EventHandler eventHandler;
	private static ColorHandler colorHandler;
	private static TimeFormatHandler timeformatHandler;
	private static MineHandler mineHandler;
	private static WarpHandler warpHandler;
	private static InfoHandler infoHandler;
	private static RedstoneLimit redstoneLimit;
	
	private static XenoAPI api;
	
	private static Map<UUID, PlayerHandler> handlers = new HashMap<>();

	@Override
	public void onEnable() {
		instance = this;
		api = new XenoAPI(this);
		
		commandHandler = new CommandHandler();
		eventHandler = new EventHandler();
		colorHandler = new ColorHandler();
		timeformatHandler =  new TimeFormatHandler();
		announcer = new Announcer();
		mineHandler = new MineHandler();
		warpHandler = new WarpHandler();
		infoHandler = new InfoHandler();
		redstoneLimit = new RedstoneLimit();


	}

	public static Main getInstance() {
		return instance;
	}

	public static CommandHandler getCommandHandler() {
		return commandHandler;
	}

	public static XenoAPI getXenoAPI() {
		return api;
	}

	public static EventHandler getEventHandler() {
		return eventHandler;
	}

	public static ColorHandler getColorHandler() {
		return colorHandler;
	}

	public static Announcer getAnnouncer() {
		return announcer;
	}
	
	public static PlayerHandler getPlayerHandler(Player player) {
		if(handlers.containsKey(player.getUniqueId())) return handlers.get(player.getUniqueId());
		PlayerHandler playerHandler = new PlayerHandler(player);
		handlers.put(player.getUniqueId(), playerHandler);
		return playerHandler;
	}

	private HashMap<Player, Player> tpa = new HashMap<Player, Player>();

	public HashMap<Player,Player> getTpa(){
		return tpa;
	}

	public static TimeFormatHandler getTimeformatHandler() {
		return timeformatHandler;
	}

	public static MineHandler getMineHandler() {
		return mineHandler;
	}

	public static WarpHandler getWarpHandler() {
		return warpHandler;
	}

	public static InfoHandler getInfoHandler() {
		return infoHandler;
	}

	public static RedstoneLimit getRedstoneLimit() {
		return redstoneLimit;
	}

}
