/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.data;

import java.util.UUID;

public class BanInfo {
	
	private long bannedFrom, bannedTo;
	private UUID victim, victimizer;
	private String reason;
	
	public BanInfo(long bannedFrom, long bannedTo, UUID victim, UUID victimizer, String reason) {
		this.bannedFrom = bannedFrom;
		this.bannedTo = bannedTo;
		this.victim = victim;
		this.victimizer = victimizer;
		this.reason = reason;
	}
	
	public long getBannedFrom() {
		return bannedFrom;
	}
	
	public long getBannedTo() {
		return bannedTo;
	}
	
	public UUID getVictim() {
		return victim;
	}
	
	public UUID getVictimizer() {
		return victimizer;
	}
	
	public String getReason() {
		return reason;
	}

}
