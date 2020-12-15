/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
 */

package org.behindbars.gamecore.easteregg.events;

import org.behindbars.gamecore.easteregg.util.EasterEggGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

public class EasterEggGUIEvent {

	EasterEggGUI gui = new EasterEggGUI();

	@EventHandler
	public void inventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();

		if (event.getView().getTitle().equalsIgnoreCase("Easter Eggs")) {
			event.setCancelled(true);
			if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType().equals(Material.AIR))) {
				return;
			}else if (event.getCurrentItem().getType().equals(Material.YELLOW_DYE)) {
				player.closeInventory();
				gui.easterEggCharlieInventory(player);
				return;
			}else if (event.getCurrentItem().getType().equals(Material.GREEN_DYE)) {
				player.closeInventory();
				gui.easterEggBravoInventory(player);
				return;
			}else if (event.getCurrentItem().getType().equals(Material.RED_DYE)) {
				player.closeInventory();
				gui.easterEggAlphaInventory(player);
				return;
			}else if (event.getCurrentItem().getType().equals(Material.PURPLE_DYE)) {
				player.closeInventory();
				gui.easterEggEliteInventory(player);
				return;
			}else if (event.getCurrentItem().getType().equals(Material.GRAY_DYE)) {
				player.closeInventory();
				gui.easterEggCenterInventory(player);
				return;
			}
		}
	}

}
