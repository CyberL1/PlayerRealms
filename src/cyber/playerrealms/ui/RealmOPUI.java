package cyber.playerrealms.ui;

import cyber.playerrealms.Main;
import cyber.playerrealms.menu.Menu;
import cyber.playerrealms.menu.PlayerMenuUtility;
import cyber.playerrealms.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class RealmOPUI extends Menu {

    public RealmOPUI(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return Utils.getString("guis.realms.op");
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws IOException {
        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

        if (item.getItemMeta().getDisplayName().equals(Main.getInstance().getConfig().getString("items.realms.create.item"))) {
            p.closeInventory();
            Utils.createRealm(p);
        } else if (item.getItemMeta().getDisplayName().equals(Main.getInstance().getConfig().getString("items.realms.go.item"))) {
            p.closeInventory();
            Utils.gotoRealm(p, p);
        } else if (item.getItemMeta().getDisplayName().equals(Main.getInstance().getConfig().getString("items.realms.gotolobby.item"))) {
            Utils.gotoLobby(p);
        } else if (item.getItemMeta().getDisplayName().equals(Main.getInstance().getConfig().getString("items.realms.visit.item"))) {
            new RealmVisitUI(PlayerMenuUtility.getPlayerMenuUtility(p)).open();
        }
    }

    @Override
    public void setMenuItems() {
        Player p = playerMenuUtility.getOwner();

        inventory.setItem(2, makeItem(Utils.getString("items.realms.gotolobby.item"), Utils.getString("items.realms.gotolobby.name")));

        if (!new File("realm-" + p.getName()).exists()) {
            inventory.setItem(0, makeItem(Utils.getString("items.realms.create.item"), Main.getInstance().getConfig().getString("items.realms.create.name")));
        } else {
            inventory.setItem(0, makeItem(Utils.getString("items.realms.go.item"), Main.getInstance().getConfig().getString("items.realms.go.name")));
        }

        inventory.setItem(1, makeItem(Utils.getString("items.realms.visit.item"), Main.getInstance().getConfig().getString("items.realms.visit.name")));
    }
}