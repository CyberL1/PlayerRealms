package cyber.playerrealms.listeners;

import cyber.playerrealms.Main;
import cyber.playerrealms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    public PlayerInteractListener(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (!p.getWorld().getName().equals(Utils.getString("lobby.world"))) return;
        if (Utils.getString("lobby.protection.intecart").equals("true")) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                p.sendMessage(Utils.getString("messages.lobbyprotection.interact"));
                e.setCancelled(true);
            }
        }
    }
}