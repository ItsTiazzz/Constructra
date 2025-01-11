package org.tywrapstudios.constructra;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.tywrapstudios.blossombridge.api.config.ConfigManager;
import net.tywrapstudios.blossombridge.api.logging.LoggingHandler;
import org.tywrapstudios.constructra.api.resource.v1.ResourceNode;
import org.tywrapstudios.constructra.config.ConstructraConfig;
import org.tywrapstudios.constructra.registry.Content;
import org.tywrapstudios.constructra.resource.IronResource;
import org.tywrapstudios.constructra.util.Util;

import java.io.File;
import java.util.Objects;

public class Constructra implements ModInitializer {
	public static final String MOD_ID = "constructra";
	public static final ConfigManager<ConstructraConfig> CONFIG_MANAGER = new ConfigManager<>(ConstructraConfig.class, new File(FabricLoader.getInstance().getConfigDir().toFile(), "satisillies.json5"));
	public static ConstructraConfig config() {return CONFIG_MANAGER.getConfig();}
	public static final LoggingHandler<ConstructraConfig> LOGGER = new LoggingHandler<>("Constructra", CONFIG_MANAGER);

	@Override
	public void onInitialize() {
		CONFIG_MANAGER.loadConfig();
		// WARNING: REMOVE BEFORE FINAL RELEASE. FOR DEV PURPOSES ONLY.
		config().util_config.debug_mode = true;
		CONFIG_MANAGER.saveConfig();

		if (!Objects.equals(config().config_version, "1.0")) {
			LOGGER.warn("Constructra config version does not match up, we may use default values:" + config().config_version);
		}

		ServerMessageEvents.CHAT_MESSAGE.register((signedMessage, serverPlayerEntity, parameters) -> {
			ResourceNode<IronResource> NODE = new ResourceNode<>(new IronResource(), new BlockPos(serverPlayerEntity.getBlockPos()), false);
			NODE.createOriginBlock(serverPlayerEntity.getServerWorld());
		});

		Content.registerAll();
		LOGGER.info(Util.generateInitPhrase());
	}

	public static Identifier id(String P) {
		return Identifier.of(MOD_ID, P);
	}
}