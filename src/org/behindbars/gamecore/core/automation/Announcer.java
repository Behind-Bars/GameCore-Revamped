/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.automation;

import org.behindbars.gamecore.Main;
import org.bukkit.Bukkit;

import net.md_5.bungee.api.ChatColor;

public class Announcer {
	
	private final String[] messages = {"You can now visit our website at: \n http://play.behind-bars.org", "Hellos", "This is the auto broadcaster"};
    private final String broadcastPrefix = ChatColor.RED + ChatColor.BOLD.toString() + ">> " + ChatColor.WHITE + ChatColor.ITALIC.toString();

    public Announcer() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                broadcast(getRandomMessage());

            }
        }, 0L, 6000L);
    }
    public String getRandomMessage(){
        return messages[(int) (Math.random() * messages.length)];
    }

    public void broadcast(String message) {
        Bukkit.getServer().broadcastMessage("");
        Bukkit.getServer().broadcastMessage(broadcastPrefix + message);
        Bukkit.getServer().broadcastMessage("");
    }

}
