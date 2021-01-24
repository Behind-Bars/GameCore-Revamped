/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
 */

package org.behindbars.gamecore.core.events;

import org.behindbars.gamecore.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityAttackEntity implements Listener {

	@EventHandler
	public void playerAttack(EntityDamageByEntityEvent event) {
		if(event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			Player damaged = (Player) event.getEntity();
			Player damager = (Player) event.getDamager();

			if(Main.getPlayerHandler(damaged).getAcceptRules() == 0) {
				event.setCancelled(true);
				damager.sendMessage(Main.getColorHandler().error + "This player has their pvp disabled!");
				return;
			}

			if(event.isCancelled()) {
				return;
			}

			Main.getPlayerHandler(damaged).setCombatLogTime(10);
			Main.getPlayerHandler(damager).setCombatLogTime(10);

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
