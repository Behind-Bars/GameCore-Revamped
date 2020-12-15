/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.handlers;

import java.lang.reflect.Field;

import org.behindbars.gamecore.core.commands.BountyCMD;
import org.behindbars.gamecore.core.commands.BroadcastCMD;
import org.behindbars.gamecore.core.commands.ClearChatCMD;
import org.behindbars.gamecore.core.commands.ClearLagCMD;
import org.behindbars.gamecore.core.commands.DisposeCMD;
import org.behindbars.gamecore.core.commands.DonationPointCMD;
import org.behindbars.gamecore.core.commands.FilterCMD;
import org.behindbars.gamecore.core.commands.FlyCMD;
import org.behindbars.gamecore.core.commands.GamemodeCMD;
import org.behindbars.gamecore.core.commands.HelpCMD;
import org.behindbars.gamecore.core.commands.HomeCMD;
import org.behindbars.gamecore.core.commands.KitCMD;
import org.behindbars.gamecore.core.commands.ListCMD;
import org.behindbars.gamecore.core.commands.PayCMD;
import org.behindbars.gamecore.core.commands.PlayerHeadCMD;
import org.behindbars.gamecore.core.commands.PrivateMessageCM;
import org.behindbars.gamecore.core.commands.ReferCMD;
import org.behindbars.gamecore.core.commands.RenameCMD;
import org.behindbars.gamecore.core.commands.ReplyCMD;
import org.behindbars.gamecore.core.commands.SetupMapCMD;
import org.behindbars.gamecore.core.commands.SpeedCMD;
import org.behindbars.gamecore.core.commands.TeleportCMD;
import org.behindbars.gamecore.core.commands.VanishCMD;
import org.behindbars.gamecore.core.commands.WorldCMD;
import org.behindbars.gamecore.core.commands.ranks.RankCMD;
import org.behindbars.gamecore.core.commands.ranks.RankupCMD;
import org.behindbars.gamecore.core.commands.token.TokenCMD;
import org.behindbars.gamecore.core.commands.token.TokenEconomyCMD;
import org.behindbars.gamecore.core.commands.token.TokenShopCMD;
import org.behindbars.gamecore.donation.commands.CraftCMD;
import org.behindbars.gamecore.donation.commands.DiscoArmorCMD;
import org.behindbars.gamecore.donation.commands.EventCMD;
import org.behindbars.gamecore.donation.commands.FeedCMD;
import org.behindbars.gamecore.donation.commands.HatCMD;
import org.behindbars.gamecore.donation.commands.NamecolorCDM;
import org.behindbars.gamecore.donation.commands.NicknameCMD;
import org.behindbars.gamecore.donation.commands.PvPCMD;
import org.behindbars.gamecore.donation.commands.SellCMD;
import org.behindbars.gamecore.donation.commands.SpawnCMD;
import org.behindbars.gamecore.economy.commands.EconomyCMD;
import org.behindbars.gamecore.economy.commands.MoneyCMD;
import org.behindbars.gamecore.pets.commands.PetCMD;
import org.behindbars.gamecore.trails.commands.TrailsCMD;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

public class CommandHandler {
	
	private CommandMap cmdMap;
	
	public CommandHandler() {
		setupCommandMap();
		registerCommands();
	}
	
	private void setupCommandMap() {  
        try {
	        final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
	        bukkitCommandMap.setAccessible(true);
	        cmdMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());       
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
        	e.printStackTrace();
        }
	}
	
	public void registerCommands() {
		registerCommand(new BroadcastCMD(), new CraftCMD(), new DiscoArmorCMD(), new EventCMD(), new FeedCMD(), new HatCMD(), new MoneyCMD(), new EconomyCMD(),
				new NamecolorCDM(), new NicknameCMD(), new PetCMD(), new PvPCMD(), new SellCMD(), new SpawnCMD(), new TrailsCMD(), new ClearChatCMD(), new ClearLagCMD(),
				new DisposeCMD(), new DonationPointCMD(), new FlyCMD(), new GamemodeCMD(), new PlayerHeadCMD(), new RankCMD(), new RankupCMD(), new RenameCMD(),
				new SetupMapCMD(), new SpeedCMD(), new TeleportCMD(), new TokenCMD(), new TokenShopCMD(), new TokenEconomyCMD(), new VanishCMD(), new WorldCMD(),
				new HomeCMD(), new BountyCMD(), new FilterCMD(), new HelpCMD(), new KitCMD(), new ListCMD(), new PayCMD(), new PrivateMessageCM(), new ReplyCMD(),
				new ReferCMD());
	}

	private void registerCommand(Command... commands) {
		for(Command command : commands) {
			cmdMap.register(command.getName(), command);
		}
	}

}
