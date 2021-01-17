package org.behindbars.gamecore.core.commands;

import org.behindbars.gamecore.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TpaAcceptCMD extends Command {

    private static String name = "tpaccept";
    private static String description = "Teleport to a random location";
    private static String usageMessage = "";
    private static List<String> aliases = Arrays.asList("");

    public TpaAcceptCMD() {
        super(name, description, usageMessage, aliases);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if(sender instanceof ConsoleCommandSender) return true;
        Player player = (Player)sender;

        if (Main.getInstance().getTpa().containsKey(player)) {
            Player player1 = Main.getInstance().getTpa().get(player);
            if (player1 != null && player1.isOnline()) {
                player1.teleport(player.getLocation());
                player1.sendMessage(Main.getColorHandler().main + "TPA: " + Main.getColorHandler().message + "You have teleported to " + player.getName());
                player.sendMessage(Main.getColorHandler().main + "TPA: " + Main.getColorHandler().message + "You have accepted the teleport from " + player1.getName());

                Main.getInstance().getTpa().remove(player);
            } else {
                player.sendMessage(Main.getColorHandler().error + "This player is no longer online");


                Main.getInstance().getTpa().remove(player);
            }
        } else {
            player.sendMessage(Main.getColorHandler().error + "You have no teleport request");

        }

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        return list;
    }
}

