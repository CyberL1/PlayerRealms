package cyber.playerrealms.listeners;

import cyber.playerrealms.Main;
import cyber.playerrealms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageListener implements Listener {

    public EntityDamageListener(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockByEntityDamage(EntityDamageByEntityEvent e) {
        Entity p = e.getDamager();

        if (p instanceof Player) {
            if (!p.getWorld().getName().equals(Utils.getString("lobby.world"))) return;
            if (Utils.getString("lobby.protection.damage").equals("true")) {
                p.sendMessage(Utils.getString("messages.lobbyprotection.damage"));
                e.setCancelled(true);
            }
        }
    }
}