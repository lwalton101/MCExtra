package no.nicknotname.mcextra.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import no.nicknotname.mcextra.register.BlockRegister;
import no.nicknotname.mcextra.util.ExperienceCalculator;
import org.jetbrains.annotations.Nullable;

public class XPBankBlockEntity extends BlockEntity {

    private int xp = 0;

    public XPBankBlockEntity(BlockPos pos, BlockState state) {
        super(BlockRegister.XP_BANK_BLOCK_ENTITY, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putInt("xp_level", xp);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        xp = nbt.getInt("xp_level");
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    public static void tick(World world, BlockPos pos, BlockState state, XPBankBlockEntity be) {

    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void handleClick(PlayerEntity player) {
        int xpAdditionAmount = 5;
        if(player.isSneaking()){
            if(getXp() < xpAdditionAmount){
                player.sendMessage(Text.translatable("block.mcextra.xp_bank.bank_not_enough_xp"), true);
                return;
            }

            setXp(getXp() - xpAdditionAmount);
            player.addExperience(xpAdditionAmount);
            player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1,1);
            player.sendMessage(Text.translatable("block.mcextra.xp_bank.xpMessage", getXp()), true);
            markDirty();
        } else{
            if(ExperienceCalculator.LevelToPoints(player.experienceLevel) + MathHelper.floor(player.experienceProgress * (float)player.getNextLevelExperience()) < xpAdditionAmount){
                player.sendMessage(Text.translatable("block.mcextra.xp_bank.player_not_enough_xp", ExperienceCalculator.LevelToPoints(player.experienceLevel) + MathHelper.floor(player.experienceProgress * (float)player.getNextLevelExperience())), true);
                return;
            }
            setXp(getXp() + xpAdditionAmount);
            player.addExperience(xpAdditionAmount * -1);
            player.sendMessage(Text.translatable("block.mcextra.xp_bank.xpMessage", getXp()), true);
            markDirty();
        }


//        MCExtra.LOGGER.info("handleClick()");
//        int localXP = getXp();
//        int deltaAmount = 0;
//        if(player.isSneaking()){
//            deltaAmount = xpAdditionAmount;
//        } else{
//            deltaAmount = xpAdditionAmount * -1;
//        }
//        localXP += deltaAmount;
//        setXp(localXP);
//        if(getXp() < xpAdditionAmount - 1){
//            player.sendMessage(Text.translatable("block.mcextra.xp_bank.xpMessage", getXp()));
//            return;
//        }
//
//        player.addExperience(deltaAmount * -1);
//
//        player.sendMessage(Text.translatable("block.mcextra.xp_bank.xpMessage", getXp()));
    }
}
