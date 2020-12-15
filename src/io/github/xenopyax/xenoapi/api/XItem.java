/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package io.github.xenopyax.xenoapi.api;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_16_R2.NBTTagCompound;

// TODO: Auto-generated Javadoc
/**
 * The Class XItem.
 */
public class XItem {
	
	/** The item. */
	private ItemStack item;
	
	/** The meta. */
	private ItemMeta meta;
	
	/**
	 * Instantiates a new x item.
	 */
	public XItem() {
		this.item = new ItemStack(Material.STICK);
		meta = item.getItemMeta();
	}
	
	/**
	 * Instantiates a new x item.
	 *
	 * @param item the item
	 */
	public XItem(ItemStack item) {
		this.item = item;
		meta = item.getItemMeta();
	}
	
	/**
	 * Sets the type.
	 *
	 * @param material the material
	 * @return the x item
	 */
	// ItemStack
	public XItem setType(Material material) {
		item.setType(material);
		item.setItemMeta(meta);
		return this;
	}
	
	/**
	 * Adds the enchantment.
	 *
	 * @param ench the ench
	 * @param level the level
	 * @return the x item
	 */
	public XItem addEnchantment(Enchantment ench, int level) {
		meta.addEnchant(ench, level, true);
		item.setItemMeta(meta);
		return this;
	}
	
	/**
	 * Sets the amount.
	 *
	 * @param amount the amount
	 * @return the x item
	 */
	public XItem setAmount(int amount) {
		item.setAmount(amount);
		item.setItemMeta(meta);
		return this;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the name
	 * @return the x item
	 */
	// Meta data
	public XItem setName(String name) {
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return this;
	}
	
	/**
	 * Sets the lore.
	 *
	 * @param lore the lore
	 * @return the x item
	 */
	public XItem setLore(List<String> lore) {
		meta.setLore(lore);
		item.setItemMeta(meta);
		return this;
	}
	
	/**
	 * Adds the item flag.
	 *
	 * @param itemFlags the item flags
	 * @return the x item
	 */
	public XItem addItemFlag(ItemFlag... itemFlags) {
		meta.addItemFlags(itemFlags);
		item.setItemMeta(meta);
		System.out.println(meta.toString());
		return this;
	}
	
	/**
	 * Sets the durability.
	 *
	 * @param damage the damage
	 * @return the x item
	 */
	public XItem setDurability(int damage) {
    	Damageable d = (Damageable)meta;
    	if(damage > item.getType().getMaxDurability()) {
    		d.setDamage(item.getType().getMaxDurability());
    	}else {
        	Integer dmg = damage - item.getType().getMaxDurability();
    		d.setDamage(dmg);
    	}
    	meta = (ItemMeta) d;
		item.setItemMeta(meta);
    	return this;
    }
	
	/**
	 * Builds the.
	 *
	 * @return the item stack
	 */
	public ItemStack build() {
		return item;
	}
	
	/**
	 * Sets the hidden data.
	 *
	 * @param item the item
	 * @param key the key
	 * @param value the value
	 * @return the item stack
	 */
	public static ItemStack setHiddenData(ItemStack item, String key, Object value) {
		net.minecraft.server.v1_16_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemcompound = (nmsItem.hasTag()) ? nmsItem.getTag() : new NBTTagCompound();
		itemcompound.setString(key, value.toString());
		nmsItem.setTag(itemcompound);
		item = CraftItemStack.asBukkitCopy(nmsItem);
		return item;
	}
	
	/**
	 * Gets the nbt.
	 *
	 * @param item the item
	 * @return the nbt
	 */
	public static NBTTagCompound getNBT(ItemStack item) {
		net.minecraft.server.v1_16_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemcompound = (nmsItem.hasTag()) ? nmsItem.getTag() : new NBTTagCompound();
		return itemcompound;
	}

}
