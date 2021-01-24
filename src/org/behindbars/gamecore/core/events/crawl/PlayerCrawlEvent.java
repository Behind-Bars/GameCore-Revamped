package org.behindbars.gamecore.core.events.crawl;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerCrawlEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private boolean started;
    private boolean stopped;

    public PlayerCrawlEvent(Player player, boolean started, boolean stopped) {
        this.player = player;
        this.started = started;
        this.stopped = stopped;
    }

    public Player getPlayer() {
        return this.player;
    }

    public boolean hasStarted() {
        return this.started;
    }

    public boolean hasStopped() {
        return this.stopped;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
