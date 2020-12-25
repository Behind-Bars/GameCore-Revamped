/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
 */

package org.behindbars.gamecore.core.events;

import org.behindbars.gamecore.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class PickupItem implements Listener {

	@EventHandler
	public void onPickup(EntityPickupItemEvent event) {
		if(event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			player.updateInventory();

			if (Main.getPlayerHandler(player).getRank() < 6) {
				if (event.getItem().getItemStack().getType().equals(Material.CHAINMAIL_HELMET)
						|| event.getItem().getItemStack().getType().equals(Material.CHAINMAIL_CHESTPLATE)
						|| event.getItem().getItemStack().getType().equals(Material.CHAINMAIL_LEGGINGS)
						|| event.getItem().getItemStack().getType().equals(Material.CHAINMAIL_BOOTS)
						|| event.getItem().getItemStack().getType().equals(Material.DIAMOND_SWORD)
						|| event.getItem().getItemStack().getItemMeta().getDisplayName().contains("Guard")
						|| event.getItem().getItemStack().getItemMeta().getDisplayName().contains("Disco")) {
					event.setCancelled(true);
					return;
				}
			}
			player.updateInventory();
		}

	}
}