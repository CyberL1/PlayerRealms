package cyber.playerrealms.utils;

import cyber.playerrealms.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Utils {

    public static String colorize(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static ConsoleCommandSender getConsole() {
        return Bukkit.getServer().getConsoleSender();
    }

    public static String getString(String s) {
        return colorize(Main.getInstance().getConfig().getString(s).replaceAll("%prefix%", Main.getInstance().getConfig().getString(colorize("messages.prefix"))));
    }

    public static File getRealmDataFolder() {
        return new File(Main.getInstance().getDataFolder(), "realms");
    }

    public static File getRealmDataFileRaw(String s) {
        return new File(getRealmDataFolder(), s + ".yml");
    }

    public static YamlConfiguration getRealmDataFile(String s) {
        return YamlConfiguration.loadConfiguration(getRealmDataFileRaw(s));
    }

    public static String getRealm(Player p) {
        return "realm-" + p.getName();
    }

    public static void setPlayerPermission(String w, Player p, int permission) throws IOException {
        YamlConfiguration file = getRealmDataFile(w);
        file.getConfigurationSection("players").set(p.getUniqueId().toString(), permission);
        file.save(getRealmDataFileRaw(w));
    }

    public static int getPlayerPermission(Player p) {
        return getRealmDataFile(getRealm(p)).getConfigurationSection("players").getInt(p.getUniqueId().toString());
    }

    public static void gotoRealm(Player playerRealm, Player playerToTp) throws IOException {
        if (getRealmDataFile(getRealm(playerRealm)).getConfigurationSection("players").getString(playerToTp.getUniqueId().toString()) == null)
            Utils.setPlayerPermission(getRealm(playerRealm), playerToTp, PlayerPermission.DEOP);
        if (getRealmDataFile(getRealm(playerRealm)).getConfigurationSection("settings").getBoolean("closed", true))
            openRealm(playerRealm);
        Bukkit.dispatchCommand(getConsole(), "mv tp " + playerToTp.getName() + " realm-" + playerRealm.getName());
    }

    public static void gotoLobby(Player p) {
        Bukkit.dispatchCommand(getConsole(), "mv tp " + p.getName() + " " + getString("lobby.world"));
    }

    public static YamlConfiguration getAllRealms() {
        YamlConfiguration config = new YamlConfiguration();
        File[] files = getRealmDataFolder().listFiles();
        try {
            for (File file : files) {
                config.load(file);
            }
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return config;
    }

    public static int getRealmVisibility(Player p) {
        return getRealmDataFile(getRealm(p)).getConfigurationSection("settings").getInt("visible");
    }

    public static void setRealmVisibility(Player p, int visibility) throws IOException {
        YamlConfiguration file = getRealmDataFile(getRealm(p));
        file.getConfigurationSection("settings").set("visible", visibility);
        file.save(getRealmDataFileRaw(getRealm(p)));
    }

    public static void createRealm(Player p) throws IOException {
        p.sendMessage(getString("messages.realms.creation.started"));

        Bukkit.dispatchCommand(getConsole(), "mv create realm-" + p.getName() + " normal");
        YamlConfiguration file = getRealmDataFile(getRealm(p));
        file.createSection("settings");
        file.createSection("players");
        file.save(getRealmDataFileRaw(getRealm(p)));
        setRealmVisibility(p, RealmVisibility.VISIBLE);
        setPlayerPermission(getRealm(p), p, PlayerPermission.CREATOR);

        p.sendMessage(getString("messages.realms.creation.done"));
    }

    public static void closeRealm(Player p) throws IOException {
        Bukkit.dispatchCommand(getConsole(), "mv unload realm-" + p.getName());
        YamlConfiguration file = getRealmDataFile(getRealm(p));
        file.getConfigurationSection("settings").set("closed", true);
        file.save(getRealmDataFileRaw(getRealm(p)));
        p.sendMessage(getString("messages.commands.rc.close.success"));
    }

    public static void openRealm(Player p) throws IOException {
        p.sendMessage(getString("messages.realms.opening"));
        Bukkit.dispatchCommand(getConsole(), "mv load realm-" + p.getName());
        YamlConfiguration file = getRealmDataFile(getRealm(p));
        file.getConfigurationSection("settings").set("closed", false);
        file.save(getRealmDataFileRaw(getRealm(p)));
    }

    public static void deleteRealm(Player p, boolean silent) throws IOException {
        getRealmDataFileRaw(getRealm(p)).delete();
        Bukkit.dispatchCommand(getConsole(), "mv remove realm-" + p.getName());
        FileUtils.deleteDirectory(new File(getRealm(p)));
        if (!silent) p.sendMessage(getString("messages.realms.deleted"));
    }
}