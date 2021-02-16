package cyber.playerrealms.commands.subcommands;

import cyber.playerrealms.commands.CommandManager;
import cyber.playerrealms.commands.SubCommand;
import cyber.playerrealms.utils.Utils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand extends SubCommand {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Get help on command";
    }

    @Override
    public String getSyntax() {
        return "/rc help <command>";
    }

    @Override
    public void perform(Player p, String[] args) {


        if (args.length == 1) {
            p.sendMessage(Utils.getString("messages.commands.rc.errors.noargs"));
        } else {
            ArrayList<SubCommand> subcommands = new CommandManager().getSubCommands();
            SubCommand command = null;
            for (SubCommand c : subcommands) {
                command = c;
                break;
            }
            if (args[1].equals(command.getName())) {
                p.sendMessage(Utils.getString("messages.commands.rc.help.body").replaceAll("%command%", command.getName()).replaceAll("%description%", command.getDescription()).replaceAll("%syntax%", command.getSyntax()));
            } else {
                p.sendMessage(Utils.getString("messages.commands.rc.help.error"));
            }
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        if (args.length == 2) {
            ArrayList<String> subcommandsArguments = new ArrayList<>();

            for (int i = 0; i < new CommandManager().getSubCommands().size(); i++) {
                subcommandsArguments.add(new CommandManager().getSubCommands().get(i).getName());
            }

            return subcommandsArguments;
        }
        return null;
    }
}