package org.behindbars.gamecore.smp;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetupSMPCMD extends Command {

    private static String name = "setupSMP";
    private static String description = "";
    private static String usageMessage = "";
    private static List<String> aliases = Arrays.asList("");

    public SetupSMPCMD() {
        super(name, description, usageMessage, aliases);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof ConsoleCommandSender) return true;
        Player player = (Player) sender;


        Location kitVillager = new Location(Bukkit.getWorld("SMP"), 32532.5, 71, -15533.5, 180, 0);
        Location wildVillager = new Location(Bukkit.getWorld("SMP"), 32543.5, 71, -15546.5, 46, 0);

        
        Villager kit = (Villager) player.getWorld().spawnEntity(kitVillager, EntityType.VILLAGER);
        kit.setInvulnerable(true);
        kit.setCollidable(false);
        kit.setAI(false);
        kit.setProfession(Villager.Profession.TOOLSMITH);
        kit.setCustomName(ChatColor.RED + ChatColor.BOLD.toString() + "Kit Tools");
        kit.setCustomNameVisible(true);


        Villager wild = (Villager) player.getWorld().spawnEntity(wildVillager, EntityType.VILLAGER);
        wild.setInvulnerable(true);
        wild.setCollidable(false);
        wild.setAI(false);
        wild.setProfession(Villager.Profession.SHEPHERD);
        wild.setCustomName(ChatColor.RED + ChatColor.BOLD.toString() + "Wild TP");
        wild.setCustomNameVisible(true);


        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        if (sender instanceof ConsoleCommandSender) return list;
        Player player = (Player) sender;
        return list;
    }
}

//32532.5, 71, -15533.5, 180, 0);
