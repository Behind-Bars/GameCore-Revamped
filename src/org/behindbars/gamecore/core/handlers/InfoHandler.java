/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
 */

package org.behindbars.gamecore.core.handlers;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class InfoHandler {

	public void setupGeneral() {
		File f = new File("plugins/GameCore/" + "generalinfo.yml");
		if (!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);

		yml.addDefault("eventStatus", Integer.valueOf(0));
		yml.addDefault("eventLandfall", Integer.valueOf(0));


		yml.options().copyDefaults(true);
		try {
			yml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public long getEventStatus() {
		File f = new File("plugins/GameCore/" + "generalinfo.yml");
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		return yml.getLong("eventStatus");
	}

	public boolean setEventStatus(int status) {
		File f = new File("plugins/GameCore/" + "generalinfo.yml");
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		yml.set("eventStatus", status);
		try {
			yml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public long isLandfall() {
		File f = new File("plugins/GameCore/" + "generalinfo.yml");
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		return yml.getLong("eventLandfall");
	}

	public boolean setLandfall(int status) {
		File f = new File("plugins/GameCore/" + "generalinfo.yml");
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		yml.set("eventLandfall", status);
		try {
			yml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
