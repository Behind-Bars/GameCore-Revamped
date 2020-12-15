/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.util;

import java.util.ArrayList;

import org.behindbars.gamecore.Main;
import org.behindbars.gamecore.core.handlers.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import net.md_5.bungee.api.ChatColor;

public class GUI {
	
	private static PlayerHandler playerHandler;
    
    /*Inventory Slots
    0   1  2  3  4  5  6  7  8
    9  10  11 12 13 14 15 16 17
    18 19  20 21 22 23 24 25 26
    27 28  29 30 31 32 33 34 35
    36 37  38 39 40 41 42 43 44
    45 46  47 48 49 50 51 52 53
    */

    public static void sellInv(Player player) {
    	Inventory sellInventory = Bukkit.createInventory(null, InventoryType.HOPPER, Main.getColorHandler().main +"Shop");
        
        createItem(Material.DIAMOND_HOE, sellInventory, 0, "Farming Shop", "Sell your farming items to the local farmer!");
        createItem(Material.BONE, sellInventory, 2, "Mob Shop", "Sell your mob loot to the local zombie killer!");
        createItem(Material.IRON_ORE, sellInventory, 4, "Mine Shop", "Sell your mined items to the local miner!");
        player.openInventory(sellInventory);
    }

    public static void sellFarmInv(Player player) {
    	Inventory sellFarmInventory = Bukkit.createInventory(null, 27, Main.getColorHandler().main +"Farmer Shop");
        
        createItem(Material.DIAMOND_HOE, sellFarmInventory, 4, "Farmer Shop", "Sell your farming items to the local farmer!");
        createItem(Material.WHEAT, sellFarmInventory, 10, "Wheat", "Sell your wheat for $5 each!");
        createItem(Material.SUGAR, sellFarmInventory, 20, "Sugar", "Sell your sugar for $5 each!");
        createItem(Material.PUMPKIN, sellFarmInventory, 12, "Pumpkin", "Sell your pumpkins for $5 each!");
        createItem(Material.CACTUS, sellFarmInventory, 14, "Cactus", "Sell your cactus for $5 each!");
        createItem(Material.NETHER_WART, sellFarmInventory, 16, "Nether Wart", "Sell your nether wart for $4 each!");
        createItem(Material.PORKCHOP, sellFarmInventory, 24, "Porkchop", "Sell your pork chops for $15 each!");
        player.openInventory(sellFarmInventory);
    }


    public static void statsInv(Player player) {
    	Inventory statsInventory = Bukkit.createInventory(null, InventoryType.HOPPER, Main.getColorHandler().main +"Stats");
        
        createItem(Material.IRON_SWORD, statsInventory, 0, "Kill Streak", null);
        createItem(Material.PAPER, statsInventory, 2, "Rewards", null);
        createItem(Material.BOOK, statsInventory, 4, "Totals", null);
        player.openInventory(statsInventory);
    }



    public static void killStreakInv(Player player) {
    	playerHandler = new PlayerHandler(player);
        int kills = playerHandler.getKills();
        double deaths = playerHandler.getDeaths();
        double ratio = kills / deaths;

        Inventory killStreakInventory = Bukkit.createInventory(null, InventoryType.HOPPER, Main.getColorHandler().main +"KillStreak");
        createItem(Material.IRON_SWORD, killStreakInventory, 0, "Kills", String.valueOf(kills));
        createItem(Material.REDSTONE, killStreakInventory, 1, "Deaths", String.valueOf(deaths));
        createItem(Material.BOW, killStreakInventory, 2, "Kill Streak", String.valueOf(playerHandler.getKillStreak()));
        createItem(Material.PAPER, killStreakInventory, 3, "Last Killed By", String.valueOf(playerHandler.getLKBP()));
        createItem(Material.BOOK, killStreakInventory, 4, "K/D", String.valueOf(ratio));
        player.openInventory(killStreakInventory);
    }

