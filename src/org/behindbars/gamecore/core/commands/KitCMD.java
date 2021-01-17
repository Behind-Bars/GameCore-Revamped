/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.behindbars.gamecore.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class KitCMD extends Command {

	private static String name = "kit";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public KitCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if (!(args.length == 1)) {
            player.sendMessage(Main.getColorHandler().usage + "/kit <name>");
        }else if(args[0].equalsIgnoreCase("tools")) {
			long elapsedTime = System.currentTimeMillis() - Main.getPlayerHandler(player).getKitToolsDate();
			long timeUntilReuse = ((24 * 60 * 60 * 1000) - elapsedTime);

			if (timeUntilReuse > 0) {
				player.sendMessage(Main.getColorHandler().coolDown + Main.getTimeformatHandler().formatTime(timeUntilReuse));
			} else {
				player.sendMessage(Main.getColorHandler().main + "Kit: " + Main.getColorHandler().message + "Received kit Tools!");
				Main.getPlayerHandler(player).setKitToolsDate();
				player.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE));
				player.getInventory().addItem(new ItemStack(Material.IRON_AXE));
			}

		}else if(args[0].equalsIgnoreCase("food")) {
			long elapsedTime = System.currentTimeMillis() - Main.getPlayerHandler(player).getKitToolsDate();
			long timeUntilReuse = ((24 * 60 * 60 * 1000) - elapsedTime);

			if (timeUntilReuse > 0) {
				player.sendMessage(Main.getColorHandler().coolDown + Main.getTimeformatHandler().formatTime(timeUntilReuse));
			}else {
				player.sendMessage(Main.getColorHandler().main + "Kit: " + Main.getColorHandler().message + "Received kit Tools!");
				Main.getPlayerHandler(player).setKitToolsDate();
				player.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE));
				player.getInventory().addItem(new ItemStack(Material.IRON_AXE));
			}

        }else if(args[0].equalsIgnoreCase("guard")) {

            if(Main.getPlayerHandler(player).getRank() < 6) {
                player.sendMessage(Main.getColorHandler().noPermission);
            }else {
	            player.sendMessage(Main.getColorHandler().main + "Kit: " + Main.getColorHandler().message + "Received kit Guard!");
	
	            ItemStack guardHelm = new ItemStack(Material.CHAINMAIL_HELMET);
	            ItemMeta guardHelmMeta = guardHelm.getItemMeta();
	            guardHelmMeta.setDisplayName(ChatColor.of("#5E95F0") + "Guard Helmet");
	            guardHelm.setItemMeta(guardHelmMeta);
	            guardHelm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	            guardHelm.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 4);
	            guardHelm.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
	            guardHelm.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
	            player.getInventory().addItem(guardHelm);
	
	            ItemStack guardChestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
	            ItemMeta guardChestplateMeta = guardChestplate.getItemMeta();
	            guardChestplateMeta.setDisplayName(ChatColor.of("#5E95F0") + "Guard Chestplate");
	            guardChestplate.setItemMeta(guardChestplateMeta);
	            guardChestplate.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	            guardChestplate.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 4);
	            guardChestplate.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
	            guardChestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
	            player.getInventory().addItem(guardChestplate);
	
	            ItemStack guardLeggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
	            ItemMeta guardLegginsMeta = guardLeggings.getItemMeta();
	            guardLegginsMeta.setDisplayName(ChatColor.of("#5E95F0") + "Guard Leggings");
	            guardLeggings.setItemMeta(guardLegginsMeta);
	            guardLeggings.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	            guardLeggings.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 4);
	            guardLeggings.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
	            guardLeggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
	            player.getInventory().addItem(guardLeggings);
	
	            ItemStack guardBoots = new ItemStack(Material.CHAINMAIL_BOOTS);
	            ItemMeta guardBootsMeta = guardBoots.getItemMeta();
	            guardBootsMeta.setDisplayName(ChatColor.of("#5E95F0") + "Guard Boots");
	            guardBoots.setItemMeta(guardBootsMeta);
	            guardBoots.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	            guardBoots.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 4);
	            guardBoots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
	            guardBoots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
	            player.getInventory().addItem(guardBoots);
	
	            ItemStack guardSword = new ItemStack(Material.DIAMOND_SWORD);
	            ItemMeta guardSwordMeta = guardSword.getItemMeta();
	            guardSwordMeta.setDisplayName(ChatColor.of("#5E95F0") + "Guard Sword");
	            guardSword.setItemMeta(guardSwordMeta);
	            guardSword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	            guardSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
	            guardSword.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
	            guardSword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
	            player.getInventory().addItem(guardSword);
	
	            ItemStack bow = new ItemStack(Material.BOW);
	            ItemMeta bowMeta = bow.getItemMeta();
	            bowMeta.setDisplayName(ChatColor.of("#5E95F0") + "Guard Bow");
	            bow.setItemMeta(bowMeta);
	            bow.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	            bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 3);
	            bow.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
	            player.getInventory().addItem(bow);
	
	            ItemStack arrow = new ItemStack(Material.ARROW);
	            ItemMeta arrowMeta = arrow.getItemMeta();
	            arrowMeta.setDisplayName(ChatColor.of("#5E95F0") + "Guard Arrow");
	            arrow.setItemMeta(arrowMeta);
	            arrow.setAmount(128);
	            player.getInventory().addItem(arrow);
	
	            ItemStack cookedbeef = new ItemStack(Material.COOKED_CHICKEN);
	            ItemMeta cookedBeefMeta = cookedbeef.getItemMeta();
	            cookedBeefMeta.setDisplayName(ChatColor.of("#5E95F0") + "Guard Food");
	            cookedbeef.setItemMeta(cookedBeefMeta);
	            cookedbeef.setAmount(64);
	            player.getInventory().addItem(cookedbeef);
	        }
        }else if(args[0].equalsIgnoreCase("list")) {
	        if(Main.getPlayerHandler(player).getRank() < 6) {
	        	player.sendMessage(Main.getColorHandler().main + "Kit: " + Main.getColorHandler().message + "Tools");
	        }else {
	        	player.sendMessage(Main.getColorHandler().main + "Kit: " + Main.getColorHandler().message + "Tools and Guard");
	        }
	    }
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		if(args.length == 1) list.addAll(Arrays.asList("list", "guard", "tools", "kit"));
		return list;
	}

}