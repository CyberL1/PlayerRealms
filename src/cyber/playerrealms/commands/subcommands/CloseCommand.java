package cyber.playerrealms.commands.subcommands;

import cyber.playerrealms.commands.SubCommand;
import cyber.playerrealms.utils.Utils;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;

public class CloseCommand extends SubCommand {

    @Override
    public String getName() {
        return "close";
    }

    @Override
    public String getDescription() {
        return "Close realm";
    }

    @Override
    public String getSyntax() {
        return "/rc close";
    }

    @Override
    public void perform(Player p, String[] args) throws IOException {
        Utils.closeRealm(p);
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }
}