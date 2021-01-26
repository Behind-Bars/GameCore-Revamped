/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.data;

import java.util.UUID;

public class MuteInfo {
	
	private long mutedFrom, mutedTo;
	private UUID victim, victimizer;
	private String reason;
	
	public MuteInfo(long mutedFrom, long mutedTo, UUID victim, UUID victimizer, String reason) {
		super();
		this.mutedFrom = mutedFrom;
		this.mutedTo = mutedTo;
		this.victim = victim;
		this.victimizer = victimizer;
		this.reason = reason;
	}
	
	public long getMutedFrom() {
		return mutedFrom;
	}
	
	public long getMutedTo() {
		return mutedTo;
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
