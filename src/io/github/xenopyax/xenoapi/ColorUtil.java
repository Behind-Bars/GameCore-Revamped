package io.github.xenopyax.xenoapi;

import java.awt.Color;

import net.md_5.bungee.api.ChatColor;

// TODO: Auto-generated Javadoc
/**
 * The Class ColorUtil.
 */
public class ColorUtil {
    
    /** The Constant RAW_GRADIENT_HEX_REGEX. */
    private final static String RAW_GRADIENT_HEX_REGEX = "<\\$#[A-Fa-f0-9]{6}>";

	/**
	 * Insert fades.
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
	 */
	protected static String insertFades(String msg, String fromHex, String toHex, boolean bold, boolean italic, boolean underlined, boolean strikethrough, boolean magic) {
        int length = msg.length();
        Color fromRGB = Color.decode((fromHex.startsWith("#") ? fromHex : "#" + fromHex));
        Color toRGB = Color.decode((toHex.startsWith("#") ? toHex : "#" + toHex));
        double rStep = Math.abs((double) (fromRGB.getRed() - toRGB.getRed()) / length);
        double gStep = Math.abs((double) (fromRGB.getGreen() - toRGB.getGreen()) / length);
        double bStep = Math.abs((double) (fromRGB.getBlue() - toRGB.getBlue()) / length);
        if (fromRGB.getRed() > toRGB.getRed()) rStep = -rStep; //200, 100
        if (fromRGB.getGreen() > toRGB.getGreen()) gStep = -gStep; //200, 100
        if (fromRGB.getBlue() > toRGB.getBlue()) bStep = -bStep; //200, 100
        Color finalColor = new Color(fromRGB.getRGB());
        msg = msg.replaceAll(RAW_GRADIENT_HEX_REGEX, "");
        msg = msg.replace("", "<$>");
        for (int index = 0; index <= length; index++) {
            int red = (int) Math.round(finalColor.getRed() + rStep);
            int green = (int) Math.round(finalColor.getGreen() + gStep);
            int blue = (int) Math.round(finalColor.getBlue() + bStep);
            if (red > 255) red = 255; if (red < 0) red = 0;
            if (green > 255) green = 255; if (green < 0) green = 0;
            if (blue > 255) blue = 255; if (blue < 0) blue = 0;
            finalColor = new Color(red, green, blue);
            String hex = "#" + Integer.toHexString(finalColor.getRGB()).substring(2);
            String formats = "";
            if (bold) formats += ChatColor.BOLD;
            if (italic) formats += ChatColor.ITALIC;
            if (underlined) formats += ChatColor.UNDERLINE;
            if (strikethrough) formats += ChatColor.STRIKETHROUGH;
            if (magic) formats += ChatColor.MAGIC;
            msg = msg.replaceFirst("<\\$>", "" + ChatColor.of(hex) + formats);
        }
        return msg;
    }
    
}
