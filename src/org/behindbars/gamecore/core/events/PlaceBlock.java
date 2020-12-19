/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.events;

import org.behindbars.gamecore.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlaceBlock implements Listener {
	
	@EventHandler
	public void onPlaceBlock(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if(event.isCancelled()) {
			event.setCancelled(true);
			event.getPlayer().getWorld().getBlockAt(event.getBlock().getLocation()).setType(Material.AIR);
			return;
		}

		String blockName = event.getBlock().getType().toString();
	/*	boolean isRedstone = redstoneLimit.getRestrictedBlocks().contains(blockName);

		if (!isRedstone || !redstoneLimit.restrict()) {
			event.setCancelled(false);
			return;
		}

		final int minX = 0;
		final int minZ = 0;
		final int maxX = 15;
		final int maxY = 255;
		final int maxZ = 15;
		int count = 0;

		for (int x = minX; x <= maxX; ++x) {
			for (int y = 0; y <= maxY; ++y) {
				for (int z = minZ; z <= maxZ; ++z) {
					blockName = event.getBlock().getChunk().getBlock(x, y, z).getType().toString();
					isRedstone = redstoneLimit.getRestrictedBlocks().contains(blockName);

					if (isRedstone) {
						count++;

					}
				}
			}

			if (count == (redstoneLimit.getRedstoneLimit() + 1) || (count > redstoneLimit.getRedstoneLimit() + 1)) {
				if (redstoneLimit.getPlayerBypass(event.getPlayer().getUniqueId())) {
					event.getPlayer().sendMessage(Utils.colour("&3This chunk exceeds maximum redstone, but you bypassed!"));
					event.setCancelled(false);
					return;
				}

				event.getPlayer().sendMessage(Utils.colour("&cThis chunk exceeds maximum redstone!"));
				event.setCancelled(true);
				return;
			}

			event.setCancelled(false);
		}*/

		Main.getPlayerHandler(player).setBlocksPlaced(Main.getPlayerHandler(player).getBlocksPlaced() + 1);

		Location fix = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
		if (event.isCancelled()) {

			int playerX = player.getLocation().getBlockX();
			int playerY = player.getLocation().getBlockY();
			int playerZ = player.getLocation().getBlockZ();
			Location loc = new Location(player.getWorld(), playerX, playerY - 1, playerZ);

			Block block = loc.getBlock();

			if(getBlockBelowLoc(player.getLocation()).getY() +3 < player.getLocation().getY()&& player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.AIR && !player.isOnGround()) {
				Location loc2 = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() +1, player.getLocation().getZ(), player.getLocation().getYaw(), -90f);
				event.getPlayer().teleport(getBlockBelowLoc(player.getLocation()));
				event.getPlayer().teleport(loc2);
				event.getPlayer().sendMessage(Main.getColorHandler().error + "Suspected block glitching!");
				return;
			}

			if (block.getType() == player.getInventory().getItemInMainHand().getType() && !player.isOnGround()) {
				event.setCancelled(true);
				Location loc2 = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() - 1, player.getLocation().getZ(), player.getLocation().getYaw(), -90f);
				event.getPlayer().teleport(loc2);
				event.getPlayer().sendMessage(Main.getColorHandler().error + "Suspected block glitching!");

			}
		}
	}

	public Location getBlockBelowLoc(Location loc) {
		Location locBelow = loc.subtract(0, 1, 0);
		if(locBelow.getBlock().getType() == Material.AIR) {
			locBelow = getBlockBelowLoc(locBelow);
		}
		return locBelow;
	}

	public boolean isAboveGround(Player player, int checkLevels) {

		World world = player.getWorld();
		Location location = player.getLocation();

		if (location.getBlock().getType() == Material.AIR && !location.getBlock().getType().name().contains("SLAB")) {
			for (int i = 1; i < checkLevels; i++) {
				if (world.getBlockAt((int)location.getX(), location.getBlockY() - i, (int)location.getY()).getType() != Material.AIR) {
					return false;
				}
			}
		}

		return true;
	}

	public static boolean isSafeLocation(Location location) {
		Block feet = location.getBlock();
		if (!feet.getType().isTransparent() && !feet.getLocation().add(0, 1, 0).getBlock().getType().isTransparent()) {
			return false; // not transparent (will suffocate)
		}
		Block head = feet.getRelative(BlockFace.UP);
		if (!head.getType().isTransparent()) {
			return false; // not transparent (will suffocate)
		}
		Block ground = feet.getRelative(BlockFace.DOWN);
		if (!ground.getType().isSolid()) {
			return false; // not solid
		}
		return true;
	}

}
