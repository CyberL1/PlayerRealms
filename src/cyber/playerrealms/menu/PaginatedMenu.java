package cyber.playerrealms.menu;

import cyber.playerrealms.utils.Utils;

public abstract class PaginatedMenu extends Menu {

    public static int page = 0;
    public static int maxItemsPerPage = 45;
    public static int index = 0;

    public PaginatedMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    public static void addMenuBorder() {
        inventory.setItem(48, makeItem("ARROW", Utils.getString("items.previous.name")));
        inventory.setItem(49, makeItem("BARRIER", Utils.getString("items.close.name")));
        inventory.setItem(50, makeItem("ARROW", Utils.getString("items.next.name")));
    }
}