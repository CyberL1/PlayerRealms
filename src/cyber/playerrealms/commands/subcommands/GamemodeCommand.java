package cyber.playerrealms.commands.subcommands;

import cyber.playerrealms.commands.SubCommand;
import cyber.playerrealms.utils.Utils;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GamemodeCommand extends SubCommand {

    @Override
    public String getName() {
        return "gamemode";
    }

    @Override
    public String getDescription() {
        return "Change gamemode";
    }

    @Override
    public String getSyntax() {
        return "/rc gamemode <gamemode> [player]";
    }

    @Override
    public void perform(Player p, String[] args) {

        if (args.length == 1) {
            p.sendMessage(Utils.getString("messages.commands.rc.gamemode.check").replaceAll("%gamemode%", WordUtils.capitalize(String.valueOf(p.getGameMode()).toLowerCase())));
        } else if (args.length == 2) {
            switch (args[1]) {
                case "survival":
                    p.setGameMode(GameMode.SURVIVAL);
                    p.sendMessage(Utils.getString("messages.commands.rc.gamemode.set").replaceAll("%gamemode%", "Survival"));
                    break;
                case "creative":
                    p.setGameMode(GameMode.CREATIVE);
                    p.sendMessage(Utils.getString("messages.commands.rc.gamemode.set").replaceAll("%gamemode%", "Creative"));
                    break;
                case "adventure":
                    p.setGameMode(GameMode.ADVENTURE);
                    p.sendMessage(Utils.getString("messages.commands.rc.gamemode.set").replaceAll("%gamemode%", "Adventure"));
                    break;
                case "spectator":
                    p.setGameMode(GameMode.SPECTATOR);
                    p.sendMessage(Utils.getString("messages.commands.rc.gamemode.set").replaceAll("%gamemode%", "Spectator"));
                    break;
                default:
                    p.sendMessage(Utils.getString("messages.commands.rc.gamemode.error").replaceAll("%gamemode%", args[1]));
            }
        } else if (args.length == 3) {
            switch (args[1]) {
                case "survival":
                    Bukkit.getPlayer(args[2]).setGameMode(GameMode.SURVIVAL);
                    p.sendMessage(Utils.getString("messages.commands.rc.gamemode.setother").replaceAll("%gamemode%", "Survival").replaceAll("%player%", args[2]));
                    break;
                case "creative":
                    Bukkit.getPlayer(args[2]).setGameMode(GameMode.CREATIVE);
                    p.sendMessage(Utils.getString("messages.commands.rc.gamemode.setother").replaceAll("%gamemode%", "Creative").replaceAll("%player%", args[2]));
                    break;
                case "adventure":
                    Bukkit.getPlayer(args[2]).setGameMode(GameMode.ADVENTURE);
                    p.sendMessage(Utils.getString("messages.commands.rc.gamemode.setother").replaceAll("%gamemode%", "Adventure").replaceAll("%player%", args[2]));
                    break;
                case "spectator":
                    Bukkit.getPlayer(args[2]).setGameMode(GameMode.SPECTATOR);
                    p.sendMessage(Utils.getString("messages.commands.rc.gamemode.setother").replaceAll("%gamemode%", "Spectator").replaceAll("%player%", args[2]));
                    break;
            }
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