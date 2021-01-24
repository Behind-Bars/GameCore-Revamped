package org.behindbars.gamecore.core.events.crawl;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Pose;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPoseChangeEvent;
import org.bukkit.event.entity.EntityToggleSwimEvent;
import org.bukkit.event.player.PlayerMoveEvent;


public class CrawlHandler implements Listener {
    private int versionId = Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].replace(".", "#").split("#")[1]);
    private Map<String, Long> playerTimestamp = new HashMap();
    private Map<String, Long> hasActivated = new HashMap();

    public CrawlHandler() {
    }

    @EventHandler
    public void poseChange(EntityPoseChangeEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player)e.getEntity();
            if (e.getPose() == Pose.SWIMMING) {
                if (this.playerTimestamp.containsKey(player.getUniqueId().toString())) {
                    long registeredTime = (Long)this.playerTimestamp.get(player.getUniqueId().toString());
                    long currentTime = System.currentTimeMillis();
                    long delay = 500L;
                    boolean isRealSwimming = currentTime < registeredTime + delay;
                    if (!isRealSwimming) {
                        Bukkit.getPluginManager().callEvent(new PlayerCrawlEvent(player, true, false));
                        this.hasActivated.put(player.getUniqueId().toString(), registeredTime);
                    }
                } else {
                    Bukkit.getPluginManager().callEvent(new PlayerCrawlEvent(player, true, false));
                    this.hasActivated.put(player.getUniqueId().toString(), System.currentTimeMillis());
                }
            } else if (this.hasActivated.containsKey(player.getUniqueId().toString())) {
                this.hasActivated.remove(player.getUniqueId().toString());
                Bukkit.getPluginManager().callEvent(new PlayerCrawlEvent(player, false, true));
            }
        }

    }

    @EventHandler
    public void crawl(EntityToggleSwimEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof Player) {
            Player player = (Player)entity;
            if (e.isSwimming()) {
                if (this.hasActivated.containsKey(player.getUniqueId().toString())) {
                    this.hasActivated.remove(player.getUniqueId().toString());
                }

                this.playerTimestamp.put(player.getUniqueId().toString(), System.currentTimeMillis());
            }
        }

    }

    @EventHandler
    public void moveCrawl(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (e.getTo().getBlock().getType() == Material.AIR && e.getFrom().clone().add(0.0D, 1.0D, 0.0D).getBlock().getType() != Material.AIR) {
            if (!this.hasActivated.containsKey(player.getUniqueId().toString()) && this.playerTimestamp.containsKey(player.getUniqueId().toString())) {
                long registeredTime = (Long)this.playerTimestamp.get(player.getUniqueId().toString());
                long currentTime = System.currentTimeMillis();
                long delay = 50L;
                boolean isRealSwimming = currentTime < registeredTime + delay;
                if (!isRealSwimming) {
                    Bukkit.getPluginManager().callEvent(new PlayerCrawlEvent(player, true, false));
                    this.hasActivated.put(player.getUniqueId().toString(), registeredTime);
                }
            }
        } else {
            if (e.getTo().getBlock().getType() != Material.WATER || e.getFrom().getBlock().getType() != Material.AIR) {
                if (this.versionId >= 13) {
                    if (e.getTo().getBlock().getType() != Material.LEGACY_STATIONARY_WATER) {
                        return;
                    }
                } else if (e.getTo().getBlock().getType() != Material.WATER) {
                    return;
                }

                if (e.getFrom().getBlock().getType() != Material.AIR) {
                    return;
                }
            }

            if (this.hasActivated.containsKey(player.getUniqueId().toString())) {
                this.hasActivated.remove(player.getUniqueId().toString());
                if (this.playerTimestamp.containsKey(player.getUniqueId().toString())) {
                    this.playerTimestamp.remove(player.getUniqueId().toString());
                }

                Bukkit.getPluginManager().callEvent(new PlayerCrawlEvent(player, false, true));
            }
        }

    }
}
