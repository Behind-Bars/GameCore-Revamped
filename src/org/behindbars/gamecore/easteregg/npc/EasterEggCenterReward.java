package org.behindbars.gamecore.easteregg.npc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class EasterEggCenterReward {

    public void setupCenterRewards(Player player) {

        //#1
        Location reward1 = new Location(Bukkit.getWorld("world"), 4.5,127,4.5, 135f, 25f);
        Villager Vreward1 = (Villager) player.getWorld().spawnEntity(reward1, EntityType.VILLAGER);
        Vreward1.setInvulnerable(true);
        Vreward1.setCollidable(false);
        Vreward1.setAI(false);
        Vreward1.setProfession(Villager.Profession.LIBRARIAN);
        Vreward1.setCustomName(ChatColor.GRAY + ChatColor.BOLD.toString() + "Easter Egg #1");
        Vreward1.setCustomNameVisible(false);

        //#1
        Location reward2 = new Location(Bukkit.getWorld("world"), 4.5,127,4.5, 135f, 25f);
        Villager Vreward2 = (Villager) player.getWorld().spawnEntity(reward2, EntityType.VILLAGER);
        Vreward2.setInvulnerable(true);
        Vreward2.setCollidable(false);
        Vreward2.setAI(false);
        Vreward2.setProfession(Villager.Profession.LIBRARIAN);
        Vreward2.setCustomName(ChatColor.GRAY + ChatColor.BOLD.toString() + "Easter Egg #2");
        Vreward2.setCustomNameVisible(false);

        //#1
        Location reward3 = new Location(Bukkit.getWorld("world"), 4.5,127,4.5, 135f, 25f);
        Villager Vreward3 = (Villager) player.getWorld().spawnEntity(reward3, EntityType.VILLAGER);
        Vreward3.setInvulnerable(true);
        Vreward3.setCollidable(false);
        Vreward3.setAI(false);
        Vreward3.setProfession(Villager.Profession.LIBRARIAN);
        Vreward3.setCustomName(ChatColor.GRAY + ChatColor.BOLD.toString() + "Easter Egg #3");
        Vreward3.setCustomNameVisible(false);

        //#1
        Location reward4 = new Location(Bukkit.getWorld("world"), 4.5,127,4.5, 135f, 25f);
        Villager Vreward4 = (Villager) player.getWorld().spawnEntity(reward4, EntityType.VILLAGER);
        Vreward4.setInvulnerable(true);
        Vreward4.setCollidable(false);
        Vreward4.setAI(false);
        Vreward4.setProfession(Villager.Profession.LIBRARIAN);
        Vreward4.setCustomName(ChatColor.GRAY + ChatColor.BOLD.toString() + "Easter Egg #4");
        Vreward4.setCustomNameVisible(false);

        //#5
        Location reward5 = new Location(Bukkit.getWorld("world"), 4.5,127,4.5, 135f, 25f);
        Villager Vrewardr5 = (Villager) player.getWorld().spawnEntity(reward5, EntityType.VILLAGER);
        Vrewardr5.setInvulnerable(true);
        Vrewardr5.setCollidable(false);
        Vrewardr5.setAI(false);
        Vrewardr5.setProfession(Villager.Profession.LIBRARIAN);
        Vrewardr5.setCustomName(ChatColor.GRAY + ChatColor.BOLD.toString() + "Easter Egg #5");
        Vrewardr5.setCustomNameVisible(false);



    }

}
