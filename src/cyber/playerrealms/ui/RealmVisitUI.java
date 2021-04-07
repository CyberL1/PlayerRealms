package cyber.playerrealms.ui;

import cyber.playerrealms.menu.Menu;
import cyber.playerrealms.menu.PaginatedMenu;
import cyber.playerrealms.menu.PlayerMenuUtility;
import cyber.playerrealms.utils.RealmVisibility;
import cyber.playerrealms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class RealmVisitUI extends Menu {

    public RealmVisitUI(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return Utils.getString("guis.realms.visit");
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws IOException {
        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

        if (item.getType().equals(Material.matchMaterial(Utils.getString("items.realms.visit.tpitem")))) {
            Utils.gotoRealm("OVERWORLD", Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName()), p);
        } else if (item.getItemMeta().getDisplayName().equals(Utils.getString("items.close"))) {
            p.closeInventory();
        } else if (item.getItemMeta().getDisplayName().equals(Utils.getString("items.previous"))) {
                if (PaginatedMenu.page == 0) {
                    return;
                } else {
                    PaginatedMenu.page = PaginatedMenu.page - 1;
                    super.open();
                }
            } else if (item.getItemMeta().getDisplayName().equals(Utils.getString("items.next"))) {
                List<World> realms = Bukkit.getWorlds()
                        .stream()
                        .filter(w ->
                                w.getName().startsWith("realm-") &&
                                        !w.getName().equals("realm-" + p.getName()) &&
                                        !w.getName().endsWith("_nether") &&
                                        !w.getName().endsWith("_the_end") &&
                                        Utils.getRealmVisibility(w.getName()) == RealmVisibility.VISIBLE)
                        .collect(Collectors.toList());

                if (!((PaginatedMenu.index + 1) >= realms.size())) {
                    PaginatedMenu.page = PaginatedMenu.page + 1;
                    super.open();
                } else {
                    return;
                }
            }
        }

    @Override
    public void setMenuItems() {

        PaginatedMenu.addMenuBorder();

        List<World> realms = Bukkit.getWorlds()
                .stream()
                .filter(w ->
                        w.getName().startsWith("realm-") &&
                                !w.getName().equals("realm-" + playerMenuUtility.getOwner().getName()) &&
                                !w.getName().endsWith("_nether") &&
                                !w.getName().endsWith("_the_end") &&
                                Utils.getRealmVisibility(w.getName()) == RealmVisibility.VISIBLE)
                .collect(Collectors.toList());

        if (!realms.isEmpty()) {
            for (int i = 0; i < PaginatedMenu.maxItemsPerPage; i++) {
                PaginatedMenu.index = PaginatedMenu.maxItemsPerPage * PaginatedMenu.page + i;
                if (PaginatedMenu.index >= realms.size()) break;
                if (realms.get(PaginatedMenu.index) != null) {
                    ItemStack realmItem = new ItemStack(Material.matchMaterial(Utils.getString("items.realms.visit.tpitem")), 1);
                    ItemMeta realmMeta = realmItem.getItemMeta();
                    realmMeta.setDisplayName(realms.get(PaginatedMenu.index).getName().substring("realm-".length()));

                    realmItem.setItemMeta(realmMeta);
                    inventory.addItem(realmItem);
                }
            }
        }
    }
}