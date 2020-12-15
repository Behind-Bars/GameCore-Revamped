/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.easteregg.events;

import org.behindbars.gamecore.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import net.md_5.bungee.api.ChatColor;

public class EasterEggClickEvent {
	
	@EventHandler
    public void playerInteractCrate(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Location spawn = new Location(Bukkit.getWorld("world"), -0.5,125,-0.5);

        if (event.getRightClicked().getName().startsWith(ChatColor.YELLOW + ChatColor.BOLD.toString())) {

            event.setCancelled(true);

            String name = event.getRightClicked().getName().replaceFirst(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Easter Egg #", "");

            long elapsedTime = System.currentTimeMillis() - Main.getPlayerHandler(player).getEastereggDate("Charlie-"+name);
            long timeUntilReuse = ((24 * 60 * 60 * 1000) - elapsedTime);
            if (timeUntilReuse > 0) {
                player.sendMessage(Main.getColorHandler().coolDown + Main.getTimeformatHandler().formatTime(timeUntilReuse));
                return;
            }
            Main.getPlayerHandler(player).setEastereggDate("Charlie-" + name);
            player.sendMessage(name);
            player.teleport(spawn);

        }else if (event.getRightClicked().getName().startsWith(ChatColor.GREEN + ChatColor.BOLD.toString())) {

            event.setCancelled(true);

            String name = event.getRightClicked().getName().replaceFirst(ChatColor.GREEN + ChatColor.BOLD.toString() + "Easter Egg #", "");

            long elapsedTime = System.currentTimeMillis() - Main.getPlayerHandler(player).getEastereggDate("Bravo-" + name);
            long timeUntilReuse = ((24 * 60 * 60 * 1000) - elapsedTime);
            if (timeUntilReuse > 0) {
                player.sendMessage(Main.getColorHandler().coolDown + Main.getTimeformatHandler().formatTime(timeUntilReuse));
                return;
            }
            Main.getPlayerHandler(player).setEastereggDate("Bravo-" +name);
            player.sendMessage(name);
            player.teleport(spawn);

        }else if (event.getRightClicked().getName().startsWith(ChatColor.BLUE + ChatColor.BOLD.toString())) {

            event.setCancelled(true);

            String name = event.getRightClicked().getName().replaceFirst(ChatColor.BLUE + ChatColor.BOLD.toString() + "Easter Egg #", "");

            long elapsedTime = System.currentTimeMillis() - Main.getPlayerHandler(player).getEastereggDate("Alpha-" + name);
            long timeUntilReuse = ((24 * 60 * 60 * 1000) - elapsedTime);
            if (timeUntilReuse > 0) {
                player.sendMessage(Main.getColorHandler().coolDown + Main.getTimeformatHandler().formatTime(timeUntilReuse));
                return;
            }
            Main.getPlayerHandler(player).setEastereggDate("Alpha-" + name);
            player.sendMessage(name);
            player.teleport(spawn);

        }else if (event.getRightClicked().getName().startsWith(ChatColor.DARK_PURPLE + ChatColor.BOLD.toString())) {

            event.setCancelled(true);

            String name = event.getRightClicked().getName().replaceFirst(ChatColor.DARK_PURPLE + ChatColor.BOLD.toString() + "Easter Egg #", "");

            long elapsedTime = System.currentTimeMillis() - Main.getPlayerHandler(player).getEastereggDate("Elite-" + name);
            long timeUntilReuse = ((24 * 60 * 60 * 1000) - elapsedTime);
            if (timeUntilReuse > 0) {
                player.sendMessage(Main.getColorHandler().coolDown + Main.getTimeformatHandler().formatTime(timeUntilReuse));
                return;
            }
            Main.getPlayerHandler(player).setEastereggDate("Elite-" +  name);
            player.sendMessage(name);
            player.teleport(spawn);

        }else if (event.getRightClicked().getName().startsWith(ChatColor.GRAY + ChatColor.BOLD.toString())) {

            event.setCancelled(true);

            String name = event.getRightClicked().getName().replaceFirst(ChatColor.GRAY + ChatColor.BOLD.toString() + "Easter Egg #", "");

            long elapsedTime = System.currentTimeMillis() - Main.getPlayerHandler(player).getEastereggDate("Center-" +name);
            long timeUntilReuse = ((24 * 60 * 60 * 1000) - elapsedTime);
            if (timeUntilReuse > 0) {
                player.sendMessage(Main.getColorHandler().coolDown + Main.getTimeformatHandler().formatTime(timeUntilReuse));
                return;
            }
            Main.getPlayerHandler(player).setEastereggDate("Center-" + name);
            player.sendMessage(name);
            player.teleport(spawn);

        }
    }

}
