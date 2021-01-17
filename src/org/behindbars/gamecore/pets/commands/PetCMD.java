/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.pets.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.behindbars.gamecore.Main;
import org.behindbars.gamecore.core.handlers.PlayerHandler;
import org.behindbars.gamecore.pets.CustomPet;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_16_R2.ChatComponentText;
import net.minecraft.server.v1_16_R2.EntityTypes;
import net.minecraft.server.v1_16_R2.WorldServer;

public class PetCMD extends Command {

	private static String name = "pet";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");
	
	private PlayerHandler playerHandler;

	public PetCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender)
			return true;
		Player player = (Player) sender;
		playerHandler = new PlayerHandler(player);
		
		if(playerHandler.getDonateFeed() == 0) {
            player.sendMessage(Main.getColorHandler().noPermission);
        }else if(args.length != 1) {
            player.sendMessage(Main.getColorHandler().donation + "/pet <type>");
            player.sendMessage(Main.getColorHandler().donation + "Available Pets: Creeper, Skeleton, Polarbear, Chicken, Rabbit, Wolf, Turtle, Parrot, Pig, Sheep, Ocelot, Fox, Spider, Zombie, Bee");
        } else {

	        /*
	         World world = player.getWorld();//get the world
	        List<Entity> entList = world.getEntities();//get all entities in the world
	        player.sendMessage(colorHandler.main + "Lag: " + colorHandler.message + "Clearing entities!");
	        for(Entity current : entList){//loop through the list
	            if (!(current instanceof Player || current.isCustomNameVisible() == true)){//make sure we aren't deleting mobs/players
	                current.remove();//remove it
	            }
	         */
	
	        //make check to see if player has a pet current time
	
	        player.sendMessage(Main.getColorHandler().donation + "Spawned your pet " + args[0]);
	
	        switch(args[0].toLowerCase()) {
	        case "creeper":
	            makePet(player, EntityTypes.CREEPER);
	            break;
	        case "skeleton":
	            makePet(player, EntityTypes.SKELETON);
	            break;
	        case "polarbear":
	            makePet(player, EntityTypes.POLAR_BEAR);
	            break;
	        case "chicken":
	            makePet(player, EntityTypes.CHICKEN);
	            break;
	        case "rabbit":
	            makePet(player, EntityTypes.RABBIT);
	            break;
	        case "wolf":
	            makePet(player, EntityTypes.WOLF);
	            break;
	        case "turtle":
	            makePet(player, EntityTypes.TURTLE);
	            break;
	        case "parrot":
	            makePet(player, EntityTypes.PARROT);
	            break;
	        case "pig":
	            makePet(player, EntityTypes.PIG);
	            break;	
	        case "sheep":
	            makePet(player, EntityTypes.SHEEP);
	            break;
	        case "ocelot":
	            makePet(player, EntityTypes.OCELOT);
	            break;
	        case "fox":
	            makePet(player, EntityTypes.FOX);
	            break;
	        case "spider":
	            makePet(player, EntityTypes.SPIDER);
	            break;
	        case "zombie":
	            makePet(player, EntityTypes.ZOMBIE);
	            break;
	        case "bee":
	            makePet(player, EntityTypes.BEE);
	            break;
	        default:
	        	player.sendMessage(Main.getColorHandler().donation + "Unknown Pet");
	          	break;
	        }
        }
		
		return true;
	}
	
	public void makePet(Player player, EntityTypes type) {
        CustomPet pet = new CustomPet(player.getLocation(), player, type);
        pet.setCustomName(new ChatComponentText(Main.getColorHandler().message
                + player.getName() + "'s Pet"));
        WorldServer world = ((CraftWorld) player.getWorld()).getHandle();
        world.addEntity(pet);

    }

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		return list;
	}

}
