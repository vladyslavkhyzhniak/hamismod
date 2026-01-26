package com.github.vladyslavkhyzhniak.hamis.block;
import com.github.vladyslavkhyzhniak.hamis.entity.HamisEntity;
import com.github.vladyslavkhyzhniak.hamis.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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

    public HamisNestBlock() {
        super(BlockBehaviour.Properties.of()
                .strength(0.5f)
                .sound(SoundType.SLIME_BLOCK)
                .randomTicks()
        );
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos blockPos, BlockState blockState, boolean isMoving) {
        if (state.getBlock() != blockState.getBlock()) {
            if (level instanceof ServerLevel serverLevel) {
                SpawnHamis(serverLevel, blockPos, 3, 7);
            }
        }
        super.onRemove(state, level, blockPos, blockState, isMoving);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource random) {
        if (!level.hasNearbyAlivePlayer(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 16)) {
            return;
        }
        AABB checkArea = new AABB(blockPos).inflate(10);
        List<HamisEntity> list = level.getEntitiesOfClass(HamisEntity.class, checkArea);

        if (list.size() < 13) {
            SpawnHamis((ServerLevel) level, blockPos, 2, 3);
        }
    }

    private void SpawnHamis(ServerLevel level, BlockPos blockPos, int min, int max) {
        Random random = new Random();
        BlockPos bestSpot = findValidSpawnSpot(level, blockPos, random);

        if (bestSpot != null) {
            int amount = random.nextInt((max - min) + 1) + min;

            for (int i = 0; i < amount; i++) {
                HamisEntity hamis = ModEntities.HAMIS.get().create(level);
                if (hamis != null) {
                    double offsetX = (random.nextDouble() - 0.5) * 0.8;
                    double offsetZ = (random.nextDouble() - 0.5) * 0.8;
                    hamis.moveTo(
                            bestSpot.getX() + 0.5 + offsetX,
                            bestSpot.getY(),
                            bestSpot.getZ() + 0.5 + offsetZ,
                            random.nextFloat() * 360F,
                            0.0F
                    );
                    level.addFreshEntity(hamis);
                }
            }
        }
    }

    private BlockPos findValidSpawnSpot(ServerLevel level, BlockPos nestPos, Random random) {
        BlockPos above = nestPos.above();
        if (isSpotSafe(level, above)) {
            return above;
        }

        for (int i = 0; i < 10; i++) {
            int dx = random.nextInt(3) - 1;
            int dy = random.nextInt(2);
            int dz = random.nextInt(3) - 1;

            BlockPos candidate = nestPos.offset(dx, dy, dz);

            if (isSpotSafe(level, candidate)) {
                return candidate;
            }
        }

        return null;
    }

    private boolean isSpotSafe(ServerLevel level, BlockPos pos) {
        boolean isFree = level.getBlockState(pos).getCollisionShape(level, pos).isEmpty();
        boolean hasFloor = level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP);
        return isFree && hasFloor;
    }
}