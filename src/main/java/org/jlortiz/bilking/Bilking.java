package org.jlortiz.bilking;

import com.wildfire.api.WildfireAPI;
import com.wildfire.main.GenderPlayer;
import com.wildfire.main.WildfireGender;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.lwjgl.system.CallbackI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bilking implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
	}

	public static BilkType canMilk(LivingEntity e) {
		if (e instanceof PlayerEntity) {
			if (FabricLoader.getInstance().isModLoaded("wildfire_gender")) {
				GenderPlayer gender = WildfireAPI.getPlayerById(e.getUuid());
				if (gender.getBustSize() > 0.35) return BilkType.PLAYER_F;
				if (gender.getBulge().getSize() > 0.35) return BilkType.PLAYER_M;
				return BilkType.PLAYER;
			}
			return BilkType.PLAYER_F;
		}
		if (!(e instanceof AnimalEntity) || e.isBaby())
			return null;
		if (e instanceof SheepEntity) {
			return BilkType.SHEEP;
		} else if (e instanceof CowEntity) {
			return BilkType.COW;
		} else if (e instanceof LlamaEntity) {
			return BilkType.LLAMA;
		} else if (e instanceof PigEntity) {
			return BilkType.PIG;
		} else if (e instanceof HorseEntity) {
			return BilkType.HORSE;
		} else if (e instanceof DonkeyEntity) {
			return BilkType.DONKEY;
		} else if (e instanceof MuleEntity) {
			return BilkType.MULE;
		}
		return null;
	}

	public static ItemStack getBucket(LivingEntity e) {
		BilkType bt = canMilk(e);
		if (bt == null) {
			return null;
		}
		ItemStack is = Items.MILK_BUCKET.getDefaultStack();
		is.getOrCreateNbt().putInt("bilk.type", bt.ordinal());
		if (bt.shouldRecordOrigin()) {
			is.getNbt().putString("bilk.origin", e.getEntityName());
		}
		return is;
	}
}
