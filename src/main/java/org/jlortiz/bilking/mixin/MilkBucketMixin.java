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

	private static final BilkType[] names = BilkType.values();

	@Override
	public Text getName(ItemStack is) {
		if (!is.hasNbt() || !is.getNbt().contains("bilk.type")) {
			if (is.getDamage() != 0) {
				is.getOrCreateNbt().putInt("bilk.type", is.getDamage());
				is.setDamage(0);
			} else {
				return super.getName(is);
			}
		}
		int damage = is.getNbt().getInt("bilk.type");
		if (damage >= names.length) {
			return super.getName(is);
		}
		BilkType bt = names[damage];
		if (bt.shouldRecordOrigin() && is.getNbt().contains("bilk.origin")) {
			return new LiteralText(is.getNbt().getString("bilk.origin")).append("'s ").append(bt.getName()).append(" Bucket");
		}
		return new LiteralText(bt.getName()).append(" Bucket");
	}
}
