package cyber.playerrealms.ui;

import cyber.playerrealms.menu.Menu;
import cyber.playerrealms.menu.PlayerMenuUtility;
import cyber.playerrealms.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class RealmSelectorUI extends Menu {

    public RealmSelectorUI(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return Utils.getString("guis.realms.selector");
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws IOException {
        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

        if (item.getItemMeta().getDisplayName().equals(Utils.getString("items.realms.create.name"))) {
            p.closeInventory();
            Utils.createRealm(p);
        } else if (item.getItemMeta().getDisplayName().equals(Utils.getString("items.realms.go.name"))) {
            p.closeInventory();
            Utils.gotoRealm("OVERWORLD", p, p);
        } else if (item.getItemMeta().getDisplayName().equals(Utils.getString("items.realms.manage.name"))) {
            new RealmManageUI(PlayerMenuUtility.getPlayerMenuUtility(p)).open();
        } else if (item.getItemMeta().getDisplayName().equals(Utils.getString("items.realms.visit.name"))) {
            new RealmVisitUI(PlayerMenuUtility.getPlayerMenuUtility(p)).open();
        }
    }

    @Override
    public void setMenuItems() {

        Player p = playerMenuUtility.getOwner();
        if (!new File("realm-" + p.getName()).exists()) {
            inventory.setItem(0, makeItem(Utils.getString("items.realms.create.item"), Utils.getString("items.realms.create.name")));
            inventory.setItem(2, null);
        } else {
            inventory.setItem(0, makeItem(Utils.getString("items.realms.go.item"), Utils.getString("items.realms.go.name")));
            inventory.setItem(2, makeItem(Utils.getString("items.realms.manage.item"), Utils.getString("items.realms.manage.name")));
        }

        inventory.setItem(1, makeItem(Utils.getString("items.realms.visit.item"), Utils.getString("items.realms.visit.name")));

    }
}