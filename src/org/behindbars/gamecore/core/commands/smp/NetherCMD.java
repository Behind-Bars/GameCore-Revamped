package org.behindbars.gamecore.core.commands.smp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NetherCMD extends Command {

    private static String name = "nether";
    private static String description = "Teleport to the Nether";
    private static String usageMessage = "";
    private static List<String> aliases = Arrays.asList("");

    public NetherCMD() {
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

        World nether = Bukkit.getWorld("world_nether");
        player.teleport(nether.getSpawnLocation());

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        return list;
    }
}
