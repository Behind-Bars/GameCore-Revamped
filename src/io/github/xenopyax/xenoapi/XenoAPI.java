/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/
package io.github.xenopyax.xenoapi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import io.github.xenopyax.xenoapi.api.XenoHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class XenoAPI.
 */
public class XenoAPI extends XenoHandler {
	
	/** The version. */
	public String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	
	/**
	 * Instantiates a new xeno API.
	 *
	 * @param plugin the plugin
	 */
	public XenoAPI(Plugin plugin) {
		super(plugin); 
	}
	
	/**
	 * Gradient.
	 *
	 * @param msg the msg
	 * @param fromHex the from hex
	 * @param toHex the to hex
	 * @param bold the bold
	 * @param italic the italic
	 * @param underlined the underlined
	 * @param strikethrough the strikethrough
	 * @param magic the magic
	 * @return the string
	 * @throws Exception 
	 */
	public String gradient(String msg, String fromHex, String toHex, boolean bold, boolean italic, boolean underlined, boolean strikethrough, boolean magic) {
		System.out.println(version);
		if(version.equals("v1_16_R2") || version.equals("v1_16_R1") || version.equals("v1_16_R3")) {
			System.out.println("valid!");
			return ColorUtil.insertFades(msg, fromHex, toHex, bold, italic, underlined, strikethrough, magic);
		}
		System.out.println("invalid!");
		return msg;
    }
	
	/**
	 * Gradient.
	 *
	 * @param msg the msg
	 * @param fromHex the from hex
	 * @param toHex the to hex
	 * @return the string
	 * @throws Exception 
	 */
	public String gradient(String msg, String fromHex, String toHex) throws Exception {
		if(version.equals("v1_16_R2") || version.equals("v1_16_R1") || version.equals("v1_16_R3")) {
			return ColorUtil.insertFades(msg, fromHex, toHex, false, false, false, false, false);
		}
		return msg;
    }

}
