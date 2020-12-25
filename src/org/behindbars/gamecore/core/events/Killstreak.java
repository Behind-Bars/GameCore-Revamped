/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
 */

package org.behindbars.gamecore.core.events;

import org.behindbars.gamecore.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Killstreak implements Listener {

	@EventHandler
	public void onKIll(PlayerDeathEvent event) {
		event.setDeathMessage(null);

		if (!(event.getEntity().getKiller() instanceof Player)) {
			Player killed = event.getEntity();
			Main.getPlayerHandler(killed).addDeath(1);
			Main.getPlayerHandler(killed).resetKillStreak(0);
			Main.getPlayerHandler(killed).setDonateDiscoArmorStatus(0);
			return;
		}else if(event.getEntity() instanceof Player && event.getEntity().getKiller() instanceof Player) {
			Player killer = event.getEntity().getKiller();
			Player killed = event.getEntity();
			Main.getPlayerHandler(killer).addToKillStreak(1);
			Main.getPlayerHandler(killed).resetKillStreak(0);
			Main.getPlayerHandler(killer).addKill(1);
			Main.getPlayerHandler(killed).setDonateDiscoArmorStatus(0);
			Main.getPlayerHandler(killed).addDeath(1);
			Main.getPlayerHandler(killed).setLastKilledByPlayer(event.getEntity().getKiller().getName());

			if(Main.getPlayerHandler(killer).getKillStreak() <= 30 && Main.getPlayerHandler(killer).getKillStreak() % 5 == 0) {
				Bukkit.getServer().broadcastMessage(Main.getPlayerHandler(killer).rankToColorString() + killer.getName() 
					+ Main.getColorHandler().message + " has reached a kill streak of " + Main.getPlayerHandler(killer).getKillStreak());
			}

			if (Main.getPlayerHandler(killed).getRank() > 5) {
				Main.getPlayerHandler(killer).addToken(5);
				Main.getPlayerHandler(killer).setPlayersKilledReward(Main.getPlayerHandler(killer).getPlayersKilledReward() + 1);
				return;
			}else {
				Main.getPlayerHandler(killer).addToken(1);
				Main.getPlayerHandler(killer).setPlayersKilledReward(Main.getPlayerHandler(killer).getPlayersKilledReward() + 1);
			}
		}

	}

}
