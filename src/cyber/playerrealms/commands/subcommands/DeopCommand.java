package cyber.playerrealms.commands.subcommands;

import cyber.playerrealms.commands.SubCommand;
import cyber.playerrealms.utils.PlayerPermission;
import cyber.playerrealms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;

public class DeopCommand extends SubCommand {

    @Override
    public String getName() {
        return "deop";
    }

    @Override
    public String getDescription() {
        return "Remove operator from player";
    }

    @Override
    public String getSyntax() {
        return "/rc deop <player>";
    }

    @Override
    public void perform(Player p, String[] args) {

        try {
            if (Utils.getPlayerPermission(Bukkit.getPlayer(args[1])) == PlayerPermission.CREATOR) {
                p.sendMessage(Utils.getString("messages.commands.rc.deop.creator"));
                return;
            }
            Utils.setPlayerPermission(p.getWorld().getName(), p, PlayerPermission.DEOP);
            p.sendMessage(Utils.getString("messages.commands.rc.deop.success").replaceAll("%player%", args[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }
}