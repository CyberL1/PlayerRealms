package cyber.playerrealms.commands.subcommands;

import cyber.playerrealms.commands.SubCommand;
import cyber.playerrealms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PvpCommand extends SubCommand {

    @Override
    public String getName() {
        return "pvp";
    }

    @Override
    public String getDescription() {
        return "Set pvp mode";
    }

    @Override
    public String getSyntax() {
        return "/rc pvp <mode>";
    }

    @Override
    public void perform(Player p, String[] args) {

        if (args.length == 1) {
            p.sendMessage(Utils.getString("messages.commands.rc.pvp.check").replaceAll("%pvp%", String.valueOf(p.getWorld().getPVP())));
        } else {
            switch (args[1]) {
                case "true":
                    Bukkit.dispatchCommand(Utils.getConsole(), "mv modify set pvp true " + Utils.getRealm(Bukkit.getWorld(p.getWorld().getName()).getEnvironment().toString(), p));
                    p.sendMessage(Utils.getString("messages.commands.rc.pvp.set").replaceAll("%pvp%", "true"));
                    break;
                case "false":
                    Bukkit.dispatchCommand(Utils.getConsole(), "mv modify set pvp false " + Utils.getRealm(Bukkit.getWorld(p.getWorld().getName()).getEnvironment().toString(), p));
                    p.sendMessage(Utils.getString("messages.commands.rc.pvp.set").replaceAll("%pvp%", "false"));
                    break;
                default:
                    p.sendMessage(Utils.getString("messages.commands.rc.pvp.error").replaceAll("%pvp%", args[1]));
                    break;
            }
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        if (args.length == 2) {
            List<String> modes = new ArrayList<>();
            modes.add("true");
            modes.add("false");

            return modes;
        }
        return null;
    }
}