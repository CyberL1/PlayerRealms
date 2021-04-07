package cyber.playerrealms.commands.subcommands;

import cyber.playerrealms.commands.SubCommand;
import cyber.playerrealms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DefaultgamemodeCommand extends SubCommand {

    @Override
    public String getName() {
        return "defaultgamemode";
    }

    @Override
    public String getDescription() {
        return "Change default gamemode";
    }

    @Override
    public String getSyntax() {
        return "/rc defaultgamemode <gamemode>";
    }

    @Override
    public void perform(Player p, String[] args) {

        if (args.length == 1) {
            p.sendMessage(Utils.getString("messages.commands.rc.errors.noargs"));
        } else {
            switch (args[1]) {
                case "survival":
                    Bukkit.dispatchCommand(Utils.getConsole(), "mv modify set mode survival " + Utils.getRealm(Bukkit.getWorld(p.getWorld().getName()).getEnvironment().toString(), p));
                    p.sendMessage(Utils.getString("messages.commands.rc.defaultgamemode.set").replaceAll("%gamemode%", "Survival"));
                    break;
                case "creative":
                    Bukkit.dispatchCommand(Utils.getConsole(), "mv modify set mode creative " + Utils.getRealm(Bukkit.getWorld(p.getWorld().getName()).getEnvironment().toString(), p));
                    p.sendMessage(Utils.getString("messages.commands.rc.defaultgamemode.set").replaceAll("%gamemode%", "Creative"));
                    break;
                case "adventure":
                    Bukkit.dispatchCommand(Utils.getConsole(), "mv modify set mode adventure " + Utils.getRealm(Bukkit.getWorld(p.getWorld().getName()).getEnvironment().toString(), p));
                    p.sendMessage(Utils.getString("messages.commands.rc.defaultgamemode.set").replaceAll("%gamemode%", "Adventure"));
                    break;
                case "spectator":
                    Bukkit.dispatchCommand(Utils.getConsole(), "mv modify set mode spectator " + Utils.getRealm(Bukkit.getWorld(p.getWorld().getName()).getEnvironment().toString(), p));
                    p.sendMessage(Utils.getString("messages.commands.rc.defaultgamemode.set").replaceAll("%gamemode%", "Spectator"));
                    break;
                default:
                    p.sendMessage(Utils.getString("messages.commands.rc.defaultgamemode.error").replaceAll("%gamemode%", args[1]));
            }
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        if (args.length == 2) {
            List<String> gamemodes = new ArrayList<>();
            gamemodes.add("survival");
            gamemodes.add("creative");
            gamemodes.add("adventure");
            gamemodes.add("spectator");

            return gamemodes;
        }
        return null;
    }
}