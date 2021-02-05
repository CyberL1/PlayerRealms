package cyber.playerrealms.listeners;

import cyber.playerrealms.Main;
import cyber.playerrealms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerInteractListener implements Listener {

    public PlayerInteractListener(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInteract(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        if (!Utils.getString("lobby.protection.interact").equals("true")) {
            p.sendMessage(Utils.getString("messages.lobbyprotection.interact"));
            e.setCancelled(true);
        }
    }
}