/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.handlers;

import java.lang.reflect.Field;

import org.behindbars.gamecore.Main;
import org.behindbars.gamecore.core.commands.*;
import org.behindbars.gamecore.core.commands.ranks.RankCMD;
import org.behindbars.gamecore.core.commands.ranks.RankupCMD;
import org.behindbars.gamecore.core.commands.smp.NetherCMD;
import org.behindbars.gamecore.core.commands.smp.WildCMD;
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
		registerOverrideCommands();
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
				new KillstreakCMD(), new WarpCMD(), new VoteCMD(), new VoteAdminCMD(), new KillstreakCMD(), new SellCMD(), new SpawnCMD(), new AdminCMD(), new TpaAcceptCMD(),
				new WildCMD(), new NetherCMD(), new TpaCMD());
	}
	
	private void registerOverrideCommands() {
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
