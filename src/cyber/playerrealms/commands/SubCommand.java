package cyber.playerrealms.commands;

import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;

public abstract class SubCommand {

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract void perform(Player player, String args[]) throws IOException;

    public abstract List<String> getSubcommandArguments(Player player, String args[]);

}
