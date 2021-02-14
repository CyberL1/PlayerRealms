package cyber.playerrealms.commands;

import cyber.playerrealms.menu.PlayerMenuUtility;
import cyber.playerrealms.ui.PluginAdminUI;
import cyber.playerrealms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class PraCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            new PluginAdminUI(PlayerMenuUtility.getPlayerMenuUtility(p)).open();
        } else {
            Bukkit.getLogger().log(Level.INFO, Utils.getString("messages.logs.execute.playeronly"));
        }
        return true;
    }
}