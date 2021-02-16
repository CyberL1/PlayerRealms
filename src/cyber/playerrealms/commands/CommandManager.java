package cyber.playerrealms.commands;

import cyber.playerrealms.commands.subcommands.*;
import cyber.playerrealms.utils.PlayerPermission;
import cyber.playerrealms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandManager implements TabExecutor {

    private ArrayList<SubCommand> subcommands = new ArrayList<>();

    public CommandManager() {
        subcommands.add(new GamemodeCommand());
        subcommands.add(new OpCommand());
        subcommands.add(new DeopCommand());
        subcommands.add(new TimeCommand());
        subcommands.add(new DifficultyCommand());
        subcommands.add(new PvpCommand());
        subcommands.add(new DefaultgamemodeCommand());
        subcommands.add(new ClearCommand());
        subcommands.add(new WeatherCommand());
        subcommands.add(new HelpCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (!p.getWorld().getName().startsWith("realm-")) {
                p.sendMessage(Utils.getString("messages.commands.rc.errors.realmonly"));
                return true;
            }

            if (Utils.getPlayerPermission(p) == PlayerPermission.DEOP) {
                p.sendMessage(Utils.getString("messages.commands.rc.errors.nopermission"));
                return true;
            }

            if (args.length > 0) {
                for (int i = 0; i < getSubCommands().size(); i++) {
                    if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                        try {
                            getSubCommands().get(i).perform(p, args);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else if (args.length == 0) {
                p.sendMessage(Utils.getString("messages.commands.rc.noargs.header"));
                p.sendMessage(Utils.getString("messages.commands.rc.noargs.description"));
                for (int i = 0; i < getSubCommands().size(); i++)
                    p.sendMessage(Utils.getString("messages.commands.rc.noargs.body").replaceAll("%command%", getSubCommands().get(i).getName()).replaceAll("%description%", getSubCommands().get(i).getDescription()));
            }
        } else {
            Bukkit.getLogger().info(Utils.getString("messages.logs.execute.playeronly"));
        }
        return true;
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subcommands;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            ArrayList<String> subcommandsArguments = new ArrayList<>();
            for (int i = 0; i < getSubCommands().size(); i++) {
                subcommandsArguments.add(getSubCommands().get(i).getName());
            }

            return subcommandsArguments;
        } else if (args.length >= 2) {
            for (int i = 0; i < getSubCommands().size(); i++) {
                if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                    return getSubCommands().get(i).getSubcommandArguments((Player) sender, args);
                }
            }
        }
        return null;
    }
}