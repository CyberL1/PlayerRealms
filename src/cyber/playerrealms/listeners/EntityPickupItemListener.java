package cyber.playerrealms.listeners;

import cyber.playerrealms.Main;
import cyber.playerrealms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class EntityPickupItemListener implements Listener {

    public EntityPickupItemListener(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityPickupItem(EntityPickupItemEvent e) {
        Player p = (Player) e.getEntity();

        if (!p.getWorld().getName().equals(Utils.getString("lobby.world"))) return;
        if (Utils.getString("lobby.protection.pickup").equals("true")) {
            e.setCancelled(true);
        }
    }
}