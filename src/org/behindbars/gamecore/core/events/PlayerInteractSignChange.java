/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.events;

import org.behindbars.gamecore.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractSignChange implements Listener {
	
	@EventHandler
	public void onSignChange(SignChangeEvent event) {
		Player player = event.getPlayer();

		if (Main.getPlayerHandler(player).getRank() == 10) {


			if (event.getLine(0).equalsIgnoreCase("[Sell]")) {
				event.setLine(0, "§1[Sell]");
			}

			Material m = Material.matchMaterial(event.getLine(2));

			if (m == null) {
				if (event.getLine(0).equalsIgnoreCase("§1[Sell]")) {
					player.sendMessage("§5§l* §7Can't find item, " + event.getLine(2));
					return;
				}
			}

			if (event.getLine(0).equalsIgnoreCase("[Buy]")) {
				event.setLine(0, "§1[Buy]");
			}


			if (m == null) {
				if (event.getLine(0).equalsIgnoreCase("§1[Buy]")) {
					player.sendMessage("§5§l* §7Can't find item, " + event.getLine(2));
					return;
				}
			}

			if (event.getLine(0).equalsIgnoreCase("[Warp]")) {
				event.setLine(0, "§1[Warp]");
			}
			if (event.getLine(0).equalsIgnoreCase("[Heal]")) {
				event.setLine(0, "§1[Heal]");
			}
			if (event.getLine(0).equalsIgnoreCase("[Prison Parkour]")) {
				event.setLine(0, "§1[Prison Parkour]");
			}
			if (event.getLine(0).equalsIgnoreCase("[Parkour]")) {
				event.setLine(0, "§1[Parkour]");
			}
			if (event.getLine(0).equalsIgnoreCase("[Smite]")) {
				event.setLine(0, "§1[Smite]");
			}
			if (event.getLine(0).equalsIgnoreCase("[Kit]")) {
				event.setLine(0, "§1[Kit]");
			}
			if (event.getLine(0).equalsIgnoreCase("[Jail Time]")) {
				event.setLine(0, "§1[Jail Time]");
			}
			if (event.getLine(0).equalsIgnoreCase("[Reset Mine]")) {
				event.setLine(0, "§1[Reset Mine]");
			}
			if (event.getLine(0).equalsIgnoreCase("[Un-Jail]")) {
				event.setLine(0, "§1[Un-Jail]");
			}
			if (event.getLine(0).equalsIgnoreCase("[Trash]")) {
				event.setLine(0, "§1[Trash]");
			}
			if (event.getLine(0).equalsIgnoreCase("[Easter Egg]")) {
				event.setLine(0, "§1[Easter Egg]");
			}

			return;
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
		if (e.getClickedBlock().getState() instanceof Sign) {
			Sign s = (Sign) e.getClickedBlock().getState();
			if (s.getLine(0).equalsIgnoreCase("§1[Sell]")) {

				int ammount = Integer.parseInt((s.getLine(1).replaceAll("[^\\d.]", "")));
				int cost = Integer.parseInt((s.getLine(3).replaceAll("[^\\d.]", "")));

				String item = s.getLine(2).toLowerCase().toString();
				int count = 0;

				for (ItemStack stack : player.getInventory().getContents()) {
					if (stack != null && stack.getType() == Material.matchMaterial(item)) {
						count += stack.getAmount();
					}
				}

				if(!(count >= ammount)) {
					player.sendMessage(Main.getColorHandler().main + "Store: " + Main.getColorHandler().message + "You do not have enough items to sell!");
					return;
				}
				if(player.getInventory().getItemInOffHand().getType().equals(Material.matchMaterial(s.getLine(2).toString()))) {
					player.sendMessage("You can not have selling item in offhand!");
					return;
				}

				player.getInventory().removeItem(new ItemStack(Material.matchMaterial(s.getLine(2).toString()), ammount));
				player.updateInventory();

				Main.getPlayerHandler(player).addMoney(cost);
			}else if (s.getLine(0).equalsIgnoreCase("§1[Buy]")) {

				int ammount = Integer.parseInt((s.getLine(1).replaceAll("[^\\d.]", "")));
				int cost = Integer.parseInt((s.getLine(3).replaceAll("[^\\d.]", "")));

				String item = s.getLine(2).toLowerCase().toString();
				int count = 0;

				if(Main.getPlayerHandler(player).getMoney() < cost) {
					player.sendMessage(Main.getColorHandler().main + "Store: " + Main.getColorHandler().message + "You do not have money to buy!");
					return;
				}

				/*if(!(count <= ammount)) {
                    player.sendMessage(Main.getColorHandler().main + "Store: " + Main.getColorHandler().message + "You do not have money to buy!");
                    return;
                } */

				player.getInventory().addItem(new ItemStack(Material.matchMaterial(s.getLine(2).toString()), ammount));
				player.updateInventory();

				Main.getPlayerHandler(player).removeMoney(cost);
			}else if (s.getLine(0).equalsIgnoreCase("§1[Warp]")) {

				if(s.getLine(2) == "") {
					return;
				}

				if(Main.getPlayerHandler(player).getJailTime() > 0) {
					player.sendMessage(Main.getColorHandler().error + "You can't warp in jail!");
					return;
				}

				Main.getWarpHandler().warp(s.getLine(2).toUpperCase().toString(), player);

			}else if (s.getLine(0).equalsIgnoreCase("§1[Parkour]")) {

				if(s.getLine(2) == "") {
					return;
				}

				long elapsedTime = System.currentTimeMillis() - Main.getPlayerHandler(player).getParkourDate(s.getLine(2));
				long timeUntilReuse = ((24*60*60*1000) - elapsedTime);
				if(timeUntilReuse > 0) {
					player.sendMessage(Main.getColorHandler().coolDown + Main.getTimeformatHandler().formatTime(timeUntilReuse));
					return;
				}

				Main.getPlayerHandler(player).setParkourDate(s.getLine(2));
				player.sendMessage(Main.getColorHandler().main + "Parkour: " + Main.getColorHandler().message+ "You have completed parkour: " + s.getLine(2));
				Main.getPlayerHandler(player).addToken(Integer.valueOf(s.getLine(2) + 0));
			}else if (s.getLine(0).equalsIgnoreCase("§1[Reset Mine]")) {

				if(s.getLine(2) == "") {
					return;
				}

				long elapsedTime = System.currentTimeMillis() - Main.getMineHandler().getMineResetTime(s.getLine(2));
				long timeUntilReuse = ((60*60*1000) - elapsedTime);
				if(timeUntilReuse > 0) {
					player.sendMessage(Main.getColorHandler().coolDown + Main.getTimeformatHandler().formatTime(timeUntilReuse));
					return;
				}else if(s.getLine(2).contains("1")) {
					Main.getMineHandler().setMineResetTime(s.getLine(2));
					Bukkit.getServer().broadcastMessage(Main.getColorHandler().main + "Mine: " + Main.getColorHandler().message + "Charlie mine has been reset!");

					if(player.isOp()) {
						player.chat("/rg sel -w world cmine");
						player.chat("//set 97.5%stone,0.5%iron_ore,0.5%coal_ore,0.5%glowstone");
						return;
					}

					player.setOp(true);
					player.chat("/rg sel -w world cmine");
					player.chat("//set 97.5%stone,0.5%iron_ore,0.5%coal_ore,0.5%glowstone");
					player.setOp(false);
				}else if(s.getLine(2).contains("2")) {
					Main.getMineHandler().setMineResetTime(s.getLine(2));
					Bukkit.getServer().broadcastMessage(Main.getColorHandler().main + "Mine: " + Main.getColorHandler().message + "Bravo mine has been reset!");

					if(player.isOp()) {
						player.chat("/rg sel -w world bmine");
						player.chat("//set 97.5%sandstone,0.5%iron_ore,0.5%gold_ore,0.5%glowstone");
						return;
					}else {
						player.setOp(true);
						player.chat("/rg sel -w world bmine");
						player.chat("//set 97.5%sandstone,0.5%iron_ore,0.5%gold_ore,0.5%glowstone");
						player.setOp(false);
					}
				}else if(s.getLine(2).contains("3")) {
					Main.getMineHandler().setMineResetTime(s.getLine(2));
					Bukkit.getServer().broadcastMessage(Main.getColorHandler().main + "Mine: " + Main.getColorHandler().message + "Alpha mine has been reset!");

					if(player.isOp()) {
						player.chat("/rg sel -w world amine");
						player.chat("//set 97.5%netherrack,0.5%lapis_ore,0.5%diamond_ore,0.5%glowstone");
						return;
					}else {
						player.setOp(true);
						player.chat("/rg sel -w world amine");
						player.chat("//set 97.5%netherrack,0.5%lapis_ore,0.5%diamond_ore,0.5%glowstone");
						player.setOp(false);
					}
				}else if(s.getLine(2).contains("4")) {
					Main.getMineHandler().setMineResetTime(s.getLine(2));
					Bukkit.getServer().broadcastMessage(Main.getColorHandler().main + "Mine: " + Main.getColorHandler().message + "Elite mine has been reset!");

					if(player.isOp()) {
						player.chat("/rg sel -w world emine");
						player.chat("//set 98.5%snow_block,0.5%emerald_ore,0.5%diamond_ore,0.5%glowstone");
						return;
					}else {
						player.setOp(true);
						player.chat("/rg sel -w world emine");
						player.chat("//set 98.5%snow_block,0.5%emerald_ore,0.5%diamond_ore,0.5%glowstone");
						player.setOp(false);
					}
				}

			}else if (s.getLine(0).equalsIgnoreCase("§1[Easter Egg]")) {
				if(s.getLine(2) == "") {
					return;
				}

				long elapsedTime = System.currentTimeMillis() - Main.getPlayerHandler(player).getEastereggDate(s.getLine(2));
				long timeUntilReuse = ((24*60*60*1000) - elapsedTime);
				if(timeUntilReuse > 0) {
					player.sendMessage(Main.getColorHandler().coolDown + Main.getTimeformatHandler().formatTime(timeUntilReuse));
					return;
				}else {
					Main.getPlayerHandler(player).setEastereggDate(s.getLine(2));
					Main.getPlayerHandler(player).setEasterEggReward(Main.getPlayerHandler(player).getEasterEggReward() + 1);
					player.sendMessage(Main.getColorHandler().main + "EasterEgg: " + Main.getColorHandler().message+ "You have completed Easter Egg: " + s.getLine(2));
					Main.getPlayerHandler(player).addToken(25);
				}
			}else if (s.getLine(0).equalsIgnoreCase("§1[Prison Parkour]")) {

				if(s.getLine(2) == "") {
					return;
				}

				long elapsedTime = System.currentTimeMillis() - Main.getPlayerHandler(player).getPrisonParkourDate(s.getLine(2));
				long timeUntilReuse = ((24*60*60*1000) - elapsedTime);
				if(timeUntilReuse > 0) {
					player.sendMessage(Main.getColorHandler().coolDown + Main.getTimeformatHandler().formatTime(timeUntilReuse));
					return;
				}else {
					Main.getPlayerHandler(player).setPrisonParkourDate(s.getLine(2));
					player.sendMessage(Main.getColorHandler().main + "Parkour: " + Main.getColorHandler().message+ "You have completed parkour: " + s.getLine(2));
					Main.getPlayerHandler(player).addToken(Integer.valueOf(s.getLine(2) + 0));
				}
			}else if (s.getLine(0).equalsIgnoreCase("§1[Jail Time]")) {

				if(Main.getPlayerHandler(player).getJailTime() == 0) {
					player.sendMessage(Main.getColorHandler().main + "Jail: " + Main.getColorHandler().message + "You are not in jail!");
					return;
				}

				player.sendMessage(Main.getColorHandler().main + "Jail: " + Main.getColorHandler().message + "You will be released in " + Main.getPlayerHandler(player).getJailTime() + " seconds!");

			}else if (s.getLine(0).equalsIgnoreCase("§1[Smite]")) {

				if(s.getLine(2) == "") {
					return;
				}

				Player target = Bukkit.getPlayer(s.getLine(2));
				if(target == null) {
					player.sendMessage(Main.getColorHandler().offlinePlayer);
					return;
				}else {
					player.sendMessage("You have smitten " + s.getLine(2));
					target.sendMessage("You have been smitten by " + s.getLine(2) );
				}
			}else if (s.getLine(0).equalsIgnoreCase("§1[Heal]")) {
				player.setHealth(20);
				player.sendMessage(Main.getColorHandler().main + "Heal: " + Main.getColorHandler().message+ "You have been healed");
				player.setFoodLevel(25);
			}else if (s.getLine(0).equalsIgnoreCase("§1[Un-Jail]")) {
				Main.getPlayerHandler(player).resetJailTime();
				Location spawn = new Location(Bukkit.getWorld("world"), -0.5,125,-0.5);
				player.teleport(spawn);
				Main.getPlayerHandler(player).setInJail(0);
				player.sendMessage(Main.getColorHandler().main + "Jail: " + Main.getColorHandler().message + "You have escaped yourself out of jail!");
			}else if (s.getLine(0).equalsIgnoreCase("§1[Kit]")) {
				player.chat("/kit " + s.getLine(2));
			}
		}
	}

}
