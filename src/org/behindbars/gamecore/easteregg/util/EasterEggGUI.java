/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.easteregg.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import io.github.xenopyax.xenoapi.api.GUIBuilder;
import io.github.xenopyax.xenoapi.api.XItem;

public class EasterEggGUI {
	
	//MAIN
    public void easterEggInventory(Player player) {
        player.openInventory(new GUIBuilder("Easter Eggs", 27)
                .addItem(9, new XItem().setType(Material.YELLOW_DYE).setName("Charlie").addEnchantment(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ENCHANTS).build())
                .addItem(11, new XItem().setType(Material.GREEN_DYE).setName("Bravo").addEnchantment(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ENCHANTS).build())
                .addItem(13, new XItem().setType(Material.BLUE_DYE).setName("Alpha").addEnchantment(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ENCHANTS).build())
                .addItem(15, new XItem().setType(Material.PURPLE_DYE).setName("Elite").addEnchantment(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ENCHANTS).build())
                .addItem(17, new XItem().setType(Material.GRAY_DYE).setName("Center").addEnchantment(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ENCHANTS).build())
                .build(true));
    }

    //CHARLIE
    public void easterEggCharlieInventory(Player player) {
        player.openInventory(new GUIBuilder("Easter Eggs: Charlie", 27)
                .addItem(4, new XItem().setType(Material.YELLOW_DYE).setName("Charlie Easter Eggs").addEnchantment(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ENCHANTS).build())
                .addItem(9, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .addItem(11, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .addItem(13, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .addItem(15, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .addItem(17, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .build(true));
    }

    //BRAVO
    public void easterEggBravoInventory(Player player) {
        player.openInventory(new GUIBuilder("Easter Eggs: Bravo", 27)
                .addItem(4, new XItem().setType(Material.GREEN_DYE).setName("Bravo Easter Eggs").addEnchantment(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build())
                .addItem(9, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .addItem(11, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .addItem(13, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .addItem(15, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .addItem(17, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .build(true));
    }

    //ALPHA
    public void easterEggAlphaInventory(Player player) {
        player.openInventory(new GUIBuilder("Easter Eggs: Alpha", 27)
                .addItem(4, new XItem().setType(Material.BLUE_DYE).setName("Alpha Easter Eggs").addEnchantment(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ENCHANTS).build())
                .addItem(9, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .addItem(11, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .addItem(13, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .addItem(15, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .addItem(17, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .build(true));
    }

    //ELITE
    public void easterEggEliteInventory(Player player) {
        player.openInventory(new GUIBuilder("Easter Eggs: Elite", 27)
                .addItem(4, new XItem().setType(Material.PURPLE_DYE).setName("Elite Easter Eggs").addEnchantment(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ENCHANTS).build())
                .addItem(9, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .addItem(11, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .addItem(13, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .addItem(15, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .addItem(17, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .build(true));
    }

    //CENTER
    public void easterEggCenterInventory(Player player) {
        player.openInventory(new GUIBuilder("Easter Eggs: Center", 27)
                .addItem(4, new XItem().setType(Material.GRAY_DYE).setName("Center Easter Eggs").addEnchantment(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ENCHANTS).build())
                .addItem(9, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .addItem(11, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .addItem(13, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .addItem(15, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .addItem(17, new XItem().setType(Material.PAPER).setName("EGG NAME").build())
                .build(true));
    }


}