    public static void rewardsInv(Player player) {
    	playerHandler = new PlayerHandler(player);
    	Inventory rewardsInventory = Bukkit.createInventory(null, 9, Main.getColorHandler().main +"Rewards");
        
        createItem(Material.DIAMOND_PICKAXE, rewardsInventory, 0, "Blocks Broken", String.valueOf(playerHandler.getBlocksBroken()));
        createItem(Material.BRICK, rewardsInventory, 1, "Blocks Placed", String.valueOf(playerHandler.getBlocksPlaced()));
        createItem(Material.ZOMBIE_HEAD, rewardsInventory, 2, "Entities Killed",  String.valueOf(playerHandler.getEntitiesKilledReward()));
        createItem(Material.PLAYER_HEAD, rewardsInventory, 3, "Players Killed", String.valueOf(playerHandler.getPlayersKilledReward()));
        createItem(Material.NETHER_STAR, rewardsInventory, 4, "Vote", null);
        createItem(Material.EGG, rewardsInventory, 5, "Easter Egg", String.valueOf(playerHandler.getEasterEggReward()));

        player.openInventory(rewardsInventory);
    }
    public static void totalsInv(Player player) {
    	Inventory totalsInventory = Bukkit.createInventory(null, 9, Main.getColorHandler().main +"Totals");
        
        createItem(Material.DIAMOND_PICKAXE, totalsInventory, 0, "Blocks Broken", null);
        createItem(Material.BRICK, totalsInventory, 1, "Blocks Placed", null);
        createItem(Material.ZOMBIE_HEAD, totalsInventory, 2, "Entities Killed", null);
        createItem(Material.PLAYER_HEAD, totalsInventory, 3, "Players Killed", null);
        createItem(Material.NETHER_STAR, totalsInventory, 4, "Vote", null);
        player.openInventory(totalsInventory);
    }

    public static void voteInv(Player player) {
    	Inventory voteInventory = Bukkit.createInventory(null, 9, Main.getColorHandler().main +"Vote Rewards (not complete)");
        
        createItem(Material.PAPER, voteInventory, 0, "1", null);
        createItem(Material.PAPER, voteInventory, 1, "2", null);
        createItem(Material.PAPER, voteInventory, 2, "3", null);

        player.openInventory(voteInventory);
    }


    public static void donationShopInv(Player player) {
    	playerHandler = new PlayerHandler(player);
    	Inventory donationShopInventory = Bukkit.createInventory(null, 9, Main.getColorHandler().main +"Donation Shop: " + Main.getColorHandler().message 
        		+ "(" + String.valueOf(playerHandler.getDonationPoints()) + " Available)");
        
        createItem(Material.PUMPKIN, donationShopInventory, 0, "/hat", "(#) Put an item on your head!");
        createItem(Material.DIAMOND_SWORD, donationShopInventory, 1, "/pvp", "(#) Disable/Enable pvp!");
        createItem(Material.NAME_TAG, donationShopInventory, 2, "/nickname", "(#) Have a multi-color name!");
        createItem(Material.COOKED_CHICKEN, donationShopInventory, 3, "/feed", "(#) Satisfy your hunger!");
        createItem(Material.IRON_BARS, donationShopInventory, 4, "/bail", "(#) Bail yourself out of jail! ($5000)");
        createItem(Material.NAME_TAG, donationShopInventory, 5, "/namecolor", "(#) have a single-color name!");
        createItem(Material.NETHER_STAR, donationShopInventory, 6, "/spawn", "(#) Teleport to spawn!");
        createItem(Material.BOOK, donationShopInventory, 7, "/stats", "(#) View stats, totals, redeem rewards, and more!");
        createItem(Material.REDSTONE, donationShopInventory, 8, "/trails", "(#) Put on a particle trail!");

        player.openInventory(donationShopInventory);
    }


