package org.behindbars.gamecore.smp;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.behindbars.gamecore.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.HashMap;
import java.util.Random;

public class CommandWild implements Listener {



    @EventHandler
    public void onCOmmand(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage();
        String[] args = message.split(" ");
        Player player = event.getPlayer();

        if(args[0].equalsIgnoreCase("/wild")) {
            event.setCancelled(true);
            if(!player.getWorld().getName().equalsIgnoreCase("SMP")) {
                player.sendMessage(ChatColor.DARK_RED + "ERROR: " + ChatColor.RED + "You can only run this command in the SMP world!");
                return;
            }

            player.sendMessage(ChatColor.DARK_RED + "Wild: " + ChatColor.RED + "Teleporting to a random location!");
            player.teleport(getRandomLoc(40000, -40000, 40000, -40000, player.getWorld()));
            return;
        }

        if (args[0].equalsIgnoreCase("/tpa")) {
                event.setCancelled(true);
                if (args.length == 2) {
                    Player player2 = Bukkit.getPlayer(args[1]);
                    if (player2 != null && player2.isOnline()) {

                        if(!player.getName().equals(player2.getName())) {
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
                            } else {
                                player.sendMessage(Main.getColorHandler().usage + "You have already requested teleport to " + player2.getName());

                            }
                        }else {
                            player.sendMessage(Main.getColorHandler().error + "You can not teleport to yourself!");

                        }

                    } else {
                        player.sendMessage(Main.getColorHandler().offlinePlayer);

                    }
                } else {
                    player.sendMessage(Main.getColorHandler().usage + "/tpa <player>");
                }
            return;
            }


        if (args[0].equalsIgnoreCase("/tpaccept")) {
                event.setCancelled(true);
                if (Main.getInstance().getTpa().containsKey(player)) {
                    Player player1 = Main.getInstance().getTpa().get(player);
                    if (player1 != null && player1.isOnline()) {
                        player1.teleport(player.getLocation());
                        player1.sendMessage(Main.getColorHandler().main + "TPA: " + Main.getColorHandler().message + "You have teleported to "+player.getName());
                        player.sendMessage(Main.getColorHandler().main + "TPA: " + Main.getColorHandler().message + "You have accepted the teleport from "+player1.getName());

                        Main.getInstance().getTpa().remove(player);
                    } else {
                        player.sendMessage(Main.getColorHandler().error + "This player is no longer online");


                        Main.getInstance().getTpa().remove(player);
                    }
                } else {
                    player.sendMessage(Main.getColorHandler().error + "You have no teleport request");

                }
            }
            return;
        }






    public Location getRandomLoc(int xmax, int xmin, int zmax, int zmin, World w){

        Random r = new Random();
        double x = r.nextInt(xmax - xmin) + xmin + 1.0;
        double z = r.nextInt(zmax-zmin) + zmin + 1.0;
        double y = w.getHighestBlockYAt((int) x, (int) z) + 1.0;
        return new Location(w, x, y, z);
    }
}
