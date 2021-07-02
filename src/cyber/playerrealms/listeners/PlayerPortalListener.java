package cyber.playerrealms.listeners;

import cyber.playerrealms.Main;
import cyber.playerrealms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerPortalListener implements Listener {

    public PlayerPortalListener(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent e) {
        Player p = e.getPlayer();
        if (p.getWorld().getName().equals(Utils.getString("lobby.world"))) return;
        e.setCancelled(true);
        if (e.getCause() == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) {
            if (p.getWorld().getEnvironment() != World.Environment.NETHER) {
                if (Bukkit.getWorld(p.getWorld().getName() + "_nether") != null) {
                    Utils.gotoRealm("NETHER", p, p);
                } else {
                    p.sendMessage(Utils.getString("messages.realms.worlds_disabled.nether"));
                }
            } else {
                Utils.gotoRealm("OVERWORLD", p, p);
            }
        } else if (e.getCause() == PlayerTeleportEvent.TeleportCause.END_PORTAL) {
            if (Bukkit.getWorld(p.getWorld().getName() + "_the_end") != null) {
                Utils.gotoRealm("THE_END", p, p);
            } else {
                p.sendMessage(Utils.getString("messages.realms.worlds_disabled.the_end"));
            }
        }
    }
}