package cyber.playerrealms.ui;

import cyber.playerrealms.menu.Menu;
import cyber.playerrealms.menu.PlayerMenuUtility;
import cyber.playerrealms.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class RealmTeleportUI extends Menu {

    public RealmTeleportUI(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return Utils.getString("guis.realms.teleport");
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.getString("items.realms.teleporter.overworld.name"))) {
            p.closeInventory();
            Utils.gotoRealm("OVERWORLD", p, p);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.getString("items.realms.teleporter.nether.name"))) {
            p.closeInventory();
            Utils.gotoRealm("NETHER", p, p);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.getString("items.realms.teleporter.end.name"))) {
            p.closeInventory();
            Utils.gotoRealm("THE_END", p, p);
        }
    }

    @Override
    public void setMenuItems() {

        inventory.setItem(0, makeItem(Utils.getString("items.realms.teleporter.overworld.item"), Utils.getString("items.realms.teleporter.overworld.name")));
        inventory.setItem(1, makeItem(Utils.getString("items.realms.teleporter.nether.item"), Utils.getString("items.realms.teleporter.nether.name")));
        inventory.setItem(2, makeItem(Utils.getString("items.realms.teleporter.end.item"), Utils.getString("items.realms.teleporter.end.name")));

    }
}