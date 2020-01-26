package com.example.examplemod.Blocks;

import com.example.examplemod.TERegister;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class SunFurnaceEntity extends TileEntity
{
    public ItemStack bottomSlot;
    public ItemStack topSlot;

    public SunFurnaceEntity()
    {
        super(TERegister.sun_furnace_tile_entity);
    }
}