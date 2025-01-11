package org.tywrapstudios.constructra.util;

import net.fabricmc.loader.api.FabricLoader;

import java.util.List;
import java.util.Random;

public class Util {
    public static String generateInitPhrase() {
        final List<String> INIT_PHRASES = List.of(
                "I love Config formatting version AAAA it reminds me of my mental health.",
                "Roses are red, violets are blue, unexpected \"{\" at line 32.",
                "garlic bread?",
                "Stop reading init messages, go build a factory!",
                "\"glorps glop bieb bobls\" - The Aliens saying this mod is fire",
                "UwUwUwUwUwUwUwUwUwUwUwUwU",
                "Stub your little toe.",
                "THE STORM IS COMING.",
                "Hier heb ik geen actieve herinnering aan",
                "Axolotls are better than @Tiazzzz",
                "your soulmate is out there somewhere, but god is probably preventing the meetup.",
                "On dit que pétrire c’est modeler , moi je dit que péter c’est démolir",
                "Jus Monika",
                "···· · ···· ·  −−··−−  −−· −−− − −·−· ···· ·−",
                "Leave your E kittens and go outside get a real size cartboard"
        );

        return INIT_PHRASES.get(new Random().nextInt(INIT_PHRASES.size()));
    }

    public static String getModVer(String modId) {
        if (FabricLoader.getInstance().isModLoaded(modId)) {
            return FabricLoader.getInstance().getModContainer(modId).orElseThrow().getMetadata().getVersion().getFriendlyString();
        } else {
            return String.format("\"%s\" version not found, mod isn't loaded.", modId);
        }
    }
}
