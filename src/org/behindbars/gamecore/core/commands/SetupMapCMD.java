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
import org.behindbars.gamecore.easteregg.npc.EasterEggCharlieReward;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import net.md_5.bungee.api.ChatColor;

public class SetupMapCMD extends Command {

	private static String name = "setupmap";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public SetupMapCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if(Main.getPlayerHandler(player).getRank() < 10) {
            player.sendMessage(Main.getColorHandler().noPermission);
        }else if(args[1].equalsIgnoreCase("eastereggs")) {
            EasterEggCharlieReward.setupCharlieRewards(player);
        }else {
	        if(args[1].equalsIgnoreCase("common")) {
	
	            Location voteCrate = new Location(Bukkit.getWorld("world"), 4.5, 127, -5.5, 45f, 25f); //done
	            Villager c = (Villager) player.getWorld().spawnEntity(voteCrate, EntityType.VILLAGER);
	            c.setInvulnerable(true);
	            c.setCollidable(false);
	            c.setAI(false);
	            c.setProfession(Villager.Profession.LIBRARIAN);
	            c.setCustomName(ChatColor.RED + ChatColor.BOLD.toString() + "Vote Crate");
	            c.setCustomNameVisible(true);
	
	            Location eventLoc = new Location(Bukkit.getWorld("world"), 4.5, 127, 4.5, 135f, 25f);
	            Villager cevent = (Villager) player.getWorld().spawnEntity(eventLoc, EntityType.VILLAGER);
	            cevent.setInvulnerable(true);
	            cevent.setCollidable(false);
	            cevent.setAI(false);
	            cevent.setProfession(Villager.Profession.LIBRARIAN);
	            cevent.setCustomName(ChatColor.RED + ChatColor.BOLD.toString() + "Event");
	            cevent.setCustomNameVisible(true);
	
	            Location rewards = new Location(Bukkit.getWorld("world"), -5.5, 127, -5.5, -45f, 25f);
	            Villager villager = (Villager) player.getWorld().spawnEntity(rewards, EntityType.VILLAGER);
	            villager.setInvulnerable(true);
	            villager.setCollidable(false);
	            villager.setAI(false);
	            villager.setProfession(Villager.Profession.LIBRARIAN);
	            villager.setCustomName(ChatColor.RED + ChatColor.BOLD.toString() + "Stats");
	            villager.setCustomNameVisible(true);
	
	            Location dailyReward = new Location(Bukkit.getWorld("world"), -5.5, 127, 4.5, -135f, 25f);
	            Villager dailyr = (Villager) player.getWorld().spawnEntity(dailyReward, EntityType.VILLAGER);
	            dailyr.setInvulnerable(true);
	            dailyr.setCollidable(false);
	            dailyr.setAI(false);
	            dailyr.setProfession(Villager.Profession.LIBRARIAN);
	            dailyr.setCustomName(ChatColor.RED + ChatColor.BOLD.toString() + "Daily Reward");
	            dailyr.setCustomNameVisible(true);
	
	            Location holoParkour = new Location(Bukkit.getWorld("world"), -12.5, 127, 11.5);
	            ArmorStand as = (ArmorStand) player.getWorld().spawnEntity(holoParkour, EntityType.ARMOR_STAND); //Spawn the ArmorStand
	            as.setGravity(false); //Make sure it doesn't fall
	            as.setCanPickupItems(false); //I'm not sure what happens if you leave this as it is, but you might as well disable it
	            as.setCustomName(ChatColor.RED + ChatColor.BOLD.toString() + "Parkour"); //Set this to the text you want
	            as.setCustomNameVisible(true); //This makes the text appear no matter if your looking at the entity or not
	            as.setVisible(false); //Makes the ArmorStand invisible
	
	            Location holoFurnace = new Location(Bukkit.getWorld("world"), -12.5, 127, -12.5);
	            ArmorStand as1 = (ArmorStand) player.getWorld().spawnEntity(holoFurnace, EntityType.ARMOR_STAND); //Spawn the ArmorStand
	            as1.setGravity(false); //Make sure it doesn't fall
	            as1.setCanPickupItems(false); //I'm not sure what happens if you leave this as it is, but you might as well disable it
	            as1.setCustomName(ChatColor.RED + ChatColor.BOLD.toString() + "Craft / Enchant / Smelt"); //Set this to the text you want
	            as1.setCustomNameVisible(true); //This makes the text appear no matter if your looking at the entity or not
	            as1.setVisible(false); //Makes the ArmorStand invisible
	
	            Location holoShop = new Location(Bukkit.getWorld("world"), 19.5, 127, -9.5);
	            ArmorStand as2 = (ArmorStand) player.getWorld().spawnEntity(holoShop, EntityType.ARMOR_STAND); //Spawn the ArmorStand
	            as2.setGravity(false); //Make sure it doesn't fall
	            as2.setCanPickupItems(false); //I'm not sure what happens if you leave this as it is, but you might as well disable it
	            as2.setCustomName(ChatColor.RED + ChatColor.BOLD.toString() + "Store"); //Set this to the text you want
	            as2.setCustomNameVisible(true); //This makes the text appear no matter if your looking at the entity or not
	            as2.setVisible(false); //Makes the ArmorStand invisible
	
	            Location holoMainPortal = new Location(Bukkit.getWorld("world"), 11.5, 127, 11.5);
	            ArmorStand as3 = (ArmorStand) player.getWorld().spawnEntity(holoMainPortal, EntityType.ARMOR_STAND); //Spawn the ArmorStand
	            as3.setGravity(false); //Make sure it doesn't fall
	            as3.setCanPickupItems(false); //I'm not sure what happens if you leave this as it is, but you might as well disable it
	            as3.setCustomName(ChatColor.RED + ChatColor.BOLD.toString() + "Main Portals"); //Set this to the text you want
	            as3.setCustomNameVisible(true); //This makes the text appear no matter if your looking at the entity or not
	            as3.setVisible(false); //Makes the ArmorStand invisible
	
	            Location parkourPortal1 = new Location(Bukkit.getWorld("world"), -9.00, 125, 32.5);
	            Location parkourPortal2 = new Location(Bukkit.getWorld("world"), -13.00, 125, 31.5);
	            Location parkourPortal3 = new Location(Bukkit.getWorld("world"), -17.00, 125, 29.5);
	            Location parkourPortal4 = new Location(Bukkit.getWorld("world"), -23.00, 125, 25.5);
	            Location parkourPortal5 = new Location(Bukkit.getWorld("world"), -26.5, 125, 21.00);
	            Location parkourPortal6 = new Location(Bukkit.getWorld("world"), -30.5, 125, 16.00);
	            Location parkourPortal7 = new Location(Bukkit.getWorld("world"), -32.5, 125, 12.00);
	            Location parkourPortal8 = new Location(Bukkit.getWorld("world"), -33.5, 125, 8.00);
	
	            Location freePortalHolo = new Location(Bukkit.getWorld("world"), 23.5, 126, 6.00);
	            Location netherPortalHolo = new Location(Bukkit.getWorld("world"), 21.5, 126, 12.00);
	            Location plotsPortal = new Location(Bukkit.getWorld("world"), 12.00, 126, 21.5);
	            Location donorPortal = new Location(Bukkit.getWorld("world"), 6.00, 126, 23.00);
	
	
	            portalHolo("1", parkourPortal1, player);
	            portalHolo("2", parkourPortal2, player);
	            portalHolo("3", parkourPortal3, player);
	            portalHolo("4", parkourPortal4, player);
	            portalHolo("5", parkourPortal5, player);
	            portalHolo("6", parkourPortal6, player);
	            portalHolo("7", parkourPortal7, player);
	            portalHolo("8", parkourPortal8, player);
	            holo("Free World", freePortalHolo, player);
	            holo("Nether World", netherPortalHolo, player);
	            holo("Player Plots/Market", plotsPortal, player);
	            holo("Donor Island", donorPortal, player);
	        }
	    }
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		return list;
	}
	
	public void portalHolo(String num, Location location, Player player) {
	    ArmorStand as3 = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND); //Spawn the ArmorStand
	    as3.setGravity(false); //Make sure it doesn't fall
	    as3.setCanPickupItems(false); //I'm not sure what happens if you leave this as it is, but you might as well disable it
	    as3.setCustomName(ChatColor.RED + ChatColor.BOLD.toString() + "Parkour " + num); //Set this to the text you want
	    as3.setCustomNameVisible(true); //This makes the text appear no matter if your looking at the entity or not
	    as3.setVisible(false); //Makes the ArmorStand invisible
	}

	public void holo(String num, Location location, Player player) {
	    ArmorStand as3 = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND); //Spawn the ArmorStand
	    as3.setGravity(false); //Make sure it doesn't fall
	    as3.setCanPickupItems(false); //I'm not sure what happens if you leave this as it is, but you might as well disable it
	    as3.setCustomName(ChatColor.RED + ChatColor.BOLD.toString() + num); //Set this to the text you want
	    as3.setCustomNameVisible(true); //This makes the text appear no matter if your looking at the entity or not
	    as3.setVisible(false); //Makes the ArmorStand invisible
	}

}
