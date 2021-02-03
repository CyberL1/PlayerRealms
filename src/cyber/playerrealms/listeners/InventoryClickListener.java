package cyber.playerrealms.listeners;

import cyber.playerrealms.Main;
import cyber.playerrealms.ui.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.io.IOException;
import java.text.ParseException;

public class InventoryClickListener implements Listener {

    public InventoryClickListener(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) throws IOException, ParseException {
        Player p = (Player) e.getWhoClicked();
        String title = e.getView().getTitle();

        if (title.equals(RealmSelectorUI.inventory_name)) {
            if (e.getCurrentItem() != null) {
                RealmSelectorUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }
        if (title.equals(RealmDEOPUI.inventory_name)) {
            if (e.getCurrentItem() != null) {
                RealmDEOPUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }
        if (title.equals(RealmOPUI.inventory_name)) {
            if (e.getCurrentItem() != null) {
                RealmOPUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }
        if (title.equals(RealmCreatorUI.inventory_name)) {
            if (e.getCurrentItem() != null) {
                RealmCreatorUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }
        if (title.equals(RealmManageUI.inventory_name)) {
            if (e.getCurrentItem() != null) {
                RealmManageUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }
    }
}
