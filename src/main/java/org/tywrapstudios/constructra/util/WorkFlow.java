package org.tywrapstudios.constructra.util;

abstract class WorkFlow {
    /*
        Hello! Welcome to Constructra!
        This mod is a massive work in progress, so to keep everything organized and tidy, I have created this.
        In here I will put various things that I want to keep track of, and will be updated as I go.
        This class will not have any code in it that you should directly use, just comments or other stuff.

        Maybe I'll replace this with a Trello or Notion page idk
     */

    /*
        NEXATEK -> Company.
        WICKED (Waste Inspection and Coordination for Kinetic Evaluation and Deployment) -> Changing waste into currency.
        Terbysium -> Special Alloy only found in the asteroid belt.
        DART (Directly Artificial Research Tree) -> Research Tree.
        CASU (Controlled Amplified Shock Unit) -> Shock based weapon.
        Electronic Boomstick -> Stronger version of the CASU.
        CIO (Computer Integrated Operator) -> AI Personal Assistant.
        OperAXIS -> HUD Operating System name, to display information.
     */

    /*
       TODO LIST
        - Think about whether I want to completely change the base game or actually keep some functionality.
          (Like disabling certain items, or bigger such as the health system)
            - Getting rid of a lot of recipes might be a good idea.
        - Further implementing the Resource System, now with actual harvesting
            - Harvesting will be done in the following way:
            1. The user finds a [Randomly Generated] ResourceNode with a Resource set to it (e.g. Iron)
            2. The user can hold down the attack button (Left Click) to start "harvesting".
            3. Every so often, the Node spews out a piece of the Resource's retrievableItem.
                - This time frame is directly modified by the ResourcePurity
            4. If the Node was obstructed, the third time the node gets harvested from, it will de-obstruct.
            5. After Nodes are not obstructed anymore (Porta-)Miners can be used to harvest from the Node automatically.
            Current Problems:
            - Vanilla Blocks do not have a pretty way of making them a Resource.
                - We Might have to resort to fully disabling them in general, which falls back to the first point.
            - I have yet to implement Random Generation.
     */

    // Items
    /* TODO: ALL ITEMS TO ADD
        A
        AI Expansion Server
        Alien DNA Capsule
        Alien Power Matrix
        Alien Protein
        Alien Remains
        B
        Bacon Agaric
        Ballistic Warp Drive
        Bauxite
        Beacon
        Beryl Nut
        Biochemical Sculptor
        Boom Box
        C
        Color Cartridge
        Cup
        D
        Dark Matter Crystal
        Diamonds
        E
        Electromagnetic Control Rod
        F
        Flower Petals
        H
        Hard Drive
        L
        Limestone
        M
        Magnetic Field Generator
        Medicinal Inhaler
        Miner
        N
        Neural-Quantum Processor
        NEXATEK Coupon
        Nexite Ingot
        Nexite Triangle
        Nexonium
        P
        Paleberry
        Power Shard
        Power Slug
        R
        Raw Quartz
        S
        Singularity Cell
        Smokeless Powder
        Statues
        Sulfur
        T
        Thermal Propulsion Rocket
        Time Crystal
        U
        Uranium
     */

    // Buildings
    /* TODO: ALL BUILDINGS TO ADD
        A
        Alien Power Augmenter
        Assembler
        WICKED Shop
        WICKED Burner
        B
        Biomass Burner
        Blender
        C
        Coal-Powered Generator
        Constructor
        Converter
        Conveyor Belts
        Conveyor Lifts
        Conveyor Merger
        Conveyor Poles
        Conveyor Splitter
        Crafting Bench
        D
        Dimensional Depot Uploader
        Drone Port
        E
        Equipment Workshop
        F
        Fluid Buffer
        Foundry
        Freight Platform
        Fuel-Powered Generator
        G
        Geothermal Generator
        H
        Hypertube
        Hypertube Entrance
        Hypertube Supports
        I
        Indicator Light
        J
        Jump Pad
        L
        Lookout Tower
        M
        MAM -> reimplement
        Manufacturer
        Miner
        N
        Nuclear Power Plant
        O
        Oil Extractor
        P
        Packager
        Particle Accelerator
        Personal Storage Box
        Pipeline Junction
        Pipeline Pump
        Pipeline Supports
        Pipelines
        Portal
        Power Line
        Power Poles
        Power Storage
        Power Switch
        Power Tower
        Priority Power Switch
        Programmable Splitter
        Q
        Quantum Encoder
        R
        Radar Tower
        Railway
        Refinery
        Resource Well Pressurizer
        S
        Smart Splitter
        Smelter
        Space Elevator
        Storage Container
        T
        The BASE
        Train Signals
        Train Station
        Truck Station
        U
        U-Jelly Landing Pad
        V
        Valve
        W
        Water Extractor
     */

    // Equipment
    /* TODO: ALL EQUIPMENT TO ADD
        B
        Blade Runners
        Boomstick
        C
        Candy Cane Boomstick
        CASU (Controlled Amplified Shock Unit)
        Chainsaw
        Cluster Nobelisk
        E
        Explosive Rebar
        F
        Factory Cart -> reimplement
        G
        Gas Filter
        Gas Mask
        Gas Nobelisk
        H
        Hazmat Suit
        Homing Rifle Ammo
        Hover pack
        I
        Iodine-Infused Filter
        Iron Rebar
        J
        Jetpack
        M
        Medical Inhaler
        N
        Nobelisk
        Nobelisk Detonator
        Nuke Nobelisk
        O
        Object Scanner
        P
        Parachute
        Portable Miner
        Pulse Nobelisk
        R
        Rebar Gun
        Rifle
        S
        Shatter Rebar
        Stun Rebar
        T
        Turbo Rifle Ammo
        Z
        Zipline
     */

    // Fluids
    /* TODO: ALL FLUIDS TO ADD
        A
        Alumina Solution
        C
        Crude Oil
        D
        Dark Matter Residue
        Dissolved Silica
        E
        Excited Photonic Matter
        F
        Fuel
        H
        Heavy Oil Residue
        I
        Ionized Fuel
        L
        Liquid Biofuel
        N
        Nitric Acid
        Nitrogen Gas
        R
        Rocket Fuel
        S
        Sulfuric Acid
        T
        Turbofuel
        W
        Water
     */
}
