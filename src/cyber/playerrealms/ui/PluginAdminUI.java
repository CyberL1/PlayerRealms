package cyber.playerrealms.ui;

import cyber.playerrealms.Main;
import cyber.playerrealms.menu.Menu;
import cyber.playerrealms.menu.PlayerMenuUtility;
import cyber.playerrealms.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PluginAdminUI extends Menu {

    public PluginAdminUI(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return Utils.getString("guis.realms.admin");
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.getString("items.admin.reload.name"))) {
            p.closeInventory();
            Main.getInstance().reloadConfig();
            p.sendMessage(Utils.getString("messages.logs.reload.done"));
        }
    }

    @Override
    public void setMenuItems() {

        inventory.setItem(0, makeItem(Utils.getString("items.admin.reload.item"), Utils.getString("items.admin.reload.name")));

    }
}