package cyber.playerrealms.listeners;

import cyber.playerrealms.Main;
import cyber.playerrealms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemListener implements Listener {

    public PlayerDropItemListener(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        Player p = e.getPlayer();

        if (!p.getWorld().getName().equals(Utils.getString("lobby.world"))) return;
        if (Utils.getString("lobby.protection.drop").equals("true")) {
            p.sendMessage(Utils.getString("messages.lobbyprotection.drop"));
            e.setCancelled(true);
        }
    }
}