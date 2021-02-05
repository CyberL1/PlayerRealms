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

        if (item.getItemMeta().getDisplayName().equals(Main.getInstance().getConfig().getString("items.realms.create"))) {
            Utils.createRealm(p);
        } else if (item.getItemMeta().getDisplayName().equals(Main.getInstance().getConfig().getString("items.realms.go"))) {
            Utils.gotoRealm(p, p);
        } else if (item.getItemMeta().getDisplayName().equals(Main.getInstance().getConfig().getString("items.realms.gotolobby"))) {
            Utils.gotoLobby(p);
        } else if (item.getItemMeta().getDisplayName().equals(Main.getInstance().getConfig().getString("items.realms.visit"))) {
            new RealmVisitUI(PlayerMenuUtility.getPlayerMenuUtility(p)).open();
        }
    }

    @Override
    public void setMenuItems() {
        Player p = playerMenuUtility.getOwner();

        inventory.setItem(2, makeItem("COMPASS", Utils.getString("items.realms.gotolobby")));

        if (!new File("realm-" + p.getName()).exists()) {
            inventory.setItem(0, makeItem("DARK_OAK_DOOR", Main.getInstance().getConfig().getString("items.realms.create")));
        } else {
            inventory.setItem(0, makeItem("DARK_OAK_DOOR", Main.getInstance().getConfig().getString("items.realms.go")));
        }

        inventory.setItem(1, makeItem("ACACIA_DOOR", Main.getInstance().getConfig().getString("items.realms.visit")));
    }
}