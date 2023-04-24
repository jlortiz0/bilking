package org.jlortiz.bilking.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.jlortiz.bilking.BilkType;
import org.jlortiz.bilking.Bilking;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(MilkBucketItem.class)
public class MilkBucketMixin extends Item {
	public MilkBucketMixin(Item.Settings settings) {
		super(settings);
	}

	private static BilkType[] names = BilkType.values();

	@Override
	public Text getName(ItemStack is) {
//		if (!is.hasNbt() || !is.getNbt().contains("bilk.type")) {
//			return super.getName(is);
//		}
		// int damage = is.getNbt().getInt("bilk.type");
		int damage = is.getDamage();
		if (damage >= names.length) {
			return super.getName(is);
		}
		return new LiteralText(names[damage].getName()).append(" Bucket");
	}
}
