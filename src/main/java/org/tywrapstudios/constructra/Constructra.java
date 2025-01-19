package org.tywrapstudios.constructra;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tywrapstudios.blossombridge.api.config.ConfigManager;
import net.tywrapstudios.blossombridge.api.logging.LoggingHandler;
import org.tywrapstudios.constructra.api.command.v1.EnumArgumentType;
import org.tywrapstudios.constructra.api.resource.v1.ResourceManager;
import org.tywrapstudios.constructra.api.resource.v1.ResourceNode;
import org.tywrapstudios.constructra.command.CaCommandImpl;
import org.tywrapstudios.constructra.config.ConstructraConfig;
import org.tywrapstudios.constructra.network.payload.NodeQueryC2SPayload;
import org.tywrapstudios.constructra.network.payload.NodeQueryS2CPayload;
import org.tywrapstudios.constructra.registry.Content;
import org.tywrapstudios.constructra.util.Util;

import java.io.File;
import java.util.Objects;

public class Constructra implements ModInitializer {
	public static final String MOD_ID = "constructra";
	public static final ConfigManager<ConstructraConfig> CONFIG_MANAGER = new ConfigManager<>(ConstructraConfig.class, new File(FabricLoader.getInstance().getConfigDir().toFile(), "constructra.json5"));
	public static ConstructraConfig config() {return CONFIG_MANAGER.getConfig();}
	public static final LoggingHandler<ConstructraConfig> LOGGER = new LoggingHandler<>("Constructra", CONFIG_MANAGER);

	@Override
	public void onInitialize() {
		CONFIG_MANAGER.loadConfig();
		// WARNING: REMOVE BEFORE FINAL RELEASE. FOR DEV PURPOSES ONLY.
		config().util_config.debug_mode = true;
		CONFIG_MANAGER.saveConfig();
		if (!Objects.equals(config().config_version, "1.0")) {
			LOGGER.warn("Constructra config version does not match up, we may use default values: " + config().config_version);
		}

		ServerTickEvents.END_SERVER_TICK.register(ResourceManager.Nodes::tick);
		CommandRegistrationCallback.EVENT.register((dispatcher, access, env) -> CaCommandImpl.register(dispatcher));
		ArgumentTypeRegistry.registerArgumentType(id("enum_argument"), EnumArgumentType.class, new EnumArgumentType.Info());

		PayloadTypeRegistry.playC2S().register(NodeQueryC2SPayload.ID, NodeQueryC2SPayload.CODEC);
		PayloadTypeRegistry.playS2C().register(NodeQueryS2CPayload.ID, NodeQueryS2CPayload.CODEC);
		ServerPlayNetworking.registerGlobalReceiver(NodeQueryC2SPayload.ID, (load, ctx) -> {
			ResourceNode<?> node = ResourceManager.Nodes.getAtPos(load.pos(), ctx.server().getOverworld());
			if (node != null) {
				ServerPlayNetworking.send(ctx.player(), new NodeQueryS2CPayload(node));
			}
		});


		Content.registerAll();
		LOGGER.info(Util.generateInitPhrase());
	}

	public static Identifier id(String P) {
		return Identifier.of(MOD_ID, P);
	}
}