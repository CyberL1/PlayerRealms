package cyber.playerrealms.listeners;

import cyber.playerrealms.Main;
import cyber.playerrealms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;

public class EntityPortalEnterListener implements Listener {

    public EntityPortalEnterListener(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityPortalEnter(EntityPortalEnterEvent e) {
        Entity p = e.getEntity();

        if (p instanceof Player) {
            if (p.getWorld().getName().equals(Utils.getString("lobby.world"))) return;
            if (p.getWorld().getEnvironment() == World.Environment.THE_END) {
                p.teleport(Bukkit.getWorld(Utils.getRealm("OVERWORLD", p)).getSpawnLocation());
            }
        }
    }
}