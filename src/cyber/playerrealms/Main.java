package cyber.playerrealms;

import cyber.playerrealms.commands.CommandManager;
import cyber.playerrealms.commands.RealmsCommand;
import cyber.playerrealms.listeners.*;
import cyber.playerrealms.ui.*;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main plugin;

    public void onEnable() {
        plugin = this;

        if (!getDataFolder().exists()) getDataFolder().mkdir();
        saveDefaultConfig();

        // Commands
        this.getCommand("realms").setExecutor(new RealmsCommand());
        this.getCommand("rc").setExecutor(new CommandManager());

        // Listeners
        new InventoryClickListener(this);
        new PlayerChangedWorldListener(this);
        new PlayerChatListener(this);
        new MenuClickListener(this);

        // Main UIs
        RealmSelectorUI.Initialize();
        RealmOPUI.Initialize();
        RealmDEOPUI.Initialize();
        RealmCreatorUI.Initialize();

        // Manage UIs
        RealmManageUI.Initialize();

    }

    public static Main getInstance() {
        return plugin;
    }
}