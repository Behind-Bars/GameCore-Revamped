/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package io.github.xenopyax.xenoapi.api;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import io.github.xenopyax.xenoapi.interfaces.IQuestion;

// TODO: Auto-generated Javadoc
/**
 * The Class QuestionAPI.
 */
public class QuestionAPI implements Listener {
	
	/** The questioned players. */
	private HashMap<UUID, IQuestion> questionedPlayers = new HashMap<>();
	
	/**
	 * Instantiates a new question API.
	 *
	 * @param plugin the plugin
	 */
	protected QuestionAPI(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	/**
	 * Ask.
	 *
	 * @param player the player
	 * @param question the question
	 * @param questionHandler the question handler
	 */
	public void ask(Player player, String question, IQuestion questionHandler) {
		questionedPlayers.put(player.getUniqueId(), questionHandler);
		player.sendMessage(question);
	}
	
	/**
	 * On message.
	 *
	 * @param event the event
	 */
	@EventHandler
	public void onMessage(AsyncPlayerChatEvent event) {
		if(questionedPlayers.containsKey(event.getPlayer().getUniqueId())) {
			questionedPlayers.get(event.getPlayer().getUniqueId()).onAnswer(event.getMessage());
		}
	}
}
