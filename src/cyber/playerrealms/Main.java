package cyber.playerrealms;

import cyber.playerrealms.commands.CommandManager;
import cyber.playerrealms.commands.OverworldCommand;
import cyber.playerrealms.commands.PraCommand;
import cyber.playerrealms.commands.RealmsCommand;
import cyber.playerrealms.listeners.*;
import cyber.playerrealms.utils.UpdateChecker;
import cyber.playerrealms.utils.Utils;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main plugin;

    public void onEnable() {
        plugin = this;

        if (!getDataFolder().exists()) getDataFolder().mkdir();
        saveDefaultConfig();

        new UpdateChecker(this, 89101).getVersion(ver -> {
            if (!this.getDescription().getVersion().equalsIgnoreCase(ver))
                this.getLogger().info(Utils.getString("messages.logs.updateavaliable").replaceAll("%url%", "https://www.spigotmc.org/resources/89101"));
        });

        // Commands
        getCommand("realms").setExecutor(new RealmsCommand());
        getCommand("rc").setExecutor(new CommandManager());
        getCommand("overworld").setExecutor(new OverworldCommand());
        getCommand("pra").setExecutor(new PraCommand());

        // Listeners
        new PlayerChatListener(this);
        new MenuClickListener(this);
        new EntityDamageListener(this);
        new PlayerDropItemListener(this);
        new EntityPickupItemListener(this);
        new PlayerInteractListener(this);
        new PlayerPortalListener(this);
        new EntityPortalEnterListener(this);
        new PlayerJoinListener(this);
    }

    public static Main getInstance() {
        return plugin;
    }
}