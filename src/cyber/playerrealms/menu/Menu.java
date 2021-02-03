package cyber.playerrealms.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.Arrays;

public abstract class Menu implements InventoryHolder {

    // Protected values that can be accessed in the menus
    protected PlayerMenuUtility playerMenuUtility;
    protected static Inventory inventory;

    // Constructor for Menu. Pass in a PlayerMenuUtility so that
    // we have information on who's menu this is and
    // what info is to be transfered
    public Menu(PlayerMenuUtility playerMenuUtility) {
        this.playerMenuUtility = playerMenuUtility;
    }

    // Let each menu decide their name
    public abstract String getMenuName();

    // Let each menu decide their slot amount
    public abstract int getSlots();

    // Let each menu decide how the items in the menu will be handled when clicked
    public abstract void handleMenu(InventoryClickEvent e) throws IOException;

    // Let each menu decide what items are to be placed in the inventory menu
    public abstract void setMenuItems();

    // When called, an inventory is created and opened for the player
    public void open() {
        // The owner of the inventory created is the Menu itself,
        // so we are able to reverse engineer the Menu object from the
        // inventoryHolder in the MenuListener class when handling clicks
        inventory = Bukkit.createInventory(this, getSlots(), getMenuName());

        // Grab all the items specified to be used for this menu and add to inventory
        this.setMenuItems();

        // Open the inventory for the player
        playerMenuUtility.getOwner().openInventory(inventory);
    }

    // Overridden method from the InventoryHolder interface
    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public static ItemStack makeItem(Material material, String displayName, String... lore) {

        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);

        itemMeta.setLore(Arrays.asList(lore));
        item.setItemMeta(itemMeta);

        return item;
    }
}