package org.tywrapstudios.constructra.config;

import blue.endless.jankson.Comment;
import net.tywrapstudios.blossombridge.api.config.AbstractConfig;

public class ConstructraConfig extends AbstractConfig {
    @Comment("This file has all the configuration options for Constructra." +
             "The default for a value will be [in between square brackets]." +
             "Do not change the config_version unless you know what you're doing or else some of your changes may be arbitrarily reset and we will use the default values.")
    public String config_version = "1.0";
    @Comment("Config related to Resources.")
    public ResourceConfig resources = new ResourceConfig();
    public static class ResourceConfig {
        @Comment("The default purity of each Resource in your world. Choose from NONE, IMPURE, NORMAL, PURE or RANDOM.")
        public String default_resource_purity = "RANDOM";
    }
}
