/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.smp;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

public class PortalEvent implements Listener {
	
	@EventHandler
	public void onPortal(PlayerPortalEvent e) {
		if(e.getCause() == TeleportCause.END_PORTAL || e.getCause() == TeleportCause.END_GATEWAY) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cUse /end");
		}
		
		if(e.getCause() == TeleportCause.NETHER_PORTAL) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cUse /nether");
		}
	}

}
