package com.example.examplemod;

import java.util.function.Supplier;

import com.example.examplemod.Blocks.SunFurnaceEntity;
import com.example.examplemod.ExampleMod.RegistryEvents;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class TERegister
{
    @ObjectHolder("examplemod:sunFurnaceEntity")
    public static TileEntityType<?> sunFurnaceEntity;


    @SubscribeEvent
    public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> e)
    {
        e.getRegistry().registerAll
        (
            TileEntityType.Builder.create((Supplier<TileEntity>) SunFurnaceEntity::new, RegistryEvents.sunFurance).build(null).setRegistryName("sunFurnaceEntity")
        );
    }
}