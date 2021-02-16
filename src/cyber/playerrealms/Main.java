package cyber.playerrealms;

import cyber.playerrealms.commands.CommandManager;
import cyber.playerrealms.commands.PraCommand;
import cyber.playerrealms.commands.RealmsCommand;
import cyber.playerrealms.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main plugin;

    public void onEnable() {
        plugin = this;

        if (!getDataFolder().exists()) getDataFolder().mkdir();
        saveDefaultConfig();

        // Commands
        getCommand("realms").setExecutor(new RealmsCommand());
        getCommand("rc").setExecutor(new CommandManager());
        getCommand("pra").setExecutor(new PraCommand());

        // Listeners
        new PlayerChatListener(this);
        new MenuClickListener(this);
        new EntityDamageListener(this);
        new PlayerDropItemListener(this);
        new EntityPickupItemListener(this);
        new PlayerInteractListener(this);
    }

    public static Main getInstance() {
        return plugin;
    }
}