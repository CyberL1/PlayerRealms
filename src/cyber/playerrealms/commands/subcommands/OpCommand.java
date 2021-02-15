package cyber.playerrealms.commands.subcommands;

import cyber.playerrealms.commands.SubCommand;
import cyber.playerrealms.utils.PlayerPermission;
import cyber.playerrealms.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;

public class OpCommand extends SubCommand {

    @Override
    public String getName() {
        return "op";
    }

    @Override
    public String getDescription() {
        return "Make someone an operator";
    }

    @Override
    public String getSyntax() {
        return "/rc op <player>";
    }

    @Override
    public void perform(Player p, String[] args) {

        if (args.length == 1) {
            p.sendMessage(Utils.getString("messages.commands.rc.errors.noargs"));
        } else {
            try {
                if (Utils.getPlayerPermission(Bukkit.getPlayer(args[1])) == PlayerPermission.CREATOR) {
                    p.sendMessage(Utils.getString("messages.commands.rc.op.creator"));
                    return;
                }
                Utils.setPlayerPermission(p.getWorld().getName(), Bukkit.getPlayer(args[1]), PlayerPermission.OP);
                p.sendMessage(Utils.getString("messages.commands.rc.op.success").replaceAll("%player%", args[1]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }
}