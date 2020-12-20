/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
 */

package org.behindbars.gamecore.core.handlers;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class MineHandler {

	public void setupMines() {
		File f = new File("plugins/GameCore/" +  "mines.yml");
		if (!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);

		yml.addDefault("mines", "test");
		yml.addDefault("Mines.mine1", "");
		yml.addDefault("Mines.mine2", "");
		yml.addDefault("Mines.mine3", "");
		yml.addDefault("Mines.mine4", "");


		yml.options().copyDefaults(true);
		try {
			yml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public long getMineResetTime(String parkourNumber) {
		File f = new File("plugins/GameCore/" + "mines.yml");
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		return yml.getLong("Mines.mine" + parkourNumber);
	}

	public boolean setMineResetTime(String parkourNumber) {
		File f = new File("plugins/GameCore/" + "mines.yml");
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		yml.set("Mines.mine" + parkourNumber, System.currentTimeMillis());
		try {
			yml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
