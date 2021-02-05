package cyber.playerrealms.ui;

import cyber.playerrealms.menu.Menu;
import cyber.playerrealms.menu.PlayerMenuUtility;
import cyber.playerrealms.utils.RealmVisibility;
import cyber.playerrealms.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class RealmManageUI extends Menu {

    public RealmManageUI(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return Utils.getString("guis.realms.manage");
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws IOException {
        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

        if (item.getItemMeta().getDisplayName().equals(Utils.getString("items.close"))) {
            p.closeInventory();
        } else if (item.getItemMeta().getDisplayName().equals(Utils.getString("items.realms.manage.delete.title"))) {
            Utils.deleteRealm(p, false);
        } else if (item.getItemMeta().getDisplayName().equals((Utils.getString("items.realms.manage.visibility.title")))) {
            p.closeInventory();
            if (Utils.getRealmVisibility(p) == RealmVisibility.VISIBLE) {
                Utils.setRealmVisibility(p, RealmVisibility.NOTVISIBLE);
            } else {
                Utils.setRealmVisibility(p, RealmVisibility.VISIBLE);
            }
        }
    }

    @Override
    public void setMenuItems() {
        Player p = playerMenuUtility.getOwner();

        inventory.setItem(0, makeItem("OAK_SIGN", Utils.getString("items.realms.manage.visibility.title"), Utils.getString("items.realms.manage.visibility.current").replaceAll("%visibility%", String.valueOf(Utils.getRealmVisibility(p)))));
        inventory.setItem(1, makeItem("TNT", Utils.getString("items.realms.manage.delete.title"), Utils.getString("items.realms.manage.delete.lore")));
        inventory.setItem(8, makeItem("BARRIER", Utils.getString("items.close")));

    }
}