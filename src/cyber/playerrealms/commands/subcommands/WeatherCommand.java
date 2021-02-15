package cyber.playerrealms.commands.subcommands;

import cyber.playerrealms.commands.SubCommand;
import cyber.playerrealms.utils.Utils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WeatherCommand extends SubCommand {

    @Override
    public String getName() {
        return "weather";
    }

    @Override
    public String getDescription() {
        return "Set weather in realm";
    }

    @Override
    public String getSyntax() {
        return "/rc weather <weather>";
    }

    @Override
    public void perform(Player p, String[] args) {

        if (args.length == 1) {
            p.sendMessage(Utils.getString("messages.commands.rc.errors.noargs"));
        } else {
            switch (args[1]) {
                case "clear":
                    p.getWorld().setStorm(false);
                    p.getWorld().setThundering(false);
                    p.sendMessage(Utils.getString("messages.commands.rc.weather.set").replaceAll("%weather%", "clear"));
                    break;
                case "rain":
                    p.getWorld().setStorm(true);
                    p.getWorld().setThundering(false);
                    p.sendMessage(Utils.getString("messages.commands.rc.weather.set").replaceAll("%weather%", "rain"));
                    break;
                case "thunder":
                    p.getWorld().setStorm(true);
                    p.getWorld().setThundering(true);
                    p.sendMessage(Utils.getString("messages.commands.rc.weather.set").replaceAll("%weather%", "thunder"));
                    break;
                default:
                    p.sendMessage(Utils.getString("messages.commands.rc.weather.error").replaceAll("%weather%", args[1]));
            }
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        if (args.length == 2) {
            List<String> time = new ArrayList<>();
            time.add("clear");
            time.add("rain");
            time.add("thunder");

            return time;
        }
        return null;
    }
}