    public static void rulesInv(Player player) {
    	Inventory rulesInventory = Bukkit.createInventory(null, 54, Main.getColorHandler().main +"Rules");
        
        createItem(Material.BOOK, rulesInventory, 4, "Rules", "Accept or Decline");
        createItem(Material.PAPER, rulesInventory, 10, "Rule #1", "No Hacking");
        createItem(Material.PAPER, rulesInventory, 12, "Rule #2", "No unapproved Mods or Scripts");
        createItem(Material.PAPER, rulesInventory, 14, "Rule #3", "No abusive language (Bullying, Racism, Inappropriate words, or Caps)");
        createItem(Material.PAPER, rulesInventory, 16, "Rule #4", "No advertising");
        createItem(Material.PAPER, rulesInventory, 19, "Rule #5", "No DDOS Threats or DOXXing");
        createItem(Material.PAPER, rulesInventory, 20, "Rule #6", "No Staff Impersonation");
        createItem(Material.PAPER, rulesInventory, 21, "Rule #7", "No Scamming");
        createItem(Material.PAPER, rulesInventory, 23, "Rule #8", "No Glitch Exploitation (including lag machines)");
        createItem(Material.PAPER, rulesInventory, 24, "Rule #9", "No Offensive Skins or Usernames");
        createItem(Material.PAPER, rulesInventory, 25, "Rule #10", "No AFK Machines or AFK/AutoFarms");
        createItem(Material.PAPER, rulesInventory, 28, "Rule #11", "No begging or trolling");
        createItem(Material.PAPER, rulesInventory, 30, "Rule #12", "No griefing claimed areas");
        createItem(Material.PAPER, rulesInventory, 32, "Rule #13", "No PvP token farming");
        createItem(Material.PAPER, rulesInventory, 34, "Rule #14", "Do not reveal vanished staff!");

        createItem(Material.RED_STAINED_GLASS_PANE, rulesInventory, 53, "Decline", "");
        createItem(Material.RED_STAINED_GLASS_PANE, rulesInventory, 52, "Decline", "");
        createItem(Material.RED_STAINED_GLASS_PANE, rulesInventory, 51, "Decline", "");
        createItem(Material.RED_STAINED_GLASS_PANE, rulesInventory, 50, "Decline", "");
        createItem(Material.BLACK_STAINED_GLASS, rulesInventory, 49, "Accept Or Decline", "");
        createItem(Material.GREEN_STAINED_GLASS_PANE, rulesInventory, 48, "Accept", "");
        createItem(Material.GREEN_STAINED_GLASS_PANE, rulesInventory, 47, "Accept", "");
        createItem(Material.GREEN_STAINED_GLASS_PANE, rulesInventory, 46, "Accept", "");
        createItem(Material.GREEN_STAINED_GLASS_PANE, rulesInventory, 45, "Accept", "");


        player.openInventory(rulesInventory);
    }

    public static void eventLandFallInv(Player player) {
    	Inventory eventLandFallInventory = Bukkit.createInventory(null, 9, Main.getColorHandler().main +"Event: " + "Land Fall");
        
        createItem(Material.GREEN_STAINED_GLASS_PANE, eventLandFallInventory, 0, ChatColor.GREEN + "START", "Start Landfall event!");
        createItem(Material.BIRCH_FENCE_GATE, eventLandFallInventory, 1, ChatColor.BLUE + "Open Portal", "Allow people into waiting room!");
        createItem(Material.BIRCH_FENCE_GATE, eventLandFallInventory, 2, ChatColor.RED + "Close Portal", "Deny people into waiting room!");
        createItem(Material.IRON_TRAPDOOR, eventLandFallInventory, 3, ChatColor.BLUE + "Open Waiting Room", "Drops people onto the floor!");
        createItem(Material.IRON_TRAPDOOR, eventLandFallInventory, 4, ChatColor.RED + "Close Waiting Room", "Replaces floor!");
        createItem(Material.GRAVEL, eventLandFallInventory, 5, ChatColor.BLUE + "Set Gravel & Leaves", "Replaces Log with leaves!");
        createItem(Material.OAK_LEAVES, eventLandFallInventory, 6, ChatColor.RED + "Set Logs & Leaves", "(#) Teleport to spawn!");
        createItem(Material.WATER_BUCKET, eventLandFallInventory, 7, ChatColor.BLUE + "Set Water", "Set water (removes gravel)!");
        createItem(Material.RED_STAINED_GLASS_PANE, eventLandFallInventory, 8, ChatColor.DARK_RED + "END", "Stop Landfall event!");

        player.openInventory(eventLandFallInventory);
    }

