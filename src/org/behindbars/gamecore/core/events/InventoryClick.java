/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
 */

package org.behindbars.gamecore.core.events;

import org.behindbars.gamecore.Main;
import org.behindbars.gamecore.core.util.Bar;
import org.behindbars.gamecore.core.util.GUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.potion.PotionEffectType;

public class InventoryClick implements Listener {

	private Bar bar;
	
	@EventHandler
	private void inventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();

		if (event.getSlotType() == InventoryType.SlotType.ARMOR) {
			if (event.getCurrentItem().hasItemMeta() == true && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Main.getColorHandler().message + "Disco")) {
				if (event.getCurrentItem() != null || (event.getCurrentItem().getType().equals(Material.AIR)) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Main.getColorHandler().message + "Disco")) {
					event.setCancelled(true);
					player.updateInventory();
					player.closeInventory();
					player.sendMessage(Main.getColorHandler().donation + "To remove disco armor use /discoarmor");
					return;

				}
			}
		}else if (event.getView().getTitle().contains("Land Fall")) {
			event.setCancelled(true);
			if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType().equals(Material.AIR))) {
				return;
			}else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("START")) {


				Main.getInfoHandler().setEventStatus(1);
				Main.getInfoHandler().setLandfall(1);

				player.closeInventory();
				bar = new Bar();
				bar.createBar();


				if (Bukkit.getOnlinePlayers().size() > 0)
					for (Player on : Bukkit.getOnlinePlayers())
						bar.addPlayer(on);
			}else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Open Portal")) {
				player.closeInventory();
				player.chat("/rg sel -w event landfall_fence");
				player.chat("//set air");
			}else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Close Portal")) {
				player.closeInventory();
				player.chat("/rg sel -w event landfall_fence");
				player.chat("//set nether_brick_fence");
			}else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Open Wait")) {
				player.closeInventory();
				player.chat("/rg sel -w event landfall_glass");
				player.chat("//set air");
			}else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Close Wait")) {
				player.closeInventory();
				player.chat("/rg sel -w event landfall_glass");
				player.chat("//set glass");
			}else if (event.getCurrentItem().getType().equals(Material.OAK_LEAVES)) {
				player.closeInventory();
				player.chat("/rg sel -w event landfall_log");
				player.chat("//set oak_log");
				player.chat("/rg sel -w event landfall_leaf");
				player.chat("//set oak_leaves");
			}else if (event.getCurrentItem().getType().equals(Material.GRAVEL)) {
				player.closeInventory();
				player.chat("/rg sel -w event landfall_log");
				player.chat("//set oak_leaves");
				player.chat("/rg sel -w event landfall_leaf");
				player.chat("//set gravel");
			}else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Set Water")) {
				player.closeInventory();
				player.chat("/rg sel -w event landfall_water");
				player.chat("//set water");
			}else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("END")) {
				player.closeInventory();
				if(Main.getInfoHandler().getEventStatus()==0) {
					player.sendMessage(Main.getColorHandler().error + "No current event!");
					return;
				}else {
					Main.getInfoHandler().setEventStatus(0);
					Main.getInfoHandler().setLandfall(0);
					bar.getBar().removeAll();
				}
			}
		}else if (event.getView().getTitle().contains("Stats")) {
			event.setCancelled(true);
			if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType().equals(Material.AIR))) {
				return;
			}else if (event.getCurrentItem().getType().equals(Material.IRON_SWORD)) {
				player.closeInventory();
				GUI.killStreakInv(player);
			}else if (event.getCurrentItem().getType().equals(Material.PAPER)) {
				player.closeInventory();
				GUI.rewardsInv(player);
			}else if (event.getCurrentItem().getType().equals(Material.BOOK)) {
				player.closeInventory();
				GUI.totalsInv(player);
			}
		}else if (event.getView().getTitle().contains("KillStreak")) {
			event.setCancelled(true);
			if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType().equals(Material.AIR))) {
				return;
			}
		}else if (event.getView().getTitle().contains("Vote Rewards")) {
			event.setCancelled(true);
			if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType().equals(Material.AIR))) {
				return;
			}
		}else if (event.getView().getTitle().contains("Donation Shop")) {
			event.setCancelled(true);
			if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType().equals(Material.AIR))) {
				return;
			}
		}else if (event.getView().getTitle().contains("Totals")) {
			event.setCancelled(true);
			if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType().equals(Material.AIR))) {
				return;
			}
		}else if (event.getView().getTitle().contains("Rules")) {
			event.setCancelled(true);
			if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType().equals(Material.AIR))) {
				return;
			}
			if (event.getCurrentItem().getType().equals(Material.GREEN_STAINED_GLASS_PANE)) {
				player.closeInventory();

				player.sendMessage(Main.getColorHandler().main + "Rules: " + Main.getColorHandler().message +"You have accepted the rules of the server");
				if(player.getFlySpeed() == 0f && player.getWalkSpeed() == 0f) {
					player.setFlySpeed(0.1f);
					player.setWalkSpeed(0.2f);
					player.removePotionEffect(PotionEffectType.JUMP);
					Main.getPlayerHandler(player).setAcceptRules(1);
				}
			}else if (event.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)) {
				player.closeInventory();

				player.kickPlayer(Main.getColorHandler().main + "Rules: " + Main.getColorHandler().message +"You have declined the rules of the server");
			}

		}else if(event.getView().getTitle().contains("Rewards")) {
			event.setCancelled(true);
			if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType().equals(Material.AIR))) {
				return;
			}else if (event.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getPlayersKilledReward() < 500) {
					player.sendMessage(Main.getColorHandler().reward + "Not enough Player Kill points!");
					return;
				} else {
					Main.getPlayerHandler(player).setPlayersKilledReward(Main.getPlayerHandler(player).getPlayersKilledReward()-5);
					Main.getPlayerHandler(player).addMoney(1000);
					player.sendMessage(Main.getColorHandler().reward +"You have redeemed 500 Player Kill points for $1000");
				}
			}else if (event.getCurrentItem().getType().equals(Material.ZOMBIE_HEAD)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getEntitiesKilledReward() < 500) {
					player.sendMessage(Main.getColorHandler().reward + "Not enough Entity Kill points!");
					return;
				} //FIX THE ONLY COST 5
				Main.getPlayerHandler(player).setEntitiesKilledReward(Main.getPlayerHandler(player).getEntitiesKilledReward()-5);
				Main.getPlayerHandler(player).addMoney(1000);
				player.sendMessage(Main.getColorHandler().reward +"You have redeemed 500 Player Kill points for $1000");
			}else if (event.getCurrentItem().getType().equals(Material.DIAMOND_PICKAXE)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getBlocksBroken() < 5000) {
					player.sendMessage(Main.getColorHandler().reward + "Not enough Broken Block points!");
					return;
				}else {
					Main.getPlayerHandler(player).setBlocksBroken(Main.getPlayerHandler(player).getBlocksBroken()-5);
					Main.getPlayerHandler(player).addMoney(1000);
					player.sendMessage(Main.getColorHandler().reward +"You have redeemed 5000 Break Blocks points for $1000");
				}
			}else if (event.getCurrentItem().getType().equals(Material.BRICK)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getBlocksPlaced() < 5000) {
					player.sendMessage(Main.getColorHandler().reward + "Not enough Blocks Placed points!");
					return;
				}else {
					Main.getPlayerHandler(player).setBlocksPlaced(Main.getPlayerHandler(player).getBlocksPlaced()-5);
					Main.getPlayerHandler(player).addMoney(1000);
					player.sendMessage(Main.getColorHandler().reward +"You have redeemed 5000 Blocks placed points for $1000");
				}
			}else if (event.getCurrentItem().getType().equals(Material.EGG)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getEasterEggReward() < 50) {
					player.sendMessage(Main.getColorHandler().reward + "(50) Not enough Easter Eggs found!");
					return;
				}else {
					Main.getPlayerHandler(player).setEasterEggReward(Main.getPlayerHandler(player).getEasterEggReward()-5);
					Main.getPlayerHandler(player).addMoney(1000);
					player.sendMessage(Main.getColorHandler().reward +"You have redeemed 50 Easter Egg finds for $1000");
				}
			}

		}else if(event.getView().getTitle().contains("Token Shop")) {
			event.setCancelled(true);
			if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType().equals(Material.AIR))) {
				return;
			}else if (event.getCurrentItem().getType().equals(Material.DIAMOND_HELMET)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 500) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(500);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}else if (event.getCurrentItem().getType().equals(Material.DIAMOND_CHESTPLATE)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 800) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(800);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().toString());
					player.getInventory().addItem(new ItemStack(Material.DIAMOND_HELMET));
				}
			}else if (event.getCurrentItem().getType().equals(Material.DIAMOND_LEGGINGS)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 700) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(700);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}else if (event.getCurrentItem().getType().equals(Material.DIAMOND_BOOTS)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 400) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(400);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}else if (event.getCurrentItem().getType().equals(Material.ENDER_CHEST)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 1500) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(1500);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}else if (event.getCurrentItem().getType().equals(Material.ENCHANTED_BOOK)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 1500) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(1500);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					ItemStack book = new ItemStack(Material.ENCHANTED_BOOK, 1);
					EnchantmentStorageMeta bookmeta = (EnchantmentStorageMeta) book.getItemMeta();
					bookmeta.addStoredEnchant(Enchantment.MENDING, 1, true);
					book.setItemMeta(bookmeta);
					player.getInventory().addItem(book);
				}
			}else if (event.getCurrentItem().getType().equals(Material.DIAMOND_AXE)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 40) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(40);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}else if (event.getCurrentItem().getType().equals(Material.DIAMOND_SWORD)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 100) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(100);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}else if (event.getCurrentItem().getType().equals(Material.DIAMOND_PICKAXE)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 50) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(50);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}else if (event.getCurrentItem().getType().equals(Material.DIAMOND_SHOVEL)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 25) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(25);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}else if (event.getCurrentItem().getType().equals(Material.DIAMOND_HOE)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 25) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(25);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}else if (event.getCurrentItem().getType().equals(Material.ANVIL)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 1500) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(1500);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}else if (event.getCurrentItem().getType().equals(Material.HOPPER)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 2500) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(2500);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}else if (event.getCurrentItem().getType().equals(Material.BEACON)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 1500) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(1500);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}else if (event.getCurrentItem().getType().equals(Material.BOW)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 50) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(50);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}else if (event.getCurrentItem().getType().equals(Material.ARROW)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 20) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(20);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					ItemStack arrow = new ItemStack(Material.ARROW);
					arrow.setAmount(64);
					player.getInventory().addItem(arrow);
				}
			}else if (event.getCurrentItem().getType().equals(Material.EXPERIENCE_BOTTLE)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 1000) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(1000);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					ItemStack experience = new ItemStack(Material.EXPERIENCE_BOTTLE);
					experience.setAmount(64);
					player.getInventory().addItem(experience);
				}
			}else if (event.getCurrentItem().getType().equals(Material.NETHERITE_AXE)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 80) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(80);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}else if (event.getCurrentItem().getType().equals(Material.NETHERITE_SWORD)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 200) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(200);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}else if (event.getCurrentItem().getType().equals(Material.NETHERITE_PICKAXE)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 100) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(100);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}else if (event.getCurrentItem().getType().equals(Material.NETHERITE_SHOVEL)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 50) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(50);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}else if (event.getCurrentItem().getType().equals(Material.NETHERITE_HOE)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 50) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(50);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}else if (event.getCurrentItem().getType().equals(Material.NETHERITE_HELMET)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 1000) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(1000);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}else if (event.getCurrentItem().getType().equals(Material.NETHERITE_CHESTPLATE)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 1600) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(1600);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}else if (event.getCurrentItem().getType().equals(Material.NETHERITE_LEGGINGS)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 1400) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(1400);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}else if (event.getCurrentItem().getType().equals(Material.NETHERITE_BOOTS)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 800) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(800);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}else if (event.getCurrentItem().getType().equals(Material.COOKED_BEEF)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 32) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(32);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					ItemStack cookedBeef = new ItemStack(Material.COOKED_BEEF);
					cookedBeef.setAmount(64);
					player.getInventory().addItem(cookedBeef);
				}
			}else if (event.getCurrentItem().getType().equals(Material.GOLDEN_APPLE)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 500) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(500);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}else if (event.getCurrentItem().getType().equals(Material.ENCHANTED_GOLDEN_APPLE)) {
				player.closeInventory();
				if(Main.getPlayerHandler(player).getToken() < 2000) {
					player.sendMessage(Main.getColorHandler().token + "Not enough tokens to purchase this!");
					return;
				}else {
					Main.getPlayerHandler(player).removeToken(2000);
					player.sendMessage(Main.getColorHandler().token +"You have purchased " + event.getCurrentItem().getType().toString());
					player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
				}
			}
		}
	}

}
