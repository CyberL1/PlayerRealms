package cyber.playerrealms.utils;

import cyber.playerrealms.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;
import org.bukkit.entity.Entity;
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

    public static String getRealm(String type, Entity p) {
        String base = "realm-" + p.getName();
        switch (type) {
            case "OVERWORLD":
                return base;
            case "NETHER":
                return base + "_nether";
            case "THE_END":
                return base + "_the_end";
        }
        return base;
    }

    public static void setPlayerPermission(String w, Player p, int permission) throws IOException {
        YamlConfiguration file = getRealmDataFile(w);
        file.getConfigurationSection("players").set(p.getUniqueId().toString(), permission);
        file.save(getRealmDataFileRaw(w));
    }

    public static int getPlayerPermission(Player p) {
        return getRealmDataFile(p.getWorld().getName().replaceAll("_nether", "").replaceAll("_the_end", "")).getConfigurationSection("players").getInt(p.getUniqueId().toString());
    }

    public static void gotoRealm(String type, Player playerRealm, Player playerToTp) {
        if (Bukkit.getWorld(getRealm("OVERWORLD", playerRealm)) == null) openRealm(playerRealm);
        playerRealm.teleport(Bukkit.getWorld(getRealm(type, playerRealm)).getSpawnLocation());
    }

    public static void gotoLobby(Player p) {
        Bukkit.dispatchCommand(getConsole(), "mv tp " + p.getName() + " " + getString("lobby.world"));
    }

    public static int getRealmVisibility(String w) {
        return getRealmDataFile(w).getConfigurationSection("settings").getInt("visible");
    }

    public static void setRealmVisibility(Player p, int visibility) throws IOException {
        YamlConfiguration file = getRealmDataFile(getRealm("OVERWORLD", p));
        file.getConfigurationSection("settings").set("visible", visibility);
        file.save(getRealmDataFileRaw(getRealm("OVERWORLD", p)));
    }

    public static void createRealm(Player p) throws IOException {
        p.sendMessage(getString("messages.realms.creation.started"));

        Bukkit.dispatchCommand(getConsole(), "mv create realm-" + p.getName() + " normal");
        Bukkit.dispatchCommand(getConsole(), "mvm set autoload false realm-" + p.getName());
        Bukkit.dispatchCommand(getConsole(), "mv create realm-" + p.getName() + "_nether nether");
        Bukkit.dispatchCommand(getConsole(), "mvm set autoload false realm-" + p.getName() + "_nether");
        Bukkit.dispatchCommand(getConsole(), "mv create realm-" + p.getName() + "_the_end end");
        Bukkit.dispatchCommand(getConsole(), "mvm set autoload false realm-" + p.getName() + "_the_end");
        YamlConfiguration file = getRealmDataFile(getRealm("OVERWORD", p));
        file.createSection("settings");
        file.createSection("players");
        file.save(getRealmDataFileRaw(getRealm("OVERWORD", p)));
        setRealmVisibility(p, RealmVisibility.VISIBLE);
        setPlayerPermission(getRealm("OVERWORD", p), p, PlayerPermission.CREATOR);

        p.sendMessage(getString("messages.realms.creation.done"));
    }

    public static void closeRealm(Player p) {
        Bukkit.dispatchCommand(getConsole(), "mv unload realm-" + p.getName());
        Bukkit.dispatchCommand(getConsole(), "mv unload realm-" + p.getName() + "_nether");
        Bukkit.dispatchCommand(getConsole(), "mv unload realm-" + p.getName() + "_the_end");
        p.sendMessage(getString("messages.commands.rc.close.success"));
    }

    public static void openRealm(Player p) {
        p.sendMessage(getString("messages.realms.opening"));
        Bukkit.dispatchCommand(getConsole(), "mv load realm-" + p.getName());
        Bukkit.dispatchCommand(getConsole(), "mv load realm-" + p.getName() + "_nether");
        Bukkit.dispatchCommand(getConsole(), "mv load realm-" + p.getName() + "_the_end");
    }

    public static void deleteRealm(Player p, boolean silent) throws IOException {
        getRealmDataFileRaw(getRealm("OVERWORD", p)).delete();
        Bukkit.dispatchCommand(getConsole(), "mv remove realm-" + p.getName());
        Bukkit.dispatchCommand(getConsole(), "mv remove realm-" + p.getName() + "_nether");
        Bukkit.dispatchCommand(getConsole(), "mv remove realm-" + p.getName() + "_the_end");
        FileUtils.deleteDirectory(new File(getRealm("OVERWORD", p)));
        FileUtils.deleteDirectory(new File(getRealm("NETHER", p)));
        FileUtils.deleteDirectory(new File(getRealm("THE_END", p)));
        if (!silent) p.sendMessage(getString("messages.realms.deleted"));
    }
}