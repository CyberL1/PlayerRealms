package cyber.playerrealms.ui;

import cyber.playerrealms.menu.Menu;
import cyber.playerrealms.menu.PlayerMenuUtility;
import cyber.playerrealms.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class RealmCreatorUI extends Menu {

    public RealmCreatorUI(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return Utils.getString("guis.realms.creator");
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.getString("items.realms.visit"))) {
            new RealmVisitUI(PlayerMenuUtility.getPlayerMenuUtility(p)).open();
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.getString("items.realms.gotolobby"))) {
            Utils.gotoLobby(p);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.getString("items.realms.manage.item"))) {
            new RealmManageUI(PlayerMenuUtility.getPlayerMenuUtility(p)).open();
        }
    }

    @Override
    public void setMenuItems() {

        inventory.setItem(0, makeItem("ACACIA_DOOR", Utils.getString("items.realms.visit")));
        inventory.setItem(2, makeItem("COMPASS", Utils.getString("items.realms.gotolobby")));
        inventory.setItem(1, makeItem("REDSTONE_BLOCK", Utils.getString("items.realms.manage.item")));

    }
}