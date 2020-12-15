/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
 */

package org.behindbars.gamecore.donation.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.behindbars.gamecore.Main;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DiscoArmorCMD extends Command {

	private static String name = "discoarmor";
	private static String description = "Equip some discord armor and get rockin'";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public DiscoArmorCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;

		if(Main.getPlayerHandler(player).getDonateDiscoArmor() == 0) {
			player.sendMessage(Main.getColorHandler().noPermission);
		}else if(player.getGameMode() == GameMode.CREATIVE) {
			player.sendMessage(Main.getColorHandler().error + "You can not run this command in creative mode!");
		}else if(Main.getPlayerHandler(player).getDonateDiscoArmorStatus() == 1) {
			player.getInventory().setHelmet(new ItemStack(Material.AIR));
			player.getInventory().setChestplate(new ItemStack(Material.AIR));
			player.getInventory().setLeggings(new ItemStack(Material.AIR));
			player.getInventory().setBoots(new ItemStack(Material.AIR));
			player.sendMessage(Main.getColorHandler().donation + "Disabled Disco Armor!");
			Main.getPlayerHandler(player).setDonateDiscoArmorStatus(0);
		}else if(player.getInventory().getHelmet() != null
				|| player.getInventory().getChestplate() != null
				|| player.getInventory().getLeggings() != null
				|| player.getInventory().getBoots() != null) {
			player.sendMessage(Main.getColorHandler().donation + "You need to take off all your armor!");
		}else {
			ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
			ItemMeta helmMeta = helm.getItemMeta();
			helmMeta.setDisplayName(Main.getColorHandler().message + "Disco");
			helm.setItemMeta(helmMeta);
	
			ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
			ItemMeta chestplateMeta = chestplate.getItemMeta();
			chestplateMeta.setDisplayName(Main.getColorHandler().message + "Disco");
			chestplate.setItemMeta(chestplateMeta);
	
			ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
			ItemMeta leggingsMeta = leggings.getItemMeta();
			leggingsMeta.setDisplayName(Main.getColorHandler().message + "Disco");
			leggings.setItemMeta(leggingsMeta);
	
			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
			ItemMeta bootsMeta = boots.getItemMeta();
			bootsMeta.setDisplayName(Main.getColorHandler().message + "Disco");
			boots.setItemMeta(bootsMeta);
	
			player.getInventory().setHelmet(helm);
			player.getInventory().setChestplate(chestplate);
			player.getInventory().setLeggings(leggings);
			player.getInventory().setBoots(boots);
	
			Main.getPlayerHandler(player).setDonateDiscoArmorStatus(1);
			player.sendMessage(Main.getColorHandler().donation + "Enabled Disco Armor!");
		}
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		return list;
	}

}
