/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.trails.util;

import org.behindbars.gamecore.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class Effects {
	
	private int taskID;
	private final Player player;

	public Effects(Player player) {
		this.player = player;
	}

	public void startTotem() {
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

			double var = 0;
			Location loc, first, second;
			ParticleData particle = new ParticleData(player.getUniqueId());

			@Override
			public void run() {
				if (!particle.hasID()) {
					particle.setID(taskID);
				}

				var += Math.PI / 16;

				loc = player.getLocation();
				first = loc.clone().add(Math.cos(var), Math.sin(var) + 1, Math.sin(var));
				second = loc.clone().add(Math.cos(var + Math.PI), Math.sin(var) + 1, Math.sin(var + Math.PI));

				player.getWorld().spawnParticle(Particle.TOTEM, first, 0);
				player.getWorld().spawnParticle(Particle.TOTEM, second, 0);
			}

		}, 0, 1);
	}

}
