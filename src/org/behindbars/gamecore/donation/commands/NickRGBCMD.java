package org.behindbars.gamecore.donation.commands;

import org.behindbars.gamecore.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class NickRGBCMD extends Command {

    private static String name = "nickrgb";
    private static String description = "";
    private static String usageMessage = "";
    private static List<String> aliases = Arrays.asList("");

    private final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    public NickRGBCMD() {
        super(name, description, usageMessage, aliases);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof ConsoleCommandSender) return true;
        Player player = (Player) sender;
        if(args.length == 0) {
            player.sendMessage(Main.getColorHandler().usage + "/nickrgb <######> <######>  | DO NOT INCLUDE #");
            return true;
        }
            setRGBNick(player, args[0], args[1]);

        return true;
    }

    public void setRGBNick(Player player, String hexFrom, String hexTo) {

        if((hexFrom.length() != 6 || (hexTo.length() != 6))) {
            player.sendMessage(Main.getColorHandler().error + "Your hex number is either to long or to short!");
            return;
        }

        if(!hexFrom.matches("[a-zA-Z0-9_]*")){
            player.sendMessage(Main.getColorHandler().error + "You can only type letters and numbers in!");
            return;
        }
        if(!hexTo.matches("[a-zA-Z0-9_]*")){
            player.sendMessage(Main.getColorHandler().error + "You can only type letters and numbers in!");
            return;
        }

        try {
            Main.getPlayerHandler(player).setNickname(Main.getXenoAPI().gradient(player.getName(), hexFrom, hexTo, false, false, false, false, false));
            player.setCustomName(Main.getPlayerHandler(player).rankToString() + Main.getPlayerHandler(player).getNickname());
            player.setPlayerListName(Main.getPlayerHandler(player).rankToString() + Main.getPlayerHandler(player).getNickname());
            player.sendMessage(Main.getColorHandler().donation + "You changed your nickname to " + Main.getPlayerHandler(player).getNickname());
        } catch (NumberFormatException ignored) {
            player.sendMessage(Main.getColorHandler().error + "You have typed an invalid HEX code in!");
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        return list;
    }
}