    public static void tokenShopInv(Player player) {
    	playerHandler = new PlayerHandler(player);
    	Inventory tokenShopInventory = Bukkit.createInventory(null, 45, Main.getColorHandler().main + "Token Shop: " + Main.getColorHandler().message + "(" 
        		+ String.valueOf(playerHandler.getToken()) + " Available) " + ChatColor.RED + ChatColor.BOLD.toString() + "Ensure Open Inv Slot!");
        
        createItem(Material.DIAMOND_HELMET, tokenShopInventory, 0, "Diamond Helmet", "500 Tokens");
        createItem(Material.DIAMOND_AXE, tokenShopInventory, 1, "Diamond Axe", "40 Tokens");
        createItem(Material.ANVIL, tokenShopInventory, 3, "Anvil", "1500 Tokens");
        createItem(Material.HOPPER, tokenShopInventory, 4, "Hopper", "2500 Tokens");
        createItem(Material.BEACON, tokenShopInventory, 5, "Beacon", "1500 Tokens");
        createItem(Material.NETHERITE_AXE, tokenShopInventory, 7, "Netherite Axe", "80 Tokens");
        createItem(Material.NETHERITE_HELMET, tokenShopInventory, 8, "Netherite Helmet", "1000 Tokens");
        createItem(Material.DIAMOND_CHESTPLATE, tokenShopInventory, 9, "Diamond Chestplate", "800 Tokens");
        createItem(Material.DIAMOND_SWORD, tokenShopInventory, 10, "Diamond Sword", "100 Tokens");
      // MENDING  createItem(Material.MENDING, tokenShopInventory, 13, "Vote", null);
        createItem(Material.NETHERITE_SWORD, tokenShopInventory, 16, "Netherite Sword", "200 Tokens");
        createItem(Material.NETHERITE_CHESTPLATE, tokenShopInventory, 17, "Netherite Chestplate", "1600 Tokens");
        createItem(Material.DIAMOND_LEGGINGS, tokenShopInventory, 18, "Diamond Leggings", "700 Tokens");
        createItem(Material.DIAMOND_PICKAXE, tokenShopInventory, 19, "Diamond Pickaxe", "50 Tokens");
        createItem(Material.BOW, tokenShopInventory, 21, "Bow", "50 Tokens");
        createItem(Material.ARROW, tokenShopInventory, 22, "Arrows x64", "20 Tokens");
        createItem(Material.EXPERIENCE_BOTTLE, tokenShopInventory, 23, "Experience Bottles x64", "1000 Tokens");
        createItem(Material.NETHERITE_PICKAXE, tokenShopInventory, 25, "Netherite Pickaxe", "100 Tokens");
        createItem(Material.NETHERITE_LEGGINGS, tokenShopInventory, 26, "Netherite Leggings", "1400 Tokens");
        createItem(Material.DIAMOND_BOOTS, tokenShopInventory, 27, "Diamond Boots", "400 Tokens");
        createItem(Material.DIAMOND_SHOVEL, tokenShopInventory, 28, "Diamond Shovel", "25 Tokens");
        createItem(Material.COOKED_BEEF, tokenShopInventory, 30, "Cooked Beef x64", "32 Tokens");
        createItem(Material.ENCHANTED_GOLDEN_APPLE, tokenShopInventory, 31, "Enchanted Golden Apple", "2000 Tokens");
        createItem(Material.GOLDEN_APPLE, tokenShopInventory, 32, "Golden Apple", "500 Tokens");
        createItem(Material.NETHERITE_SHOVEL, tokenShopInventory, 34, "Netherite Shovel", "50 Tokens");
        createItem(Material.NETHERITE_BOOTS, tokenShopInventory, 35, "Netherite Boots", "800 Tokens");
        createItem(Material.DIAMOND_HOE, tokenShopInventory, 37, "Diamond Hoe", "25 Tokens");
        createItem(Material.NETHERITE_HOE, tokenShopInventory, 43, "Netherite Hoe", "50 Tokens");
        createItem(Material.ENDER_CHEST, tokenShopInventory, 40, "Enderchest", "1500 Tokens");


        ArrayList<String> bookLore = new ArrayList<String>();
        bookLore.add("1500 Tokens");
        ItemStack book = new ItemStack(Material.ENCHANTED_BOOK, 1);
        EnchantmentStorageMeta bookmeta = (EnchantmentStorageMeta) book.getItemMeta();
        bookmeta.addStoredEnchant(Enchantment.MENDING, 1, true);
        bookmeta.setDisplayName(Main.getColorHandler().message + "Mending Book");
        bookmeta.setLore(bookLore);
        book.setItemMeta(bookmeta);
        tokenShopInventory.setItem(13, book);

        player.openInventory(tokenShopInventory);
    }


