/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.handlers;

import java.lang.reflect.Field;

import org.behindbars.gamecore.Main;
import org.behindbars.gamecore.core.commands.BailCMD;
import org.behindbars.gamecore.core.commands.BanCMD;
import org.behindbars.gamecore.core.commands.BountyCMD;
import org.behindbars.gamecore.core.commands.BroadcastCMD;
import org.behindbars.gamecore.core.commands.BuyCMD;
import org.behindbars.gamecore.core.commands.ClearChatCMD;
import org.behindbars.gamecore.core.commands.ClearLagCMD;
import org.behindbars.gamecore.core.commands.ContrabandCMD;
import org.behindbars.gamecore.core.commands.DisposeCMD;
import org.behindbars.gamecore.core.commands.DonationAdminCMD;
import org.behindbars.gamecore.core.commands.DonationCMD;
import org.behindbars.gamecore.core.commands.DonationPointCMD;
import org.behindbars.gamecore.core.commands.FilterCMD;
import org.behindbars.gamecore.core.commands.FlyCMD;
import org.behindbars.gamecore.core.commands.GamemodeCMD;
import org.behindbars.gamecore.core.commands.HelpCMD;
import org.behindbars.gamecore.core.commands.HomeCMD;
import org.behindbars.gamecore.core.commands.JailCMD;
import org.behindbars.gamecore.core.commands.KickCMD;
import org.behindbars.gamecore.core.commands.KitCMD;
import org.behindbars.gamecore.core.commands.ListCMD;
import org.behindbars.gamecore.core.commands.MuteCMD;
import org.behindbars.gamecore.core.commands.PayCMD;
import org.behindbars.gamecore.core.commands.PlayerHeadCMD;
import org.behindbars.gamecore.core.commands.PrivateMessageCM;
import org.behindbars.gamecore.core.commands.ReferCMD;
import org.behindbars.gamecore.core.commands.RenameCMD;
import org.behindbars.gamecore.core.commands.ReplyCMD;
import org.behindbars.gamecore.core.commands.RulesCMD;
import org.behindbars.gamecore.core.commands.ScoreboardToggle;
import org.behindbars.gamecore.core.commands.SetupMapCMD;
import org.behindbars.gamecore.core.commands.SpeedCMD;
import org.behindbars.gamecore.core.commands.StatsCMD;
import org.behindbars.gamecore.core.commands.SuicideCMD;
import org.behindbars.gamecore.core.commands.TeleportCMD;
import org.behindbars.gamecore.core.commands.TempbanCMD;
import org.behindbars.gamecore.core.commands.UnbanCMD;
import org.behindbars.gamecore.core.commands.UnjailCMD;
import org.behindbars.gamecore.core.commands.VanishCMD;
import org.behindbars.gamecore.core.commands.VoteAdminCMD;
import org.behindbars.gamecore.core.commands.VoteCMD;
import org.behindbars.gamecore.core.commands.WarnCMD;
import org.behindbars.gamecore.core.commands.WarpCMD;
import org.behindbars.gamecore.core.commands.WorldCMD;
import org.behindbars.gamecore.core.commands.ranks.RankCMD;
import org.behindbars.gamecore.core.commands.ranks.RankupCMD;
import org.behindbars.gamecore.core.commands.token.TokenCMD;
import org.behindbars.gamecore.core.commands.token.TokenEconomyCMD;
import org.behindbars.gamecore.core.commands.token.TokenShopCMD;
import org.behindbars.gamecore.donation.commands.*;
import org.behindbars.gamecore.economy.commands.EconomyCMD;
import org.behindbars.gamecore.economy.commands.MoneyCMD;
import org.behindbars.gamecore.killstreak.KillstreakCMD;
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
		regiserOverrideCommands();
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
	
	private void registerCommands() {
		registerCommand(new BroadcastCMD(), new CraftCMD(), new DiscoArmorCMD(), new EventCMD(), new FeedCMD(), new HatCMD(), new MoneyCMD(), new EconomyCMD(),
				new NamecolorCDM(), new NicknameCMD() , new NickRGBCMD(), new PetCMD(), new PvPCMD(), new SellCMD(), new SpawnCMD(), new TrailsCMD(), new ClearChatCMD(), new ClearLagCMD(),
				new DisposeCMD(), new DonationPointCMD(), new FlyCMD(), new PlayerHeadCMD(), new RankCMD(), new RankupCMD(), new RenameCMD(),
				new SetupMapCMD(), new SpeedCMD(), new TeleportCMD(), new TokenCMD(), new TokenShopCMD(), new TokenEconomyCMD(), new VanishCMD(), new WorldCMD(),
				new HomeCMD(), new BountyCMD(), new FilterCMD(), new HelpCMD(), new KitCMD(), new ListCMD(), new PayCMD(), new PrivateMessageCM(), new ReplyCMD(),
				new ReferCMD(), new RulesCMD(), new ScoreboardToggle(), new StatsCMD(), new SuicideCMD(), new ContrabandCMD(), new JailCMD(), new UnjailCMD(), new BailCMD(),
				new MuteCMD(), new BanCMD(), new UnbanCMD(), new TempbanCMD(), new KickCMD(), new WarnCMD(), new DonationCMD(), new BuyCMD(), new DonationAdminCMD(),
				new KillstreakCMD(), new WarpCMD(), new VoteCMD(), new VoteAdminCMD(), new KillstreakCMD(), new SellCMD(), new SpawnCMD(), new AdminCMD());
	}
	
	private void regiserOverrideCommands() {
		GamemodeCMD gmcmd = new GamemodeCMD();
		Main.getInstance().getCommand("gamemode").setExecutor(gmcmd);
		Main.getInstance().getCommand("gamemode").setTabCompleter(gmcmd);
	}
	
	private void registerCommand(Command... commands) {
		for(Command command : commands) {
			cmdMap.register(command.getName(), command);
		}
	}

}
