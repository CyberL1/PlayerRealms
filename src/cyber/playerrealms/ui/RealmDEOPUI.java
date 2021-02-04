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

public class RealmDEOPUI {

    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 54;

    public static void Initialize() {
        inventory_name = Utils.getString("guis.realms.deop");

        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory GUI(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

        Utils.createItem(inv, "COMPASS", 1, 4, Utils.getString("items.realms.gotolobby"));

        if (!new File("realm-" + p.getName()).exists()) {
            Utils.createItem(inv, "DARK_OAK_DOOR", 1, 21, Main.getInstance().getConfig().getString("items.realms.create"));
        } else {
            Utils.createItem(inv, "DARK_OAK_DOOR", 1, 21, Main.getInstance().getConfig().getString("items.realms.go"));
        }

        Utils.createItem(inv, "ACACIA_DOOR", 1, 23, Main.getInstance().getConfig().getString("items.realms.visit"));

        toReturn.setContents(inv.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) throws IOException {
        if (clicked.getItemMeta().getDisplayName().equals(Main.getInstance().getConfig().getString("items.realms.create"))) {
            p.closeInventory();
            Utils.createRealm(p);
        } else if (clicked.getItemMeta().getDisplayName().equals(Main.getInstance().getConfig().getString("items.realms.go"))) {
            p.closeInventory();
            Utils.gotoRealm(p, p);
        } else if (clicked.getItemMeta().getDisplayName().equals(Main.getInstance().getConfig().getString("items.realms.gotolobby"))) {
            Utils.gotoLobby(p);
        } else if (clicked.getItemMeta().getDisplayName().equals(Main.getInstance().getConfig().getString("items.realms.visit"))) {
            new RealmVisitUI(PlayerMenuUtility.getPlayerMenuUtility(p)).open();
        }
    }
}