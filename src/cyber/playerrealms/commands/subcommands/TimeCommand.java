package cyber.playerrealms.commands.subcommands;

import cyber.playerrealms.commands.SubCommand;
import cyber.playerrealms.utils.Utils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TimeCommand extends SubCommand {

    @Override
    public String getName() {
        return "time";
    }

    @Override
    public String getDescription() {
        return "Set time in realm";
    }

    @Override
    public String getSyntax() {
        return "/rc time <time>";
    }

    @Override
    public void perform(Player p, String[] args) {

        if (args.length == 1) {
            p.sendMessage(Utils.getString("messages.commands.rc.time.check").replaceAll("%time%", String.valueOf(p.getWorld().getTime())));
        } else {
            switch (args[1]) {
                case "day":
                    p.getWorld().setTime(1000);
                    p.sendMessage(Utils.getString("messages.commands.rc.time.set").replaceAll("%time%", "day"));
                    break;
                case "midnight":
                    p.getWorld().setTime(18000);
                    p.sendMessage(Utils.getString("messages.commands.rc.time.set").replaceAll("%time%", "midnight"));
                    break;
                case "night":
                    p.getWorld().setTime(13000);
                    p.sendMessage(Utils.getString("messages.commands.rc.time.set").replaceAll("%time%", "night"));
                    break;
                case "noon":
                    p.getWorld().setTime(6000);
                    p.sendMessage(Utils.getString("messages.commands.rc.time.set").replaceAll("%time%", "noon"));
                    break;
                default:
                    p.sendMessage(Utils.getString("messages.commands.rc.time.error").replaceAll("%time%", args[1]));
            }
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        if (args.length == 2) {
            List<String> time = new ArrayList<>();
            time.add("day");
            time.add("night");
            time.add("midnight");
            time.add("noon");

            return time;
        }
        return null;
    }
}