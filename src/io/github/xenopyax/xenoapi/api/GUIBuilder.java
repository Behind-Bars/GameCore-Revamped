/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package io.github.xenopyax.xenoapi.api;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

// TODO: Auto-generated Javadoc
/**
 * The Class GUIBuilder.
 */
public class GUIBuilder {
	
	/** The inv. */
	private Inventory inv;
	
	/** The title. */
	private String title;
	
	/**
	 * Instantiates a new GUI builder.
	 *
	 * @param title the title
	 * @param size the size
	 */
	public GUIBuilder(String title, int size) {
		inv = Bukkit.createInventory(null, size, title);
		this.title = title;
	}
	
	/**
	 * Instantiates a new GUI builder.
	 *
	 * @param owner the owner
	 * @param title the title
	 * @param size the size
	 */
	public GUIBuilder(InventoryHolder owner, String title, int size) {
		inv = Bukkit.createInventory(owner, size, title);
		this.title = title;
	}
	
	/**
	 * Sets the title.
	 *
	 * @param title the title
	 * @return the GUI builder
	 */
	public GUIBuilder setTitle(String title) {
		Inventory tempInv = Bukkit.createInventory(inv.getHolder(), inv.getSize(), title);
		this.title = title;
		for(int i = 0; i < inv.getSize(); i++) {
			if(inv.getItem(i) != null) {
				tempInv.setItem(i, inv.getItem(i));
			}
		}
		inv = tempInv;
		return this;
	}
	
	/**
	 * Sets the size.
	 *
	 * @param size the size
	 * @return the GUI builder
	 */
	public GUIBuilder setSize(int size) {
		Inventory tempInv = Bukkit.createInventory(inv.getHolder(), size, title);
		for(int i = 0; i < inv.getSize(); i++) {
			if(inv.getItem(i) != null) {
				tempInv.setItem(i, inv.getItem(i));
			}
		}
		inv = tempInv;
		return this;
	}
	
	/**
	 * Adds the item.
	 *
	 * @param index the index
	 * @param item the item
	 * @return the GUI builder
	 */
	public GUIBuilder addItem(int index, ItemStack item) {
		inv.setItem(index, item);
		return this;
	}
	
	/**
	 * Builds the.
	 *
	 * @param fill the fill
	 * @return the inventory
	 */
	public Inventory build(boolean fill) {
		if(fill) {
			for(int i = 0; i < inv.getSize(); i++) {
				if(inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR) {
					addItem(i, new XItem().setType(Material.BLACK_STAINED_GLASS_PANE).setName("§0").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
				}
			}
		}
				
		return inv;
	}
	
}
