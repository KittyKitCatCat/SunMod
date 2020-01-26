package com.example.examplemod.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class SunFurnace extends Block
{
    public SunFurnace(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        TileEntity entity = worldIn.getTileEntity(pos);
        SunFurnaceEntity sunentity;
        if (entity instanceof SunFurnaceEntity)
            sunentity = (SunFurnaceEntity) entity;
        else
            return false;

        if (sunentity != null)
        {
            if (player.getHeldItem(handIn) != null)
            {
                sunentity.bottomSlot = player.getHeldItem(handIn);
                player.setHeldItem(handIn, null);
            }

            else
            {
                player.setHeldItem(handIn, sunentity.bottomSlot);
                sunentity.bottomSlot = null;
            }
        }
        return true;
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new SunFurnaceEntity();
    }
}
