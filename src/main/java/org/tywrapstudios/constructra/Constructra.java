package org.tywrapstudios.constructra;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import net.tywrapstudios.blossombridge.api.config.ConfigManager;
import net.tywrapstudios.blossombridge.api.logging.LoggingHandler;
import org.tywrapstudios.constructra.api.resource.v1.ResourceManager;
import org.tywrapstudios.constructra.config.ConstructraConfig;
import org.tywrapstudios.constructra.registry.Content;
import org.tywrapstudios.constructra.resource.Resources;
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
			LOGGER.warn("Constructra config version does not match up, we may use default values:" + config().config_version);
		}

		ServerTickEvents.END_SERVER_TICK.register(ResourceManager.Nodes::tick);

		ServerMessageEvents.CHAT_MESSAGE.register((signedMessage, serverPlayerEntity, parameters) -> {
			String msg = signedMessage.getSignedContent();
			if (msg.equals(";;nodes spawn iron")) {
				ResourceManager.Nodes.addNode(Resources.IRON, serverPlayerEntity.getBlockPos(), false, serverPlayerEntity.getWorld());
			}
			if (msg.equals(";;nodes spawn iron obs")) {
				ResourceManager.Nodes.addNode(Resources.IRON, serverPlayerEntity.getBlockPos(), true, serverPlayerEntity.getWorld());
			}
			if (msg.equals(";;nodes spawn gold")) {
				ResourceManager.Nodes.addNode(Resources.GOLD, serverPlayerEntity.getBlockPos(), false, serverPlayerEntity.getWorld());
			}
			if (msg.equals(";;nodes spawn gold obs")) {
				ResourceManager.Nodes.addNode(Resources.GOLD, serverPlayerEntity.getBlockPos(), true, serverPlayerEntity.getWorld());
			}
			if (msg.equals(";;nodes flush")) {
				ResourceManager.Nodes.flush();
			}
			if (msg.equals(";;reload")) {
				CONFIG_MANAGER.loadConfig();
			}
		});

		Content.registerAll();
		LOGGER.info(Util.generateInitPhrase());
	}

	public static Identifier id(String P) {
		return Identifier.of(MOD_ID, P);
	}
}