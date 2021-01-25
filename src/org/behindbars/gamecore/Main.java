/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
 */

package org.behindbars.gamecore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.xenopyax.xenoapi.XenoAPI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

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
		combatLog();
	}

	public void combatLog() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) {
                	
                    if(getPlayerHandler(player).getCombatLogTime() < 1 && getPlayerHandler(player).getCombatLogTime() > -1) {
                    	player.setGlowing(false);
                    	player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§c§lYou're out of combat"));
                    }

                    if(getPlayerHandler(player).getCombatLogTime() >= 0) {
                    	int time = getPlayerHandler(player).getCombatLogTime();
                    	String bar = "----------";
                    	if(time == 10) {
                    		bar = "§c" + bar;
                    	}else if(time >= 0) {
                    		bar = "§c" + bar.substring(0, time) + "§a" +bar.substring(time , bar.length());
                    	}
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§4Combat log: §7[" + bar + "§7]"));
                        Random r = new Random();
                        for (int i = 0; i < 5 ; i++)
                            player.getWorld().spawnParticle(Particle.SMOKE_NORMAL, player.getLocation().add(
                                    r.nextDouble() * 1.2, r.nextDouble() * 1.2, r.nextDouble() * .5), 0);
                        for (int i = 0; i < 5 ; i++)
                            player.getWorld().spawnParticle(Particle.SMOKE_NORMAL, player.getLocation().add(
                                    -1*(r.nextDouble() * 1.2), r.nextDouble() * 1.2, (r.nextDouble() * .5) *-1), 0);
                        getPlayerHandler(player).setCombatLogTime(getPlayerHandler(player).getCombatLogTime() - 1);
                    }

                }
            }
        }, 0L, 20L);
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
	
	public static Map<UUID, PlayerHandler> getHandlers() {
		return handlers;
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
