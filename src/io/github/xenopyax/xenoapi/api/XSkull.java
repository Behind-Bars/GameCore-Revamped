/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package io.github.xenopyax.xenoapi.api;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.server.v1_16_R2.NBTTagCompound;

// TODO: Auto-generated Javadoc
/**
 * The Class XSkull.
 */
public class XSkull {
	
	/** The skull. */
	private ItemStack skull;
	
	/** The meta. */
	private SkullMeta meta;
	
	/**
	 * Instantiates a new x skull.
	 */
	public XSkull() {
		skull = new ItemStack(Material.PLAYER_HEAD);
		meta = (SkullMeta) skull.getItemMeta();
	}
	
	/**
	 * Sets the lore.
	 *
	 * @param lore the lore
	 * @return the x skull
	 */
	public XSkull setLore(List<String> lore) {
		meta.setLore(lore);
		return this;
	}
	
	/**
	 * Sets the amount.
	 *
	 * @param amount the amount
	 * @return the x skull
	 */
	public XSkull setAmount(Integer amount) {
		skull.setAmount(amount);
		return this;
	}
	
	/**
	 * Sets the itemflag.
	 *
	 * @param itemFlags the item flags
	 * @return the x skull
	 */
	public XSkull setItemflag(ItemFlag... itemFlags) {
		meta.addItemFlags(itemFlags);
		return this;
	}
	
	/**
	 * Adds the enchantment.
	 *
	 * @param ench the ench
	 * @param level the level
	 * @return the x skull
	 */
	public XSkull addEnchantment(Enchantment ench, int level) {
		meta.addEnchant(ench, level, true);
		return this;
	}
	
	/**
	 * Sets the owner.
	 *
	 * @param owner the owner
	 * @return the x skull
	 */
	public XSkull setOwner(Player owner) {
		meta.setOwningPlayer(owner);
		return this;
	}
	
	/**
	 * Sets the owner.
	 *
	 * @param owner the owner
	 * @return the x skull
	 */
	public XSkull setOwner(String owner) {
		try {
			URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + owner);
        	InputStreamReader reader = new InputStreamReader(url.openStream());
        	String uuid = new JsonParser().parse(reader).getAsJsonObject().get("id").getAsString();
			url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
			reader = new InputStreamReader(url.openStream());
			JsonObject textureProperty = new JsonParser().parse(reader).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
			String texture = textureProperty.get("value").getAsString();
			String signature = textureProperty.get("signature").getAsString();
			setSkin(texture, signature);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	/**
	 * Sets the skin.
	 *
	 * @param texture the texture
	 * @param signature the signature
	 * @return the x skull
	 */
	public XSkull setSkin(String texture, String signature) {
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		profile.getProperties().put("textures", new Property("textures", texture, signature));
		Field profileField = null;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
		return this;
	}
	
	/**
	 * Builds the.
	 *
	 * @return the item stack
	 */
	public ItemStack build() {
		skull.setItemMeta(meta);
		return skull;
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
