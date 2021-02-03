package cyber.playerrealms.ui;

import cyber.playerrealms.Main;
import cyber.playerrealms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.text.SimpleDateFormat;

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

        SimpleDateFormat sdf = new SimpleDateFormat("dd MM, yyyy HH:mm:ss");

        String creation_date = sdf.format(Utils.getRealmDataFile(Utils.getRealm(p)).getConfigurationSection("settings").get("created"));
        String expiration_date = sdf.format(Utils.getRealmDataFile(Utils.getRealm(p)).getConfigurationSection("settings").get("expires"));

        Utils.createItem(inv, "GOLD_BLOCK", 1, 21, Utils.getString("items.realms.manage.renew.title"), Utils.getString("items.realms.manage.renew.lores.created").replaceAll("%created%", creation_date), Utils.getString("items.realms.manage.renew.lores.expires").replaceAll("%expires%", expiration_date));
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
        } else if (clicked.getItemMeta().getDisplayName().equals((Utils.getString("items.realms.manage.renew.title")))) {
            p.closeInventory();
            Utils.renewRealm(p);
        }
    }
}