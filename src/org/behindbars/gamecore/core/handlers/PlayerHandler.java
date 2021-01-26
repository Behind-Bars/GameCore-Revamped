/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.behindbars.gamecore.Main;
import org.behindbars.gamecore.core.data.BanInfo;
import org.behindbars.gamecore.core.data.MuteInfo;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import io.github.xenopyax.xenoapi.api.Config;
import net.md_5.bungee.api.ChatColor;

public class PlayerHandler {

	private Date now;
	private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private Config config;
	private Player player;
	
	public PlayerHandler(Player player) {
		config = Main.getXenoAPI().getConfigManager().getNewConfig("/PlayerData/" + player.getUniqueId().toString() + ".config");
		this.player = player;
	}
	
	public void sync(UUID uuid) {
		player = Bukkit.getPlayer(uuid);
	}
	
	// TODO: BAN
	
	public void banPlayer(String reason, long time, Player victimizer) {
		BanInfo info = getLastBanInfo();
		if(info != null) {
			if(info.getBannedTo() > System.currentTimeMillis()) {
				victimizer.sendMessage("§4Already banned!");
				return;
			}else {
				saveBanToHistory(info);
			}
		}
		Connection con = Main.getDB().getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO bans (BanFrom, BanTo, Reason, Victimizer, Victim) VALUES (?, ?, ?, ?, ?);");
			pstmt.setLong(1, System.currentTimeMillis());
			pstmt.setLong(2, time);
			pstmt.setString(3, reason);
			pstmt.setString(4, victimizer.getUniqueId().toString());
			pstmt.setString(5, player.getUniqueId().toString());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void saveBanToHistory(BanInfo info) {
		Connection con = Main.getDB().getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO ban_history (BanFrom, BanTo, Reason, Victimizer, Victim) VALUES (?, ?, ?, ?, ?);");
			pstmt.setLong(1, info.getBannedFrom());
			pstmt.setLong(2, info.getBannedTo());
			pstmt.setString(3, info.getReason());
			pstmt.setString(4, info.getVictimizer().toString());
			pstmt.setString(5, info.getVictim().toString());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deleteLastBan(info.getVictim());
	}

	public static void deleteLastBan(UUID victim) {
		Connection con = Main.getDB().getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM bans WHERE Victim=?");
			pstmt.setString(1, victim.toString());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public BanInfo getLastBanInfo() {
		Connection con = Main.getDB().getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM bans WHERE Victim=?;");
			pstmt.setString(1, player.getUniqueId().toString());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String reason = rs.getString("Reason");
				UUID victim = UUID.fromString(rs.getString("Victim"));
				UUID victimizer = UUID.fromString(rs.getString("Victimizer"));
				long bannedFrom = rs.getLong("BanFrom");
				long bannedTo = rs.getLong("BanTo");
				return new BanInfo(bannedFrom, bannedTo, victim, victimizer, reason);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean isBanned(UUID player) {
		Connection con = Main.getDB().getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement("SELECT BanTo FROM bans WHERE Victim=?;");
			pstmt.setString(1, player.toString());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				long bannedTo = rs.getLong("BanTo");
				return bannedTo > System.currentTimeMillis() ? true : false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isBanned() {
		Connection con = Main.getDB().getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement("SELECT BanTo FROM bans WHERE Victim=?;");
			pstmt.setString(1, player.getUniqueId().toString());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				long bannedTo = rs.getLong("BanTo");
				return bannedTo > System.currentTimeMillis() ? true : false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// BAN END
	//TODO: MUTE
	
	public void mutePlayer(String reason, long time, Player victimizer) {
		MuteInfo info = getMuteInfo();
		if(info != null) {
			if(info.getMutedTo() > System.currentTimeMillis()) {
				victimizer.sendMessage("§4Already muted!");
				return;
			}else {
				saveMuteToHistory(info);
			}
		}
		Connection con = Main.getDB().getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO mutes (MutedFrom, MutedTo, Reason, Victimizer, Victim) VALUES (?, ?, ?, ?, ?);");
			pstmt.setLong(1, System.currentTimeMillis());
			pstmt.setLong(2, time);
			pstmt.setString(3, reason);
			pstmt.setString(4, victimizer.getUniqueId().toString());
			pstmt.setString(5, player.getUniqueId().toString());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void saveMuteToHistory(MuteInfo info) {
		Connection con = Main.getDB().getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO mute_history (MutedFrom, MutedTo, Reason, Victimizer, Victim) VALUES (?, ?, ?, ?, ?);");
			pstmt.setLong(1, info.getMutedFrom());
			pstmt.setLong(2, info.getMutedTo());
			pstmt.setString(3, info.getReason());
			pstmt.setString(4, info.getVictimizer().toString());
			pstmt.setString(5, info.getVictim().toString());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deleteLastBan(info.getVictim());
	}

	public static void deleteMute(UUID victim) {
		Connection con = Main.getDB().getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM mutes WHERE Victim=?");
			pstmt.setString(1, victim.toString());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public MuteInfo getMuteInfo() {
		Connection con = Main.getDB().getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM mutes WHERE Victim=?;");
			pstmt.setString(1, player.getUniqueId().toString());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String reason = rs.getString("Reason");
				UUID victim = UUID.fromString(rs.getString("Victim"));
				UUID victimizer = UUID.fromString(rs.getString("Victimizer"));
				long bannedFrom = rs.getLong("MutedFrom");
				long bannedTo = rs.getLong("MutedTo");
				return new MuteInfo(bannedFrom, bannedTo, victim, victimizer, reason);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean isMuted(UUID player) {
		Connection con = Main.getDB().getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement("SELECT MutedTo FROM mutes WHERE Victim=?;");
			pstmt.setString(1, player.toString());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				long bannedTo = rs.getLong("MutedTo");
				return bannedTo > System.currentTimeMillis() ? true : false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isMuted() {
		Connection con = Main.getDB().getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement("SELECT MutedTo FROM mutes WHERE Victim=?;");
			pstmt.setString(1, player.getUniqueId().toString());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				long bannedTo = rs.getLong("MutedTo");
				return bannedTo > System.currentTimeMillis() ? true : false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// MUTE END
	
	public boolean isSetup() {
		return config.contains("General");
	}

	public void setupPlayer() {
		now = new Date();
		config.set("General.Name", player.getName());
		config.set("General.firstJoin", format.format(now));
		config.set("General.ipAddress", player.getAddress().getAddress().getHostAddress());
		config.set("General.Rank", Integer.valueOf(1));
		config.set("General.jailTime", Integer.valueOf(0));
		config.set("General.isInJail", Integer.valueOf(0));
		config.set("General.Money", Integer.valueOf(0));
		config.set("General.Vanished", Integer.valueOf(0));
		config.set("General.Token", Integer.valueOf(0));
		config.set("General.donatePoint", Integer.valueOf(0));
		config.set("General.lastMessaged", "none");
		config.set("General.Referral", Integer.valueOf(0));
		config.set("General.hasReferred", Integer.valueOf(0));
		config.set("General.ScoreBoard", Integer.valueOf(0));
		config.set("General.Bounty", Integer.valueOf(0));
		config.set("General.acceptRules", Integer.valueOf(0));
		config.set("General.Filter", Integer.valueOf(0));
		config.set("General.Seen", Integer.valueOf(0));


		config.set("Punish.Mute", "");
		config.set("Punish.muteReason", "");

		config.set("Killstreak.Kills", Integer.valueOf(0));
		config.set("Killstreak.Deaths", Integer.valueOf(0));
		config.set("Killstreak.killStreak", Integer.valueOf(0));
		config.set("Killstreak.LKB", "none");
		config.set("Killstreak.combatLog", "");


		config.set("Total.blocksBrokenTotal", Integer.valueOf(0));
		config.set("Total.voteTotal", Integer.valueOf(0));
		config.set("Total.blocksPlacedTotal", Integer.valueOf(0));
		config.set("Total.entitiesKilledTotal", Integer.valueOf(0));
		config.set("Total.onlineTimeTotal", Integer.valueOf(0));

		config.set("Reward.blocksBrokenReward", Integer.valueOf(0));
		config.set("Reward.voteReward", Integer.valueOf(0));
		config.set("Reward.dailyReward", "");
		config.set("Reward.blocksPlacedReward", Integer.valueOf(0));
		config.set("Reward.playersKilledReward", Integer.valueOf(0));
		config.set("Reward.entitiesKilledReward", Integer.valueOf(0));
		config.set("Reward.easterEggReward", Integer.valueOf(0));


		config.set("Donate.donateFeed", Integer.valueOf(0));
		config.set("Donate.donateHat", Integer.valueOf(0));
		config.set("Donate.donateNickname", Integer.valueOf(0));
		config.set("Donate.Nickname", ChatColor.of("#DED7D7") + player.getName());
		config.set("Donate.donatePvpStatus", Integer.valueOf(0));
		config.set("Donate.pvpStatus", Integer.valueOf(0));
		config.set("Donate.donateBail", Integer.valueOf(0));
		config.set("Donate.donateNameColor", Integer.valueOf(0));
		config.set("Donate.Spawn", Integer.valueOf(0));
		config.set("Donate.Stats", Integer.valueOf(0));
		config.set("Donate.Trails", Integer.valueOf(0));
		config.set("Donate.discoArmor", Integer.valueOf(0));
		config.set("Donate.discoArmorStatus", Integer.valueOf(0));
		config.set("Donate.Trash", Integer.valueOf(0));

		config.set("Kit.kitTools", "");

		config.set("Parkour.inParkour", "");
		config.set("Parkour.parkour1", "");
		config.set("Parkour.parkour2", "");
		config.set("Parkour.parkour3", "");
		config.set("Parkour.parkour4", "");
		config.set("Parkour.parkour5", "");
		config.set("Parkour.parkour6", "");
		config.set("Parkour.parkour7", "");
		config.set("Parkour.parkour8", "");
		config.set("Parkour.parkour9", "");

		config.set("Easteregg.easteregg1", "");
		config.set("Easteregg.easteregg2", "");
		config.set("Easteregg.easteregg3", "");
		config.set("Easteregg.easteregg4", "");
		config.set("Easteregg.easteregg5", "");
		config.set("Easteregg.easteregg6", "");
		config.set("Easteregg.easteregg7", "");
		config.set("Easteregg.easteregg8", "");
		config.set("Easteregg.easteregg9", "");

		config.set("PrisonParkour.prisonParkour1", "");
		config.set("PrisonParkour.prisonParkour2", "");
		config.set("PrisonParkour.prisonParkour3", "");
		config.set("PrisonParkour.prisonParkour4", "");
		config.set("PrisonParkour.prisonParkour5", "");
		
		config.saveConfig();
	}

	public long getPrisonParkourDate(String parkourNumber) {
		return config.getInt("PrisonParkour.prisonParkour" + parkourNumber);
	}

	public boolean setPrisonParkourDate(String parkourNumber) {
		config.set("PrisonParkour.prisonParkour" + parkourNumber, System.currentTimeMillis());
		config.saveConfig();
		return true;
	}

	public int getBountyValue() {
		return config.getInt("General.getBountyValue.");
	}

	public boolean setBountyValue(int num) {
		config.set("General.getBountyValue.", num);
		config.saveConfig();
		return true;
	}


	public int isInJail() {
		return config.getInt("General.isInJail");
	}

	public boolean setInJail(int num) {
		config.set("General.isInJail", num);
		config.saveConfig();
		return true;
	}

	public int getJailTime() {
		return config.getInt("General.jailTime");
	}

	public boolean setJailTime(int num) {
		config.set("General.jailTime", num);
		config.saveConfig();
		return true;
	}

	public int getCombatLogTime() {
		return config.getInt("Killstreak.combatLog");
	}

	public boolean setCombatLogTime(int num) {
		config.set("Killstreak.combatLog", num);
		config.saveConfig();
		return true;
	}

	public long getEastereggDate(String parkourNumber) {
		return config.getInt("Easteregg." + parkourNumber);
	}

	public boolean setEastereggDate(String parkourNumber) {
		config.set("Easteregg." + parkourNumber, System.currentTimeMillis());
		config.saveConfig();
		return true;
	}


	public long getParkourDate(String parkourNumber) {
		return config.getInt("Parkour.parkour" + parkourNumber);
	}

	public boolean setParkourDate(String parkourNumber) {
		config.set("Parkour.parkour" + parkourNumber, System.currentTimeMillis());
		config.saveConfig();
		return true;
	}

	public boolean setHome(String name) {
		if(config.contains("Home." + name)) {
			player.sendMessage("This home has already been made!");
			return true;
		}

		config.set("Home." + name.toUpperCase() + ".W", player.getLocation().getWorld().getName());
		config.set("Home." + name.toUpperCase() + ".X", player.getLocation().getBlockX());
		config.set("Home." + name.toUpperCase() + ".Y", player.getLocation().getBlockY());
		config.set("Home." + name.toUpperCase() + ".Z", player.getLocation().getBlockZ());
		config.set("Home." + name.toUpperCase() + ".Pitch", player.getLocation().getPitch());
		config.set("Home." + name.toUpperCase() + ".Yaw", player.getLocation().getYaw());
		config.saveConfig();
		
		player.sendMessage(Main.getColorHandler().warp + "Set home " + name);
		return true;
	}

	public int getFilter() {
		return config.getInt("Donate.Filter");
	}

	public boolean setFilter(int status) {
		config.set("Donate.Filter", Integer.valueOf(status));
		config.saveConfig();
		return true;
	}


	public int getDonateTrash() {
		return config.getInt("Donate.Trash");
	}

	public boolean setDonateTrash(int status) {
		config.set("Donate.Trash", Integer.valueOf(status));
		config.saveConfig();
		return true;
	}

	public int getDonateBail() {
		return config.getInt("Donate.donateBail");
	}

	public boolean setDonateBail(int status) {
		config.set("Donate.donateBail", Integer.valueOf(status));
		config.saveConfig();
		return true;
	}

	public int getAcceptRules() {
		return config.getInt("General.acceptRules");
	}

	public boolean setAcceptRules(int status) {
		config.set("General.acceptRules", Integer.valueOf(status));
		config.saveConfig();
		return true;
	}

	public int getInParkour() {
		return config.getInt("General.inParkour");
	}

	public boolean setInParkour(int status) {
		config.set("General.inParkour", Integer.valueOf(status));
		config.saveConfig();
		return true;
	}

	public boolean delHome(String name) {
		if(config.contains("Home." + name.toUpperCase())) {
			config.set("Home." + name.toUpperCase(), null);
			player.sendMessage(Main.getColorHandler().warp + "Deleted home " + name);
			config.saveConfig();
			return true;
		}

		player.sendMessage(Main.getColorHandler().warp + "Cant find home!");
		config.saveConfig();
		return true;
	}

	public boolean teleportHome(String name) {
		if(config.contains("Home." + name)) {
			player.sendMessage(Main.getColorHandler().warp + "Warping to " + name + "!");

			World w = Bukkit.getServer().getWorld(config.getString("Home." + name + ".W"));
			int x = config.getInt("Home." + name + ".X");
			int y = config.getInt("Home." + name + ".Y");
			int z = config.getInt("Home." + name + ".Z");
			float pitch = (float) config.getDouble("Warps." + name + ".Pitch");
			float yaw = (float) config.getInt("Warps." + name + ".Yaw");

			Location location = new Location(w,x,y,z, (float) pitch, (float) yaw);
			
			player.teleport(location);
			player.getLocation().setPitch(pitch);
			player.getLocation().setYaw(yaw);
			return true;
		}

		player.sendMessage(Main.getColorHandler().warp + "Cant find home!");
		return true;
	}

	public boolean getHomeList() {
		if(!config.contains("Home")) {
			player.sendMessage("There are no homes!!");
			return true;
		}

		StringBuilder str = new StringBuilder();
		for (String string : config.getConfigurationSection("Home").getKeys(false)){
			if(str.length() > 0){
				str.append(ChatColor.GOLD + "," + ChatColor.YELLOW+ " ");
			}
			str.append(string);
		}
		player.sendMessage(Main.getColorHandler().warp + "ALL Homes:");
		player.sendMessage(ChatColor.YELLOW + str.toString());
		return true;
	}

	public long getMuteDate() {
		return config.getLong("Punish.Mute");
	}
	
	public String getMutedReason() {
		return config.getString("Punish.muteReason");
	}
	
	public boolean setMuteDate(String reason) {
		config.set("Punish.Mute", System.currentTimeMillis());
		config.set("Punish.muteReason", reason);
		config.saveConfig();
		return true;
	}
	
	public boolean removeMute() {
		config.set("Punish.Mute", null);
		config.set("Punish.muteReason", null);
		config.saveConfig();
		return true;
	}


	public long getDailyRewardDate() {
		return config.getLong("Reward.dailyReward");
	}

	public boolean setDailyRewardDate() {
		config.set("Reward.dailyReward", System.currentTimeMillis());
		config.saveConfig();
		return true;
	}


	public long getKitToolsDate() {
		return config.getLong("Kit.kitTools");
	}

	public boolean setKitToolsDate() {
		config.set("Kit.kitTools", System.currentTimeMillis());
		config.saveConfig();
		return true;
	}

	public int getDonateFeed() {
		return config.getInt("Donate.donateFeed");
	}

	public boolean setDonateFeed(int status) {
		config.set("Donate.donateFeed", Integer.valueOf(status));
		config.saveConfig();
		return true;
	}
	
	public int getDonateDiscoArmorStatus() {
		return config.getInt("Donate.discoArmorStatus");
	}

	public boolean setDonateDiscoArmorStatus(int status) {
		config.set("Donate.discoArmorStatus", Integer.valueOf(status));
		config.saveConfig();
		return true;
	}

	public int getDonateDiscoArmor() {
		return config.getInt("Donate.discoArmor");
	}

	public boolean setDonateDiscoArmor(int status) {
		config.set("Donate.discoArmor", Integer.valueOf(status));
		config.saveConfig();
		return true;
	}

	public int getDonateStats() {
		return config.getInt("Donate.Stats");
	}

	public boolean setDonateStats(int status) {
		config.set("Donate.Stats", Integer.valueOf(status));
		config.saveConfig();
		return true;
	}

	public int getDonateTrails() {
		return config.getInt("Donate.Trails");
	}

	public boolean setDonateTrails(int status) {		
		config.set("Donate.Trails", Integer.valueOf(status));
		config.saveConfig();
		return true;
	}

	public int getDonateSpawn() {
		return config.getInt("Donate.Spawn");
	}

	public boolean setDonateSpawn(int status) {
		config.set("Donate.Spawn", Integer.valueOf(status));
		config.saveConfig();
		return true;
	}

	public int isVanished() {		
		return config.getInt("General.Vanished");
	}

	public boolean setVanished(int status) {		
		config.set("General.Vanished", Integer.valueOf(status));
		config.saveConfig();
		return true;
	}

	public int getVoteReward() {		
		return config.getInt("Reward.voteReward");
	}

	public boolean setVoteReward(int status) {		
		config.set("Reward.voteReward", Integer.valueOf(status));
		config.saveConfig();
		return true;
	}

	public int getDonateHat() {		
		return config.getInt("Donate.donateHat");
	}

	public boolean setDonateHat(int status) {		
		config.set("Donate.donateHat", Integer.valueOf(status));
		config.saveConfig();
		return true;
	}

	public boolean setEntitiesKilledReward(int status) {		
		config.set("Reward.entitiesKilledReward", Integer.valueOf(status));
		config.saveConfig();
		return true;
	}

	public int getEasterEggReward() {
		return config.getInt("Reward.easterEggReward");
	}
	public boolean setEasterEggReward(int status) {
		config.set("Reward.easterEggReward", Integer.valueOf(status));
		config.saveConfig();
		return true;
	}

	public int getBlocksBroken() {
		return config.getInt("Reward.blocksBrokenReward");
	}
	public boolean setBlocksBroken(int status) {
		config.set("Reward.blocksBrokenReward", Integer.valueOf(status));
		config.saveConfig();
		return true;
	}
	public int getBlocksPlaced() {
		return config.getInt("Reward.blocksPlacedReward");
	}
	public boolean setBlocksPlaced(int status) {
		config.set("Reward.blocksPlacedReward", Integer.valueOf(status));
		config.saveConfig();
		return true;
	}


	public int getEntitiesKilledReward() {
		return config.getInt("Reward.entitiesKilledReward");
	}


	public boolean setPlayersKilledReward(int status) {
		config.set("Reward.playersKilledReward", Integer.valueOf(status));
		config.saveConfig();
		return true;
	}

	public int getPlayersKilledReward() {
		return config.getInt("Reward.playersKilledReward");
	}

	public int getDonateNickname() {
		return config.getInt("Donate.donateNickname");
	}

	public boolean setDonateNickname(int status) {
		config.set("Donate.donateNickname", Integer.valueOf(status));
		config.saveConfig();
		return true;
	}

	public int getDonateNameColor() {
		return config.getInt("Donate.donateNameColor");
	}

	public boolean setDonateNameColor(int status) {
		config.set("Donate.donateNameColor", Integer.valueOf(status));
		config.saveConfig();
		return true;
	}

	public int getDonatePvpStatus() {
		return config.getInt("Donate.donatePvpStatus");
	}

	public boolean setDondatePvpStatus(int status) {
		config.set("Donate.donatePvpStatus", Integer.valueOf(status));
		config.saveConfig();
		return true;
	}

	public int getPvpStatus() {
		return config.getInt("Donate.pvpStatus");
	}

	public boolean setPvpStatus(int status) {
		config.set("Donate.pvpStatus", Integer.valueOf(status));
		config.saveConfig();
		return true;
	}


	public boolean resetJailTime() {
		config.set("General.jailTime", "-1");
		config.saveConfig();
		return true;
	}

	public int getRank() {
		return config.getInt("General.Rank");
	}

	public void setRank(int rank) {
		config.set("General.Rank", Integer.valueOf(rank));
		config.saveConfig();
		
		Bukkit.getServer().broadcastMessage(Main.getColorHandler().main + "Rank: "+ player.getName() + "'s rank has been updated to " + rankToString());
		player.setCustomName(rankToString() + getNickname());
		player.setPlayerListName(rankToString() + getNickname());
	}

	public int getReferrals() {
		return config.getInt("General.Referral");
	}

	public boolean setReferrals(int refer) {
		config.set("General.Referral", Integer.valueOf(refer));
		config.saveConfig();
		return true;
	}

	public int getHasReferred() {
		return config.getInt("General.hasReferred");
	}

	public boolean setHasReferred(int refer) {
		config.set("General.hasReferred", Integer.valueOf(refer));
		config.saveConfig();
		return true;
	}

	public int getScoreboardToggle() {
		
		
		return config.getInt("General.Scoreboard");
	}

	public boolean setScoreboardToggle(int toggle) {
		
		
		config.set("General.Scoreboard", Integer.valueOf(toggle));
		config.saveConfig();
		return true;
	}
	
	public int getMoney() {
		return config.getInt("General.Money");
	}

	public String rankToString() {
		if(getRank() == 10) {
			return (ChatColor.of("#F5002D") + "Admin " + ChatColor.WHITE);
		}
		if(getRank() == 9) {
			return (ChatColor.of("#FC9B00") + "Mod " + ChatColor.WHITE);
		}
		if(getRank() == 8) {
			return (ChatColor.of("#FC9B00") + "Jr. Mod " + ChatColor.WHITE);
		}
		if(getRank() == 7) {
			return (ChatColor.of("#5E95F0") + "Guard " + ChatColor.WHITE);
		}
		if(getRank() == 6) {
			return (ChatColor.of("#5E95F0") + "Jr. Guard " + ChatColor.WHITE);
		}
		if(getRank() == 5) {
			return (ChatColor.of("#FC58F2") + "Free " + ChatColor.WHITE);
		}
		if(getRank() == 4) {
			return (ChatColor.of("#8A0F82") + "Elite " + ChatColor.WHITE);
		}
		if(getRank() == 3) {
			return (ChatColor.of("#0EA2EE") + "Alpha " + ChatColor.WHITE);
		}
		if(getRank() == 2) {
			return (ChatColor.of("#338D4C") + "Bravo " + ChatColor.WHITE);
		}
		if(getRank() == 1) {
			return (ChatColor.of("#EEEA0E") + "Charlie " + ChatColor.WHITE);
		}
		return String.valueOf(getRank());
	}

	public String rankToColorString() {
		if(getRank() == 10) {
			return (ChatColor.of("#F5002D").toString());
		}
		if(getRank() == 9) {
			return (ChatColor.of("#FC9B00").toString());
		}
		if(getRank() == 8) {
			return (ChatColor.of("#FC9B00").toString());
		}
		if(getRank() == 7) {
			return (ChatColor.of("#5E95F0").toString());
		}
		if(getRank() == 6) {
			return (ChatColor.of("#5E95F0").toString());
		}
		if(getRank() <=5 ) {
			return (ChatColor.of("#DED7D7").toString());
		}
		return String.valueOf(getMoney());
	}

	public boolean addMoney(int amount) {
		int currentMoney = getMoney();
		currentMoney += amount;
		config.set("General.Money", Integer.valueOf(currentMoney));
		player.sendMessage(Main.getColorHandler().main + "Balance: "  + Main.getColorHandler().message + String.valueOf(amount) + " money have been added to your account!");
		config.saveConfig();
		return true;
	}

	public boolean removeMoney(int amount) {
		int currentMoney = getMoney();
		currentMoney -= amount;
		config.set("General.Money",  Integer.valueOf(currentMoney));
		player.sendMessage(Main.getColorHandler().main + "Balance: "  + Main.getColorHandler().message + String.valueOf(amount) + " money have been removed from your account!");
		config.saveConfig();
		return true;
	}

	public int getKills() {
		return config.getInt("Killstreak.Kills");
	}

	public int getDeaths() {
		return config.getInt("Killstreak.Deaths");
	}

	public int getKillStreak() {
		return config.getInt("Killstreak.killStreak");
	}

	public boolean resetKillStreak(int rank) {
		config.set("Killstreak.killStreak", Integer.valueOf(rank));
		config.saveConfig();
		return true;
	}

	public boolean addKill(int amount) {
		int currentMoney = getKills();
		currentMoney += amount;
		config.set("Killstreak.Kills", Integer.valueOf(currentMoney));
		config.saveConfig();
		return true;
	}

	public boolean addDeath(int amount) {
		int currentMoney = getDeaths();
		currentMoney += amount;
		config.set("Killstreak.Deaths", Integer.valueOf(currentMoney));
		config.saveConfig();
		return true;
	}

	public boolean addToKillStreak(int amount) {
		int currentMoney = getKillStreak();
		currentMoney += amount;
		config.set("Killstreak.killStreak", Integer.valueOf(currentMoney));
		config.saveConfig();
		return true;
	}

	public boolean setLastKilledByPlayer(String name) {
		config.set("Killstreak.LKB", name);
		config.saveConfig();
		return true;
	}

	public String getLKBP() {
		return config.getString("Killstreak.LKB");
	}

	public boolean setLastMessaged(String name) {
		config.set("General.lastMessaged", name);
		config.saveConfig();
		return true;
	}

	public String getLastMessaged() {
		return config.getString("General.lastMessaged");
	}

	public boolean setNickname(String name) {
		config.set("Donate.Nickname", name);
		config.saveConfig();
		return true;
	}

	public String getNickname() {
		return config.getString("Donate.Nickname");
	}

	public boolean setSeen(String name) {
		config.set("General.Seen", name);
		config.saveConfig();
		return true;
	}

	public String getSeen() {
		return config.getString("General.Seen");
	}

	public int getToken() {
		return config.getInt("General.Token");
	}

	public boolean addToken(int amount) {
		int currentMoney = getToken();
		currentMoney += amount;
		config.set("General.Token",  Integer.valueOf(currentMoney));
		player.sendMessage(Main.getColorHandler().main + "Token: "  + Main.getColorHandler().message + String.valueOf(amount) + " tokens have been added to your account!");
		config.saveConfig();
		return true;
	}


	public boolean removeToken(int amount) {
		int currentMoney = getToken();
		currentMoney -= amount;
		config.set("General.Token", Integer.valueOf(currentMoney));
		player.sendMessage(Main.getColorHandler().main + "Token: "  + Main.getColorHandler().message + String.valueOf(amount) + " tokens have been removed from your account!");
		config.saveConfig();
		return true;
	}

	public int getDonationPoints() {
		return config.getInt("General.donatePoint");
	}

	public boolean addDonationPoints(int amount) {
		int currentMoney = getDonationPoints();
		currentMoney += amount;
		config.set("General.donatePoint",  Integer.valueOf(currentMoney));
		player.sendMessage(Main.getColorHandler().donation + String.valueOf(amount) + " donation points have been added to your account!");
		config.saveConfig();
		return true;
	}

	public boolean removeDonationPoints(int amount) {
		int currentMoney = getDonationPoints();
		currentMoney -= amount;
		config.set("General.donatePoint", Integer.valueOf(currentMoney));
		player.sendMessage(Main.getColorHandler().donation + String.valueOf(amount) + " donation points have been removed from your account!");
		config.saveConfig();
		return true;
	}

}
