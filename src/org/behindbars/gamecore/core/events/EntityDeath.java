/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.events;

import org.behindbars.gamecore.Main;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeath implements Listener {
	
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

        if(event.getEntity() instanceof Player) {
            Main.getPlayerHandler((Player) event.getEntity()).setCombatLogTime(-1);
            event.getEntity().setGlowing(false);
            return;
        }else if (event.getEntity() instanceof Monster) {
            Monster monsterEnt = (Monster) event.getEntity();
            if (monsterEnt.getKiller() instanceof Player) {
            	Main.getPlayerHandler(monsterEnt.getKiller()).setEntitiesKilledReward(Main.getPlayerHandler(monsterEnt.getKiller()).getEntitiesKilledReward() + 1);
            }
        }
    }

}
