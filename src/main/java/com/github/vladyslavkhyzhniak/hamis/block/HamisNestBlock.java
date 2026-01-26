package com.github.vladyslavkhyzhniak.hamis.block;

import com.github.vladyslavkhyzhniak.hamis.entity.HamisEntity;
import com.github.vladyslavkhyzhniak.hamis.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import java.util.List;
import java.util.Random;

public class HamisNestBlock extends Block {

    private int spawnDelay = 20;
    public HamisNestBlock() {
        super(BlockBehaviour.Properties.of()
                .strength(0.5f)
                .sound(SoundType.SLIME_BLOCK)
                .randomTicks()
        );
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos blockPos, BlockState blockState, boolean p_60519_) {

        if (state.getBlock() != blockState.getBlock()){
            SpawnHamis((ServerLevel) level,blockPos,2,5);
        }
        super.onRemove(state,level,blockPos,blockState,p_60519_);

    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource random) {
        if (!level.hasNearbyAlivePlayer(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 16)) {
            return;
        }
        AABB checkArea = new AABB(
                blockPos.getX(), blockPos.getY(),blockPos.getZ(),
                blockPos.getX()+7,blockPos.getY()-20,blockPos.getZ()+7
        );
        List<HamisEntity> list =level.getEntitiesOfClass(HamisEntity.class, checkArea);
        if (list.size() <13){
            SpawnHamis((ServerLevel) level,blockPos,1,3);
        }
    }

    private void SpawnHamis(ServerLevel level, BlockPos blockPos, int min, int max) {
        Random random = new Random();
        int amount = random.nextInt((max - min) + 1) + min;
        for (int i = 0; i<amount; i++){
            HamisEntity hamis = ModEntities.HAMIS.get().create(level);
            if(hamis != null){
                double x = blockPos.getX()+ 1 + (level.random.nextDouble()-0.5);
                double y = blockPos.getY() - 0.5;
                double z = blockPos.getZ()+ 1 + (level.random.nextDouble()-0.5);

                hamis.moveTo(x,y,z,level.random.nextFloat() * 360F,0);
                level.addFreshEntity(hamis);
            }
        }
    }
}