    public static void createItem(Material material, Inventory inv, int Slot, String name, String lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Main.getColorHandler().main + name);
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(Main.getColorHandler().message + lore);
        meta.setLore(Lore);
        item.setItemMeta(meta);

        inv.setItem(Slot, item);

    }


    @SuppressWarnings("deprecation")
	public static void createItemHead(Inventory inv, int Slot, String name, String lore, String owner) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setDisplayName(Main.getColorHandler().main + name);
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(Main.getColorHandler().message + lore);
        meta.setLore(Lore);
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(owner));
        item.setItemMeta(meta);

        inv.setItem(Slot, item);

    }
    
  /*  public static void petInv(Player player) {
        petInventory = Bukkit.createInventory(null, 27, Main.getColorHandler().main +"Donation Shop: " + Main.getColorHandler().message + "(" + String.valueOf(playerHandler.getDonationPoints(player)) + " Available)");
        createItemHead(petInventory, 0, "Creeper", "Click here to activate pet!", "JasperDG");
        createItemHead(petInventory, 1, "Skeleton", "Click here to activate pet!", "Skeleton");
        createItemHead(petInventory, 2, "Polar bear", "Click here to activate pet!", "mws27");
        createItemHead(petInventory, 3, "Chicken", "Click here to activate pet!", "Gaygus");
        createItemHead(petInventory, 4, "Rabbit", "Click here to activate pet!", "albino");
        createItemHead(petInventory, 5, "Wolf", "Click here to activate pet!", "Unknown");
        createItemHead(petInventory, 6, "Turtle", "Click here to activate pet!", "Matrix");
        createItemHead(petInventory, 7, "Parrot", "Click here to activate pet!", "MHF_Parrot");
        createItemHead(petInventory, 8, "Pig", "Click here to activate pet!", "seranca");
        createItemHead(petInventory, 9, "Sheep", "Click here to activate pet!", "Kolish");
        createItemHead(petInventory, 10, "Ocelot", "Click here to activate pet!", "jarkpzf");
        createItemHead(petInventory, 11, "Fox", "Click here to activate pet!", "Nick_Wilde");
        createItemHead(petInventory, 12, "Spider", "Click here to activate pet!", "Unkown");
        createItemHead(petInventory, 13, "Zombie", "Click here to activate pet!", "DarkClosed");
        createItemHead(petInventory, 14, "Husk", "Click here to activate pet!", "Click here to activate pet!");
        createItemHead(petInventory, 15, "Wither Skeleton", "poisonedsoul", "Click here to activate pet!");


        player.openInventory(petInventory);
    } */

}