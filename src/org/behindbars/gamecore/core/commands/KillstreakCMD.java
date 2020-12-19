package org.behindbars.gamecore.core.commands;

import net.md_5.bungee.api.ChatColor;
import org.behindbars.gamecore.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KillstreakCMD extends Command {

    private static String name = "killstreak";
    private static String description = "broadcast a message to the entire server";
    private static String usageMessage = "";
    private static List<String> aliases = Arrays.asList("");

    public KillstreakCMD() {
        super(name, description, usageMessage, aliases);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof ConsoleCommandSender) return true;
        Player player = (Player) sender;

        int kills = Main.getPlayerHandler(player).getKills();
        double deaths = Main.getPlayerHandler(player).getDeaths();
        double ratio = kills / deaths;

        player.sendMessage(Main.getColorHandler().main + "User: " + Main.getColorHandler().message + player.getName());
        player.sendMessage(Main.getColorHandler().main + "Kill Streak: " + Main.getColorHandler().message + Main.getPlayerHandler(player).getKillStreak());
        player.sendMessage(Main.getColorHandler().main + "Kills: " + Main.getColorHandler().message + Main.getPlayerHandler(player).getKills());
        player.sendMessage(Main.getColorHandler().main + "Deaths: " + Main.getColorHandler().message + Main.getPlayerHandler(player).getDeaths());
        player.sendMessage(Main.getColorHandler().main + "Last Killed By: " + Main.getColorHandler().message + Main.getPlayerHandler(player).getLKBP());
        player.sendMessage(Main.getColorHandler().main + "K/D Ratio: " + Main.getColorHandler().message + String.valueOf(ratio));
        
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        return list;
    }

}

