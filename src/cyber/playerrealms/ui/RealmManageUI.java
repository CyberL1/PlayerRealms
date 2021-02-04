package cyber.playerrealms.ui;

import cyber.playerrealms.Main;
import cyber.playerrealms.utils.RealmVisibility;
import cyber.playerrealms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class RealmManageUI {

    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 54;

    public static void Initialize() {
        inventory_name = Utils.colorize(Main.getInstance().getConfig().getString("guis.realms.manage"));

        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory GUI(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

        Utils.createItem(inv, "OAK_SIGN", 1, 21, Utils.getString("items.realms.manage.visibility.title"), Utils.getString("items.realms.manage.visibility.current").replaceAll("%visibility%", String.valueOf(Utils.getRealmVisibility(p))));
        Utils.createItem(inv, "TNT", 1, 23, Utils.getString("items.realms.manage.delete.title"), Utils.getString("items.realms.manage.delete.lore"));
        Utils.createItem(inv, "BARRIER", 1, 31, Utils.getString("items.close"));


        toReturn.setContents(inv.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) throws IOException {
        if (clicked.getItemMeta().getDisplayName().equals(Utils.getString("items.close"))) {
            p.closeInventory();
        } else if (clicked.getItemMeta().getDisplayName().equals(Utils.getString("items.realms.manage.delete.title"))) {
            p.closeInventory();
            Utils.deleteRealm(p, false);
        } else if (clicked.getItemMeta().getDisplayName().equals((Utils.getString("items.realms.manage.visibility.title")))) {
            p.closeInventory();
            if (Utils.getRealmVisibility(p) == RealmVisibility.VISIBLE) {
                Utils.setRealmVisibility(p, RealmVisibility.NOTVISIBLE);
            } else {
                Utils.setRealmVisibility(p, RealmVisibility.VISIBLE);
            }
        }
    }
}