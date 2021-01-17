package org.behindbars.gamecore.core.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.behindbars.gamecore.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TpaCMD extends Command {

    private static String name = "tpa";
    private static String description = "Request to teleport to a player";
    private static String usageMessage = "";
    private static List<String> aliases = Arrays.asList("");

    public TpaCMD() {
        super(name, description, usageMessage, aliases);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if(sender instanceof ConsoleCommandSender) return true;
        Player player = (Player)sender;

        if (args.length == 1) {
            Player player2 = Bukkit.getPlayer(args[0]);
            if (player2 != null && player2.isOnline()) {

                if (!player.getName().equals(player2.getName())) {
                    if (!Main.getInstance().getTpa().containsKey(player)) {
                        Main.getInstance().getTpa().put(player2, player);

                        TextComponent txt = new TextComponent();
                        txt.setText(Main.getColorHandler().usage + "Click here to accept the teleport");
                        txt.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                new ComponentBuilder("§a§lClick to Accept").create()));
                        txt.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND,
                                "/tpaccept"));
                        player2.sendMessage(Main.getColorHandler().main + "TPA: " + Main.getColorHandler().message + player.getName()
                                + " Has asked you to teleport to you");
                        player2.spigot().sendMessage(txt);
                        player.sendMessage(Main.getColorHandler().main + "TPA: " + Main.getColorHandler().message + "You have requested to teleport to " +  player.getName());
                    } else {
                        player.sendMessage(Main.getColorHandler().usage + "You have already requested teleport to " + player2.getName());

                    }
                } else {
                    player.sendMessage(Main.getColorHandler().error + "You can not teleport to yourself!");

                }

            } else {
                player.sendMessage(Main.getColorHandler().offlinePlayer);

            }
        } else {
            player.sendMessage(Main.getColorHandler().usage + "/tpa <player>");
        }




        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        return list;
    }
}
