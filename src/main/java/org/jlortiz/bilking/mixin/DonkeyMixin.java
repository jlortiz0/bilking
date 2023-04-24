package org.jlortiz.bilking.mixin;

import net.minecraft.entity.passive.AbstractDonkeyEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractDonkeyEntity.class)
public class DonkeyMixin {
    @Inject(at=@At("HEAD"), method="interactMob", cancellable = true)
    private void interactMilk(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.getItem() == Items.BUCKET) {
            ActionResult r = itemStack.getItem().useOnEntity(itemStack, player, (AbstractDonkeyEntity) (Object) this, hand);
            if (r.isAccepted()) {
                cir.setReturnValue(r);
            }
        }
    }
}
