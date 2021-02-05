package cyber.playerrealms.commands;

import cyber.playerrealms.menu.PlayerMenuUtility;
import cyber.playerrealms.ui.RealmCreatorUI;
import cyber.playerrealms.ui.RealmDEOPUI;
import cyber.playerrealms.ui.RealmOPUI;
import cyber.playerrealms.ui.RealmSelectorUI;
import cyber.playerrealms.utils.PlayerPermission;
import cyber.playerrealms.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RealmsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!p.getWorld().getName().startsWith("realm-"))
                new RealmSelectorUI(PlayerMenuUtility.getPlayerMenuUtility(p)).open();
            if (p.getWorld().getName().startsWith("realm-")) {
                if (Utils.getPlayerPermission(p) == PlayerPermission.CREATOR) {
                    new RealmCreatorUI(PlayerMenuUtility.getPlayerMenuUtility(p)).open();
                } else if (Utils.getPlayerPermission(p) == PlayerPermission.OP) {
                    new RealmOPUI(PlayerMenuUtility.getPlayerMenuUtility(p)).open();
                } else if (Utils.getPlayerPermission(p) == PlayerPermission.DEOP) {
                    new RealmDEOPUI(PlayerMenuUtility.getPlayerMenuUtility(p)).open();
                }
            }
        }
        return true;
    }
}
