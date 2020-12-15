/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package io.github.xenopyax.xenoapi.api;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;
 
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin; 
 
// TODO: Auto-generated Javadoc
/**
 * The Class Config.
 */
public class Config {
	
    /** The comments. */
    private int comments;
    
    /** The manager. */
    private ConfigManager manager;
 
    /** The file. */
    private File file;
    
    /** The config. */
    private FileConfiguration config;
 
    /**
     * Instantiates a new config.
     *
     * @param configStream the config stream
     * @param configFile the config file
     * @param comments the comments
     * @param plugin the plugin
     */
    public Config(InputStream configStream, File configFile, int comments, Plugin plugin) {
        this.comments = comments;
        this.manager = new ConfigManager(plugin);
 
        this.file = configFile;
        this.config = YamlConfiguration.loadConfiguration(new InputStreamReader(configStream));
    }
 
    /**
     * Gets the.
     *
     * @param path the path
     * @return the object
     */
    public Object get(String path) {
        return this.config.get(path);
    }
 
    /**
     * Gets the.
     *
     * @param path the path
     * @param def the def
     * @return the object
     */
    public Object get(String path, Object def) {
        return this.config.get(path, def);
    }
 
    /**
     * Gets the string.
     *
     * @param path the path
     * @return the string
     */
    public String getString(String path) {
        return this.config.getString(path);
    }
 
    /**
     * Gets the string.
     *
     * @param path the path
     * @param def the def
     * @return the string
     */
    public String getString(String path, String def) {
        return this.config.getString(path, def);
    }
 
    /**
     * Gets the int.
     *
     * @param path the path
     * @return the int
     */
    public int getInt(String path) {
        return this.config.getInt(path);
    }
 
    /**
     * Gets the int.
     *
     * @param path the path
     * @param def the def
     * @return the int
     */
    public int getInt(String path, int def) {
        return this.config.getInt(path, def);
    }
    
    /**
     * Gets the Long.
     *
     * @param path the path
     * @return the Long
     */
    public Long getLong(String path) {
        return this.config.getLong(path);
    }
 
    /**
     * Gets the Long.
     *
     * @param path the path
     * @param def the def
     * @return the Long
     */
    public Long getLong(String path, Long def) {
        return this.config.getLong(path, def);
    }
    
    /**
     * Gets the boolean.
     *
     * @param path the path
     * @return the boolean
     */
    public boolean getBoolean(String path) {
        return this.config.getBoolean(path);
    }
 
    /**
     * Gets the boolean.
     *
     * @param path the path
     * @param def the def
     * @return the boolean
     */
    public boolean getBoolean(String path, boolean def) {
        return this.config.getBoolean(path, def);
    }
 
    /**
     * Creates the section.
     *
     * @param path the path
     */
    public void createSection(String path) {
        this.config.createSection(path);
    }
 
    /**
     * Gets the configuration section.
     *
     * @param path the path
     * @return the configuration section
     */
    public ConfigurationSection getConfigurationSection(String path) {
        return this.config.getConfigurationSection(path);
    }
 
    /**
     * Gets the double.
     *
     * @param path the path
     * @return the double
     */
    public double getDouble(String path) {
        return this.config.getDouble(path);
    }
 
    /**
     * Gets the double.
     *
     * @param path the path
     * @param def the def
     * @return the double
     */
    public double getDouble(String path, double def) {
        return this.config.getDouble(path, def);
    }
 
    /**
     * Gets the list.
     *
     * @param path the path
     * @return the list
     */
    public List<?> getList(String path) {
        return this.config.getList(path);
    }
 
    /**
     * Gets the list.
     *
     * @param path the path
     * @param def the def
     * @return the list
     */
    public List<?> getList(String path, List<?> def) {
        return this.config.getList(path, def);
    }
 
    /**
     * Contains.
     *
     * @param path the path
     * @return true, if successful
     */
    public boolean contains(String path) {
        return this.config.contains(path);
    }
 
    /**
     * Removes the key.
     *
     * @param path the path
     */
    public void removeKey(String path) {
        this.config.set(path, null);
    }
 
    /**
     * Sets the.
     *
     * @param path the path
     * @param value the value
     */
    public void set(String path, Object value) {
        this.config.set(path, value);
    }
 
    /**
     * Sets the.
     *
     * @param path the path
     * @param value the value
     * @param comment the comment
     */
    public void set(String path, Object value, String comment) {
        if(!this.config.contains(path)) {
            this.config.set(manager.getPluginName() + "_COMMENT_" + comments, " " + comment);
            comments++;
        }
 
        this.config.set(path, value);
 
    }
 
    /**
     * Sets the.
     *
     * @param path the path
     * @param value the value
     * @param comment the comment
     */
    public void set(String path, Object value, String[] comment) {
 
        for(String comm : comment) {
 
            if(!this.config.contains(path)) {
                this.config.set(manager.getPluginName() + "_COMMENT_" + comments, " " + comm);
                comments++;
            }
 
        }
 
        this.config.set(path, value);
 
    }
 
    /**
     * Sets the header.
     *
     * @param header the new header
     */
    public void setHeader(String[] header) {
        manager.setHeader(this.file, header);
        this.comments = header.length + 2;
        this.reloadConfig();
    }
 
    /**
     * Reload config.
     */
    public void reloadConfig() {
        this.config = YamlConfiguration.loadConfiguration(new InputStreamReader(manager.getConfigContent(file)));
    }
 
    /**
     * Save config.
     */
    public void saveConfig() {
        String config = this.config.saveToString();
        manager.saveConfig(config, this.file);
 
    }
 
    /**
     * Gets the keys.
     *
     * @return the keys
     */
    public Set<String> getKeys() {
        return this.config.getKeys(false);
    }
 
}