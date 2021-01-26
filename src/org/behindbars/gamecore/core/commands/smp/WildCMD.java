package org.behindbars.gamecore.core.commands.smp;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class WildCMD extends Command {
    private HashMap<String, Long> wildCooldowns = new HashMap<String, Long>();

    private static String name = "wild";
    private static String description = "Teleport to a random location";
    private static String usageMessage = "";
    private static List<String> aliases = Arrays.asList("");

    public WildCMD() {
        super(name, description, usageMessage, aliases);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if(sender instanceof ConsoleCommandSender) return true;
        Player player = (Player)sender;

        if(!player.getWorld().getName().equalsIgnoreCase("SMP")) {
            player.sendMessage(org.bukkit.ChatColor.DARK_RED + "ERROR: " + org.bukkit.ChatColor.RED + "You can only run this command in the SMP world!");
            return true;
        }

        Long time = System.currentTimeMillis();
        try {
            Long lastUse = this.wildCooldowns.get(player.getName());
			/*if (lastUse + 2*1000 > time) {
				player.sendMessage(ChatColor.GREEN + "Please wait 3 seconds to send your message again!!");
				event.setCancelled(true);
			}*/
            if (lastUse + 5*60*1000 > time) {
                player.sendMessage(net.md_5.bungee.api.ChatColor.GREEN + "You can only preform this command every five minutes!");
                return true;
            }
        } catch (Exception ex) {
        }
        try {
            wildCooldowns.remove(player.getName());
        } catch (Exception ex) {
        }
        wildCooldowns.put(player.getName(), time);
    

        player.sendMessage(org.bukkit.ChatColor.DARK_RED + "Wild: " + ChatColor.RED + "Teleporting to a random location!");
        Location loc = getRandomLoc(40000, -40000, 40000, -40000, player.getWorld());
        loc.getChunk().load(true);
        if(loc.getChunk().load()) {
        	player.teleport(loc);
        }


        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        return list;
    }

    public Location getRandomLoc(int xmax, int xmin, int zmax, int zmin, World w) {
        Random r = new Random();
        double x = r.nextInt(xmax - xmin) + xmin + 1.0;
        double z = r.nextInt(zmax-zmin) + zmin + 1.0;
        double y = w.getHighestBlockYAt((int) x, (int) z) + 1.0;
        Location loc = new Location(w, x, y, z);
        if(isBlockSafe(loc.getBlock())) return loc;        
        return getRandomLoc(xmax, xmin, zmax, zmin, w);
    }
    
    private boolean isBlockSafe(Block block) {
    	if(block == null) return false;
		switch(block.getType()) {
		case LAVA:
		case WATER:
		case MAGMA_BLOCK:
		case CACTUS:
		case FIRE:
		case SWEET_BERRY_BUSH:
		case CAMPFIRE:
		case KELP:
			return false;
		default:
			return true;
		}
	}

}
