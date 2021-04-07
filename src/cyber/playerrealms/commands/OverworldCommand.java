package cyber.playerrealms.commands;

import cyber.playerrealms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OverworldCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (!p.getWorld().getName().startsWith("realm-")) {
                p.sendMessage(Utils.getString("messages.commands.rc.errors.realmonly"));
                return true;
            }
            if (p.getWorld().getEnvironment() == World.Environment.NORMAL) {
                p.sendMessage(Utils.getString("messages.commands.overworld.errors.alreadyinoverworld"));
                return true;
            } else {
                Utils.gotoRealm("OVERWORLD", p, p);
            }
        } else {
            Bukkit.getLogger().info(Utils.getString("messages.logs.execute.playeronly"));
        }
        return true;
    }
}