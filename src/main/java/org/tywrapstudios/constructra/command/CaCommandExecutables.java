package org.tywrapstudios.constructra.command;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.command.argument.RegistryEntryReferenceArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.tywrapstudios.constructra.Constructra;
import org.tywrapstudios.constructra.api.resource.v1.Resource;
import org.tywrapstudios.constructra.api.resource.v1.ResourceManager;
import org.tywrapstudios.constructra.registry.CaRegistries;

public class CaCommandExecutables {
    protected static int reload (CommandContext<ServerCommandSource> ctx) {
        ServerCommandSource source = ctx.getSource();
        source.sendFeedback(() -> Text.translatable("commands.reload.success"), true);
        Constructra.CONFIG_MANAGER.loadConfig();
        return 1;
    }

    protected static int flushNodes(CommandContext<ServerCommandSource> ctx) {
        ServerCommandSource source = ctx.getSource();
        source.sendFeedback(() -> Text.translatable("text.constructra.command.flush").formatted(Formatting.RED), true);
        ResourceManager.Nodes.flush("Flushed from command by " + source.getName(), ctx.getSource().getWorld());
        return 1;
    }

    protected static int purgeNode(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        BlockPos pos = BlockPosArgumentType.getLoadedBlockPos(ctx,"pos");
        ResourceManager.Nodes.purge(pos, 1, ctx.getSource().getWorld());
        return 1;
    }

    protected static int purgeRangedNode(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        BlockPos pos = BlockPosArgumentType.getLoadedBlockPos(ctx,"pos");
        int range = IntegerArgumentType.getInteger(ctx, "range");
        ResourceManager.Nodes.purge(pos, range, ctx.getSource().getWorld());
        return 1;
    }

    protected static int spawnNode(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        if (!ctx.getSource().getWorld().getRegistryKey().equals(World.OVERWORLD)) {
            Constructra.LOGGER.warn("Attempted to manually place ResourceNode in non-overworld World. Skipping.");
            ctx.getSource().sendFeedback(() -> Text.translatable("text.constructra.command.error.non-overworld").formatted(Formatting.RED), true);
            return 0;
        }
        BlockPos pos = BlockPosArgumentType.getLoadedBlockPos(ctx,"pos");
        Resource resource = RegistryEntryReferenceArgumentType.getRegistryEntry(ctx, "type", CaRegistries.Keys.RESOURCE).value();
        boolean obstructed = BoolArgumentType.getBool(ctx,"obstructed");

        ResourceManager.Nodes.addNode(resource, pos, obstructed, ctx.getSource().getWorld());
        return 1;
    }

    protected static int execute(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        source.sendFeedback(() -> Text.translatable("text.constructra.command.constructra", source.getName()), false);
        return 1;
    }
}
