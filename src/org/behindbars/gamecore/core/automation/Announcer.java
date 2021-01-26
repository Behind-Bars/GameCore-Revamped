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
	
	private final String[] messages = {"You can now visit our website at: \n http://play.behind-bars.org", "Remember share the \n IP: behind-bars.org", "Join the discord! \n https://discord.gg/smN32dP4H9", "Any complaints, reports, or appeals please refer to the discord until the website is setup! Please refer to the ticket system! Evidence Please.", "This server is currently in BETA, we are sorry in advanced for any issues you may run into! Please report these on the discord.", "Opening mod applications soon! Stay tuned!", "Remember to constantly be checking the rules with '/rules' for any updates.", "I'm being held here against my own will :("};
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
