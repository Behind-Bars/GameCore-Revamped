package io.github.xenopyax.xenoapi.api;

import org.bukkit.Bukkit;

import java.util.Locale;
import java.util.Optional;

// TODO: Auto-generated Javadoc
/**
 * Small reflection class to use CraftBukkit and NMS.
 *
 * @author MrMicky
 */
public final class FastReflection {

    /** The Constant OBC_PACKAGE. */
    public static final String OBC_PACKAGE = "org.bukkit.craftbukkit";
    
    /** The Constant NMS_PACKAGE. */
    public static final String NMS_PACKAGE = "net.minecraft.server";

    /** The Constant VERSION. */
    public static final String VERSION = Bukkit.getServer().getClass().getPackage().getName().substring(OBC_PACKAGE.length() + 1);

    /**
     * Instantiates a new fast reflection.
     */
    private FastReflection() {
        throw new UnsupportedOperationException();
    }

    /**
     * Nms class name.
     *
     * @param className the class name
     * @return the string
     */
    public static String nmsClassName(String className) {
        return NMS_PACKAGE + '.' + VERSION + '.' + className;
    }

    /**
     * Nms class.
     *
     * @param className the class name
     * @return the class
     * @throws ClassNotFoundException the class not found exception
     */
    public static Class<?> nmsClass(String className) throws ClassNotFoundException {
        return Class.forName(nmsClassName(className));
    }

    /**
     * Nms optional class.
     *
     * @param className the class name
     * @return the optional
     */
    public static Optional<Class<?>> nmsOptionalClass(String className) {
        return optionalClass(nmsClassName(className));
    }

    /**
     * Obc class name.
     *
     * @param className the class name
     * @return the string
     */
    public static String obcClassName(String className) {
        return OBC_PACKAGE + '.' + VERSION + '.' + className;
    }

    /**
     * Obc class.
     *
     * @param className the class name
     * @return the class
     * @throws ClassNotFoundException the class not found exception
     */
    public static Class<?> obcClass(String className) throws ClassNotFoundException {
        return Class.forName(obcClassName(className));
    }

    /**
     * Obc optional class.
     *
     * @param className the class name
     * @return the optional
     */
    public static Optional<Class<?>> obcOptionalClass(String className) {
        return optionalClass(obcClassName(className));
    }

    /**
     * Optional class.
     *
     * @param className the class name
     * @return the optional
     */
    public static Optional<Class<?>> optionalClass(String className) {
        try {
            return Optional.of(Class.forName(className));
        } catch (ClassNotFoundException e) {
            return Optional.empty();
        }
    }

    /**
     * Enum value of.
     *
     * @param <E> the element type
     * @param enumClass the enum class
     * @param enumName the enum name
     * @return the e
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<E>> E enumValueOf(Class<?> enumClass, String enumName) {
        return Enum.valueOf((Class<E>) enumClass, enumName.toUpperCase(Locale.ROOT));
    }
}
