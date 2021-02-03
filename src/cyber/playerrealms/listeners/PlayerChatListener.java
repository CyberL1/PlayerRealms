package cyber.playerrealms.listeners;

import cyber.playerrealms.Main;
import cyber.playerrealms.utils.PlayerPermission;
import cyber.playerrealms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    public PlayerChatListener(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String m = e.getMessage();
        if (!p.getWorld().getName().startsWith("realm-")) return;

        if (Utils.getPlayerPermission(p) == PlayerPermission.DEOP) {
            e.setFormat(Utils.getString("messages.realms.chat.deop").replaceAll("%player%", p.getName()).replaceAll("%msg%", m));
        } else if (Utils.getPlayerPermission(p) == PlayerPermission.OP) {
            e.setFormat(Utils.getString("messages.realms.chat.op").replaceAll("%player%", p.getName()).replaceAll("%msg%", m));
        } else if (Utils.getPlayerPermission(p) == PlayerPermission.CREATOR) {
            e.setFormat(Utils.getString("messages.realms.chat.creator").replaceAll("%player%", p.getName()).replaceAll("%msg%", m));
        }
    }
}