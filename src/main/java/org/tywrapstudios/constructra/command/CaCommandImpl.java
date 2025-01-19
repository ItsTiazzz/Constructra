package org.tywrapstudios.constructra.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.command.argument.RegistryEntryReferenceArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.tywrapstudios.constructra.registry.CaRegistries;

public class CaCommandImpl {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess access) {
        var constructraCommand = CommandManager
                .literal("constructra")
                .executes(CaCommandExecutables::execute)
                .build();

        var caCommand = CommandManager
                .literal("ca")
                .redirect(constructraCommand)
                .build();

        var nodesCommand = CommandManager
                .literal("nodes")
                .build();

        var flushCommand = CommandManager
                .literal("flush")
                .executes(CaCommandExecutables::flushNodes)
                .build();

        var spawnCommand = CommandManager
                .literal("spawn")
                .build();

        var posArg = CommandManager
                .argument("pos", BlockPosArgumentType.blockPos())
                .build();

        var typeArg = CommandManager
                .argument("type", RegistryEntryReferenceArgumentType.registryEntry(access, CaRegistries.Keys.RESOURCE))
                .build();

        var obsArg = CommandManager
                .argument("obstructed", BoolArgumentType.bool())
                .executes(CaCommandExecutables::spawnNode)
                .build();

        var purgeCommand = CommandManager
                .literal("purge")
                .build();

        var posArg2 = CommandManager
                .argument("pos", BlockPosArgumentType.blockPos())
                .executes(CaCommandExecutables::purgeNode)
                .build();

        var rangeArg = CommandManager
                .argument("range", IntegerArgumentType.integer(0))
                .executes(CaCommandExecutables::purgeRangedNode)
                .build();

        var reloadCommand = CommandManager
                .literal("reload")
                .executes(CaCommandExecutables::reload)
                .build();

        dispatcher.getRoot().addChild(constructraCommand);
        dispatcher.getRoot().addChild(caCommand);

        constructraCommand.addChild(nodesCommand);
        constructraCommand.addChild(reloadCommand);

        nodesCommand.addChild(flushCommand);
        nodesCommand.addChild(spawnCommand);
        spawnCommand.addChild(posArg);
        posArg.addChild(typeArg);
        typeArg.addChild(obsArg);
        nodesCommand.addChild(purgeCommand);
        purgeCommand.addChild(posArg2);
        posArg2.addChild(rangeArg);
    }
}
