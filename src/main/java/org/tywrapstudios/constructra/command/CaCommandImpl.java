package org.tywrapstudios.constructra.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.minecraft.command.argument.Vec3ArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.tywrapstudios.constructra.api.command.v1.EnumArgumentType;
import org.tywrapstudios.constructra.resource.Resources;

public class CaCommandImpl {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
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
                .argument("pos", Vec3ArgumentType.vec3())
                .build();

        var typeArg = CommandManager
                .argument("type", EnumArgumentType.enumArgument(Resources.class))
                .build();

        var obsArg = CommandManager
                .argument("obstructed", BoolArgumentType.bool())
                .executes(CaCommandExecutables::spawnNode)
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
    }
}
