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

        switch (args[1]) {
            case "survival":
                Bukkit.dispatchCommand(Utils.getConsole(), "mv modify set mode survival " + Utils.getRealm(p));
                p.sendMessage(Utils.getString("messages.commands.rc.defaultgamemode.set").replaceAll("%gamemode%", "Survival"));
                break;
            case "creative":
                Bukkit.dispatchCommand(Utils.getConsole(), "mv modify set mode creative " + Utils.getRealm(p));
                p.sendMessage(Utils.getString("messages.commands.rc.defaultgamemode.set").replaceAll("%gamemode%", "Creative"));
                break;
            case "adventure":
                Bukkit.dispatchCommand(Utils.getConsole(), "mv modify set mode adventure " + Utils.getRealm(p));
                p.sendMessage(Utils.getString("messages.commands.rc.defaultgamemode.set").replaceAll("%gamemode%", "Adventure"));
                break;
            case "spectator":
                Bukkit.dispatchCommand(Utils.getConsole(), "mv modify set mode spectator " + Utils.getRealm(p));
                p.sendMessage(Utils.getString("messages.commands.rc.defaultgamemode.set").replaceAll("%gamemode%", "Spectator"));
                break;
            default:
                p.sendMessage(Utils.getString("messages.commands.rc.defaultgamemode.error").replaceAll("%gamemode%", args[1]));
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