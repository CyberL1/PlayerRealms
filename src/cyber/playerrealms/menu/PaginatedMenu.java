package cyber.playerrealms.menu;

import cyber.playerrealms.utils.Utils;
import org.bukkit.Material;

public abstract class PaginatedMenu extends Menu {

    public static int page = 0;
    public static int maxItemsPerPage = 45;
    public static int index = 0;

    public PaginatedMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

     public static void addMenuBorder() {
        inventory.setItem(48, makeItem(Material.ARROW, Utils.getString("items.previous")));
        inventory.setItem(49, makeItem(Material.BARRIER, Utils.getString("items.close")));
        inventory.setItem(50, makeItem(Material.ARROW, Utils.getString("items.next")));
    }
}