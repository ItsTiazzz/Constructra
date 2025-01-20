package org.tywrapstudios.constructra.config;

import blue.endless.jankson.Comment;
import net.tywrapstudios.blossombridge.api.config.AbstractConfig;

public class ConstructraConfig extends AbstractConfig {
    @Comment("""
            This file has all the configuration options for Constructra.
            Do not change the config_version unless you know what you're doing or else some of your changes may be arbitrarily reset and we will use the default values.""")
    public String config_version = "1.0";
    @Comment("Config related to Resources.")
    public ResourceConfig resources = new ResourceConfig();
    public static class ResourceConfig {
        @Comment("""
                The default purity of each Resource in your world.
                Choose from NONE, IMPURE, NORMAL, PURE or [RANDOM].
                Default: RANDOM""")
        public String default_resource_purity = "RANDOM";
        @Comment("""
                Whether Nodes can be obstructed. When set to false, Nodes don't need to be destructed first in order to be harvested from.
                Default: true""")
        public boolean do_obstructions = true;
        @Comment("""
                Whether to display certain visual hints as to what block is the exact ResourceNode Centre. e.g. through the use of particles.
                Default: false""")
        public boolean visualize_centres = false;
    }
    @Comment("Config related to Commands.")
    public CommandConfig commands = new CommandConfig();
    public static class CommandConfig {
        @Comment("""
                The command alias for the Constructra command. Change this if you're having compatibility issues with other mods.
                Default: ca""")
        public String command_alias = "ca";
        @Comment("""
                The permission level required to use the Node commands in general.
                Default: 2""")
        public int perm_lvl_nodes = 2;
        @Comment("""
                The permission level required to remove Nodes from the world. (Both nodes flush and nodes purge.)
                Note that this is a dangerous permission to give as it can break the entire world!
                Default: 4""")
        public int perm_lvl_nodes_removal = 4;
        @Comment("""
                The permission level required to reload the Constructra config.
                Default: 2""")
        public int perm_lvl_reload = 2;
    }
}
