/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
 */

package org.behindbars.gamecore.core.events;

import org.behindbars.gamecore.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class EntityAttackEntity implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public void playerAttack(EntityDamageByEntityEvent event) {
		if(event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			Player damaged = (Player) event.getEntity();
			Player damager = (Player) event.getDamager();

			/*if(Main.getPlayerHandler(damaged).getAcceptRules() == 0) {
				event.setCancelled(true);
				damager.sendMessage(Main.getColorHandler().error + "This player has their pvp disabled!");
				return;
			}*/
			
			if(event.isCancelled()) {
				event.setCancelled(true);
				return;
			}
			
			Main.getPlayerHandler(damaged).setCombatLogTime(10);
			Main.getPlayerHandler(damager).setCombatLogTime(10);
			damaged.setGlowing(true);
			damager.setGlowing(true);
			damaged.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§c§lYou've entered combat"));
			damager.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§c§lYou've entered combat"));

			if(Main.getPlayerHandler(damaged).getPvpStatus() == 1) {
				event.setCancelled(true);
				damager.sendMessage(Main.getColorHandler().error + "This player has their pvp disabled!");
				return;
			}

			if(Main.getPlayerHandler(damager).getPvpStatus() == 1) {
				event.setCancelled(true);
				damager.sendMessage(Main.getColorHandler().error + "You have your pvp disabled!");
				return;
			}

		}
	}

}
