/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.events;

import org.behindbars.gamecore.Main;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class CraftItem implements Listener {
	
	@EventHandler
	public void craftItem(PrepareItemCraftEvent e) {
		if(e.getRecipe() == null) return;
		Material itemType = e.getRecipe().getResult().getType();
		
		if (itemType==Material.ENDER_CHEST
				||itemType==Material.ANVIL
				||itemType==Material.HOPPER
				||itemType==Material.BEACON
				||itemType==Material.DIAMOND_HELMET
				||itemType==Material.DIAMOND_CHESTPLATE
				||itemType==Material.DIAMOND_LEGGINGS
				||itemType==Material.DIAMOND_BOOTS
				||itemType==Material.DIAMOND_SWORD
				||itemType==Material.GOLDEN_APPLE
				||itemType==Material.NETHERITE_HELMET
				||itemType==Material.NETHERITE_BOOTS
				||itemType==Material.NETHERITE_LEGGINGS
				||itemType==Material.NETHERITE_CHESTPLATE
				||itemType==Material.NETHERITE_SWORD) {

			e.getInventory().setResult(new ItemStack(Material.AIR));
			for(HumanEntity he:e.getViewers()) {
				if(he instanceof Player) {
					((Player)he).sendMessage(Main.getColorHandler().error+"You cannot craft this!");
				}
			}
			return;
		}
	}

}
