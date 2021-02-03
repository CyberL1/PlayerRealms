package cyber.playerrealms.ui;

import cyber.playerrealms.menu.PlayerMenuUtility;
import cyber.playerrealms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RealmCreatorUI {

    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 54;

    public static void Initialize() {
        inventory_name = Utils.getString("guis.realms.creator");

        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory GUI(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

        Utils.createItem(inv, "ACACIA_DOOR", 1, 21, Utils.getString("items.realms.visit"));
        Utils.createItem(inv, "COMPASS", 1, 23, Utils.getString("items.realms.gotolobby"));
        Utils.createItem(inv, "REDSTONE_BLOCK", 1, 31, Utils.getString("items.realms.manage.item"));

        toReturn.setContents(inv.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
        if (clicked.getItemMeta().getDisplayName().equals(Utils.getString("items.realms.gotolobby"))) {
            Utils.gotoLobby(p);
        } else if (clicked.getItemMeta().getDisplayName().equals(Utils.getString("items.realms.visit"))) {
            new RealmVisitUI(PlayerMenuUtility.getPlayerMenuUtility(p)).open();
        } else if (clicked.getItemMeta().getDisplayName().equals(Utils.getString("items.realms.manage.item"))) {
            p.openInventory(RealmManageUI.GUI(p));
        }
    }
}