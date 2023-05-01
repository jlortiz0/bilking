package org.jlortiz.bilking.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.jlortiz.bilking.BilkType;
import org.jlortiz.bilking.Bilking;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BucketItem.class)
public class BucketMixin extends Item {
    @Shadow
    private Fluid fluid;

    public BucketMixin(Fluid fluid, Item.Settings settings) {
        super(settings);
        this.fluid = fluid;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.getItem() == Items.BUCKET) {
            BilkType damage = Bilking.canMilk(entity);
            if (damage != null) {
                user.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
                ItemStack itemStack2 = Items.MILK_BUCKET.getDefaultStack();
                itemStack2.setDamage(damage.ordinal());
                if (!user.isCreative()) {
                    if (itemStack.getCount() == 1) {
                        user.setStackInHand(hand, itemStack2);
                    } else {
                        itemStack.decrement(1);
                        if (!user.getInventory().insertStack(itemStack2))
                            user.dropItem(itemStack2, false);
                    }
                }
                return ActionResult.success(user.getWorld().isClient);
            }
        }
        return super.useOnEntity(stack, user, entity, hand);
    }

    @Inject(at=@At("HEAD"), method="use", cancellable = true)
    private void useOnSelf(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (world.isClient || !world.getServer().isSingleplayer() || world.getServer().getCurrentPlayerCount() > 1 || !user.isSneaking()) return;
        BlockHitResult blockHitResult = raycast(world, user, RaycastContext.FluidHandling.NONE);
        if (blockHitResult.getType() == HitResult.Type.MISS) {
            ItemStack itemStack = user.getStackInHand(hand);
            ActionResult ar = this.useOnEntity(itemStack, user, user, hand);
            if (ar.isAccepted()) {
                cir.setReturnValue(TypedActionResult.success(user.getStackInHand(hand)));
            }
        }
    }
}
