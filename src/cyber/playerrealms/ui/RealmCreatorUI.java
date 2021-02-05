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

        if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.getString("items.realms.visit.name"))) {
            new RealmVisitUI(PlayerMenuUtility.getPlayerMenuUtility(p)).open();
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.getString("items.realms.gotolobby.name"))) {
            Utils.gotoLobby(p);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.getString("items.realms.manage.name"))) {
            new RealmManageUI(PlayerMenuUtility.getPlayerMenuUtility(p)).open();
        }
    }

    @Override
    public void setMenuItems() {

        inventory.setItem(0, makeItem(Utils.getString("items.realms.visit.item"), Utils.getString("items.realms.visit.name")));
        inventory.setItem(1, makeItem(Utils.getString("items.realms.manage.item"), Utils.getString("items.realms.manage.name")));
        inventory.setItem(2, makeItem(Utils.getString("items.realms.gotolobby.item"), Utils.getString("items.realms.gotolobby.name")));

    }
}