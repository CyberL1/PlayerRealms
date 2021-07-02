package cyber.playerrealms.commands.subcommands;

import cyber.playerrealms.commands.SubCommand;
import cyber.playerrealms.utils.Utils;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GameruleCommand extends SubCommand {

    @Override
    public String getName() {
        return "gamerule";
    }

    @Override
    public String getDescription() {
        return "Set some gamerules";
    }

    @Override
    public String getSyntax() {
        return "/rc gamerule <rule> [value]";
    }

    @Override
    public void perform(Player p, String[] args) {

        if (args.length == 2) {
            switch (args[1]) {
                case "announceAdvancements":
                case "disableElytraMovementCheck":
                case "disableRaids":
                case "doDaylightCycle":
                case "doEntityDrops":
                case "doFireTick":
                case "doInsomnia":
                case "doImmediateRespawn":
                case "doLimitedCrafting":
                case "doMobLoot":
                case "doMobSpawning":
                case "doTileDrops":
                case "doTraderSpawning":
                case "doWeatherCycle":
                case "drowningDamage":
                case "fallDamage":
                case "fireDamage":
                case "forgiveDeadPlayers":
                // When 1.17 is out: case "freezeeDamage":
                case "keepInventory":
                case "maxEntityCramming":
                case "mobGriefing":
                case "naturalRegeneration":
                // When 1.17 is out: case "playersSleepingPercentage":
                case "randomTickSpeed":
                case "sendCommandFeedback":
                case "showDeathMessages":
                case "spanRadius":
                case "spectatorsGenerateChunks":
                case "universalAnger":
                    p.sendMessage(Utils.getString("messages.commands.rc.gamerule.check").replaceAll("%gamerule%", args[1]).replaceAll("%value%", String.valueOf(p.getWorld().getGameRuleValue(GameRule.getByName(args[1])))));
                    break;
                default:
                    p.sendMessage(Utils.getString("messages.commands.rc.gamerule.errors.invalid").replaceAll("%gamerule%", args[1]));
                    break;
            }
        } else if (args.length == 3) {
            World world = p.getWorld();
            Boolean done = false;
            switch (args[1]) {
                case "announceAdvancements":
                    world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, args[2].equals("true"));
                    done = true;
                    break;
                case "disableElytraMovementCheck":
                    world.setGameRule(GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK, args[2].equals("true"));
                    done = true;
                    break;
                case "disableRaids":
                    world.setGameRule(GameRule.DISABLE_RAIDS, args[2].equals("true"));
                    done = true;
                    break;
                case "doDaylightCycle":
                    world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, args[2].equals("true"));
                    done = true;
                    break;
                case "doEntityDrops":
                    world.setGameRule(GameRule.DO_ENTITY_DROPS, args[2].equals("true"));
                    done = true;
                    break;
                case "doFireTick":
                    world.setGameRule(GameRule.DO_FIRE_TICK, args[2].equals("true"));
                    done = true;
                    break;
                case "doInsomnia":
                    world.setGameRule(GameRule.DO_INSOMNIA, args[2].equals("true"));
                    done = true;
                    break;
                case "doImmediateRespawn":
                    world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, args[2].equals("true"));
                    done = true;
                    break;
                case "doLimitedCrafting":
                    world.setGameRule(GameRule.DO_LIMITED_CRAFTING, args[2].equals("true"));
                    done = true;
                    break;
                case "doMobLoot":
                    world.setGameRule(GameRule.DO_MOB_LOOT, args[2].equals("true"));
                    done = true;
                    break;
                case "doMobSpawning":
                    world.setGameRule(GameRule.DO_MOB_SPAWNING, args[2].equals("true"));
                    done = true;
                    break;
                case "doTileDrops":
                    world.setGameRule(GameRule.DO_TILE_DROPS, args[2].equals("true"));
                    done = true;
                    break;
                case "doTraderSpawning":
                    world.setGameRule(GameRule.DO_TRADER_SPAWNING, args[2].equals("true"));
                    done = true;
                    break;
                case "doWeatherCycle":
                    world.setGameRule(GameRule.DO_WEATHER_CYCLE, args[2].equals("true"));
                    done = true;
                    break;
                case "drowningDamage":
                    world.setGameRule(GameRule.DROWNING_DAMAGE, args[2].equals("true"));
                    done = true;
                    break;
                case "fallDamage":
                    world.setGameRule(GameRule.FALL_DAMAGE, args[2].equals("true"));
                    done = true;
                    break;
                case "fireDamage":
                    world.setGameRule(GameRule.FIRE_DAMAGE, args[2].equals("true"));
                    done = true;
                    break;
                case "forgiveDeadPlayers":
                    world.setGameRule(GameRule.FORGIVE_DEAD_PLAYERS, args[2].equals("true"));
                    done = true;
                    break;
                /* When 1.17 is out:
                case "freezeDamage":
                    world.setGameRule(GameRule.FREEZE_DAMAGE, args[2].equals("true"));
                    done = true;
                    break; */
                case "keepInventory":
                    world.setGameRule(GameRule.KEEP_INVENTORY, args[2].equals("true"));
                    done = true;
                    break;
                case "maxEntityCramming":
                    world.setGameRule(GameRule.MAX_ENTITY_CRAMMING, Integer.parseInt(args[2]));
                    done = true;
                    break;
                case "mobGriefing":
                    world.setGameRule(GameRule.MOB_GRIEFING, args[2].equals("true"));
                    done = true;
                    break;
                case "naturalRegeneration":
                    world.setGameRule(GameRule.NATURAL_REGENERATION, args[2].equals("true"));
                    done = true;
                    break;
                /* When 1.17 is out:
                case "playersSleepingPercentage":
                    world.setGameRule(GameRule.PLAYER_SLEEPING_PERCENTAGE, args[2].equals("true"));
                    done = true;
                    break; */
                case "randomTickSpeed":
                    world.setGameRule(GameRule.RANDOM_TICK_SPEED, Integer.parseInt(args[2]));
                    done = true;
                    break;
                case "sendCommandFeedback":
                    world.setGameRule(GameRule.SEND_COMMAND_FEEDBACK, args[2].equals("true"));
                    done = true;
                    break;
                case "showDeathMessages":
                    world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, args[2].equals("true"));
                    done = true;
                    break;
                case "spawnRadius":
                    world.setGameRule(GameRule.SPAWN_RADIUS, Integer.parseInt(args[2]));
                    done = true;
                    break;
                case "spectatorsGenerateChunks":
                    world.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, args[2].equals("true"));
                    done = true;
                    break;
                case "universalAnger":
                    world.setGameRule(GameRule.UNIVERSAL_ANGER, args[2].equals("true"));
                    done = true;
                    break;
                default:
                    p.sendMessage(Utils.getString("messages.commands.rc.gamerule.errors.invalid").replaceAll("%gamerule%", args[1]));
                    break;
            }
            if (done) p.sendMessage(Utils.getString("messages.commands.rc.gamerule.set").replaceAll("%gamerule%", args[1]).replaceAll("%value%", String.valueOf(args[2].equals("true"))));
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        if (args.length == 2) {
            List<String> gamerules = new ArrayList<>();
            gamerules.add("announceAdvancements");
            gamerules.add("disableElytraMovementCheck");
            gamerules.add("disableRaids");
            gamerules.add("doDaylightCycle");
            gamerules.add("doEntityDrops");
            gamerules.add("doFireTick");
            gamerules.add("doInsomnia");
            gamerules.add("doImmediateRespawn");
            gamerules.add("doLimitedCrafting");
            gamerules.add("doMobLoot");
            gamerules.add("doMobSpawning");
            gamerules.add("doPatrolSpawning");
            gamerules.add("doTileDrops");
            gamerules.add("doTraderSpawning");
            gamerules.add("doWeatherCycle");
            gamerules.add("drowningDamage");
            gamerules.add("fallDamage");
            gamerules.add("fireDamage");
            gamerules.add("forgiveDeadPlayers");
            // When 1.17 is out: gamerules.add("freezeDamage");
            gamerules.add("maxEntityCramming");
            gamerules.add("mobGriefing");
            gamerules.add("naturalRegeneration");
            // When 1.17 is out: gamerules.add("playersSleepingPercentage");
            gamerules.add("randomTickSpeed");
            gamerules.add("sendCommandFeedback");
            gamerules.add("showDeathMessages");
            gamerules.add("spawnRadius");
            gamerules.add("spectatorsGenerateChunks");
            gamerules.add("universalAnger");

            return gamerules;
        }
        return null;
    }
}