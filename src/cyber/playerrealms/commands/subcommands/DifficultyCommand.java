package cyber.playerrealms.commands.subcommands;

import cyber.playerrealms.commands.SubCommand;
import cyber.playerrealms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DifficultyCommand extends SubCommand {

    @Override
    public String getName() {
        return "difficulty";
    }

    @Override
    public String getDescription() {
        return "Change difficulty";
    }

    @Override
    public String getSyntax() {
        return "/rc difficulty <difficulty>";
    }

    @Override
    public void perform(Player p, String[] args) {

        switch (args[1]) {
            case "easy":
                Bukkit.dispatchCommand(Utils.getConsole(), "mv modify set diff easy " + Utils.getRealm(p));
                p.sendMessage(Utils.getString("messages.commands.rc.difficulty.set").replaceAll("%difficulty%", "Easy"));
                break;
            case "hard":
                Bukkit.dispatchCommand(Utils.getConsole(), "mv modify set diff hard " + Utils.getRealm(p));
                p.sendMessage(Utils.getString("messages.commands.rc.difficulty.set").replaceAll("%difficulty%", "Hard"));
                break;
            case "normal":
                Bukkit.dispatchCommand(Utils.getConsole(), "mv modify set diff normal " + Utils.getRealm(p));
                p.sendMessage(Utils.getString("messages.commands.rc.difficulty.set").replaceAll("%difficulty%", "Normal"));
                break;
            case "peaceful":
                Bukkit.dispatchCommand(Utils.getConsole(), "mv modify set diff peaceful " + Utils.getRealm(p));
                p.sendMessage(Utils.getString("messages.commands.rc.difficulty.set").replaceAll("%difficulty%", "Peaceful"));
                break;
            default:
                p.sendMessage(Utils.getString("messages.commands.rc.difficulty.error").replaceAll("%difficulty%", args[1]));
                break;
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        if (args.length == 2) {
            List<String> difficulties = new ArrayList<>();
            difficulties.add("easy");
            difficulties.add("hard");
            difficulties.add("normal");
            difficulties.add("peaceful");

            return difficulties;
        }
        return null;
    }
}