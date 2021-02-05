package cyber.playerrealms.listeners;

import cyber.playerrealms.Main;
import cyber.playerrealms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    public BlockBreakListener(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockDestroy(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (!p.getWorld().getName().equals(Utils.getString("lobby.world"))) return;
        if (Utils.getString("lobby.protection.build").equals("true")) {
            p.sendMessage(Utils.getString("messages.lobbyprotection.build"));
            e.setCancelled(true);
        }
    }
}