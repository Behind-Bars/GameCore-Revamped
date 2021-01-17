package org.behindbars.gamecore.core.commands.smp;

import org.behindbars.gamecore.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WildCMD extends Command {

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

        player.sendMessage(org.bukkit.ChatColor.DARK_RED + "Wild: " + ChatColor.RED + "Teleporting to a random location!");
        player.teleport(getRandomLoc(40000, -40000, 40000, -40000, player.getWorld()));


        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        return list;
    }

    public Location getRandomLoc(int xmax, int xmin, int zmax, int zmin, World w){

        Random r = new Random();
        double x = r.nextInt(xmax - xmin) + xmin + 1.0;
        double z = r.nextInt(zmax-zmin) + zmin + 1.0;
        double y = w.getHighestBlockYAt((int) x, (int) z) + 1.0;

        return new Location(w, x, y, z);



    }

}
