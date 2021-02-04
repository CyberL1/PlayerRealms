package cyber.playerrealms.ui;

import cyber.playerrealms.Main;
import cyber.playerrealms.menu.PlayerMenuUtility;
import cyber.playerrealms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class RealmSelectorUI {

    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 54;

    public static void Initialize() {
        inventory_name = Utils.colorize(Main.getInstance().getConfig().getString("items.realms.selector"));

        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory GUI(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

        if (!new File("realm-" + p.getName()).exists()) {
            Utils.createItem(inv, "DARK_OAK_DOOR", 1, 21, Utils.getString("items.realms.create"));
            inv.setItem(24, null);
        } else {
            Utils.createItem(inv, "DARK_OAK_DOOR", 1, 21, Utils.getString("items.realms.go"));
            Utils.createItem(inv, "REDSTONE_BLOCK", 1, 24, Utils.getString("items.realms.manage.item"));
        }

        Utils.createItem(inv, "ACACIA_DOOR", 1, 23, Utils.getString("items.realms.visit"));

        toReturn.setContents(inv.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) throws IOException {
        if (clicked.getItemMeta().getDisplayName().equals(Main.getInstance().getConfig().getString("items.realms.create"))) {
            p.closeInventory();
            Utils.createRealm(p);
        } else if (clicked.getItemMeta().getDisplayName().equals(Main.getInstance().getConfig().getString("items.realms.go"))) {
            Utils.gotoRealm(p, p);
        } else if (clicked.getItemMeta().getDisplayName().equals(Main.getInstance().getConfig().getString("items.realms.visit"))) {
            new RealmVisitUI(PlayerMenuUtility.getPlayerMenuUtility(p)).open();
        } else if (clicked.getItemMeta().getDisplayName().equals(Utils.getString("items.realms.manage.item"))) {
            p.openInventory(RealmManageUI.GUI(p));
        }
    }
}