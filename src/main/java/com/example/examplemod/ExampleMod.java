package com.example.examplemod;

import com.example.examplemod.Blocks.SunFurnace;
import com.google.common.base.Preconditions;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("examplemod")
public class ExampleMod
{
    public static String MODID = "examplemod";
    private static final Logger LOGGER = LogManager.getLogger();

    public ExampleMod()
    {
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {

        public static Block example_block;
        public static Block sun_furance;
        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> blockRegistryEvent)
        {
            IForgeRegistry<Block> registry = blockRegistryEvent.getRegistry();

            sun_furance = registerBlock(registry, new SunFurnace(Block.Properties.from(Blocks.FURNACE)), "sun_furance");
            example_block = registerBlock(registry, new SunFurnace(Block.Properties.from(Blocks.GLASS)), "example_block");
        }

        private static <T extends Block> T registerBlock(IForgeRegistry<Block> registry, T newBlock, String name)
        {
            String prefixedName = MODID + ":" + name;
            newBlock.setRegistryName(prefixedName);
            registry.register(newBlock);
            return newBlock;
        }

        public static Item example_item;
        public static Item test;
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> itemRegistryEvent)
        {
            final IForgeRegistry<Item> registry = itemRegistryEvent.getRegistry();
            registry.registerAll(
                    example_item = setup(new BlockItem(example_block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)), "example_item"),
                    test = setup(new BlockItem(sun_furance, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)), "test")
            );
        }
        @Nonnull
        private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final String name)
        {
            Preconditions.checkNotNull(name, "Name to assign to entry cannot be null!");
            return setup(entry, new ResourceLocation(MODID, name));
        }

        @Nonnull
        private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final ResourceLocation registryName)
        {
            Preconditions.checkNotNull(entry, "Entry cannot be null!");
            Preconditions.checkNotNull(registryName, "Registry name to assign to entry cannot be null!");
            entry.setRegistryName(registryName);
            return entry;
        }
    }
}
