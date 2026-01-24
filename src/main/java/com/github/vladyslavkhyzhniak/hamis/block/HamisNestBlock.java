package com.github.vladyslavkhyzhniak.hamis.block;

import com.github.vladyslavkhyzhniak.hamis.entity.HamisEntity;
import com.github.vladyslavkhyzhniak.hamis.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class HamisNestBlock extends Block {

    public HamisNestBlock() {
        super(BlockBehaviour.Properties.of()
                .strength(0.5f)
                .sound(SoundType.SLIME_BLOCK)
        );
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos blockPos, BlockState blockState, boolean p_60519_) {

        if (state.getBlock() != blockState.getBlock()){
            spawnHamisFamily((ServerLevel) level, blockPos);
        }
        super.onRemove(state,level,blockPos,blockState,p_60519_);

    }

    private void spawnHamisFamily(ServerLevel level, BlockPos blockPos) {
        int count = level.random.nextInt(3) +3;
        for (int i = 0; i<count; i++){
            HamisEntity hamis = ModEntities.HAMIS.get().create(level);
            if(hamis != null){
                double x = blockPos.getX()+ 1 + (level.random.nextDouble()-0.5);
                double y = blockPos.getY()+ 1;
                double z = blockPos.getZ()+ 1 + (level.random.nextDouble()-0.5);

            hamis.moveTo(x,y,z,level.random.nextFloat() * 360F,0);
            level.addFreshEntity(hamis);
            }
        }
    }
}
