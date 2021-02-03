package cyber.playerrealms.commands.subcommands;

import cyber.playerrealms.commands.SubCommand;
import cyber.playerrealms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class ClearCommand extends SubCommand {

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "Clear inventory";
    }

    @Override
    public String getSyntax() {
        return "/rc clear [player]";
    }

    @Override
    public void perform(Player p, String[] args) {
        if (args.length != 2) {
            p.getInventory().clear();
            p.sendMessage(Utils.getString("messages.commands.rc.clear.success").replaceAll("%target%", p.getName()));
        } else {
            Bukkit.getPlayer(args[1]).getInventory().clear();
            p.sendMessage(Utils.getString("messages.commands.rc.clear.success").replaceAll("%target%", args[1]));
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }
}