package net.cwright21.dragongear.trait;

import net.cwright21.dragongear.DragonGear;
import net.cwright21.dragongear.util.Const;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.DataGenerator;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.common.Tags;
import net.silentchaos512.gear.SilentGear;
import net.silentchaos512.gear.api.data.trait.TraitBuilder;
import net.silentchaos512.gear.api.data.trait.TraitsProviderBase;
import net.silentchaos512.gear.core.SoundPlayback;
import net.silentchaos512.gear.gear.trait.effect.*;
import net.silentchaos512.gear.setup.SgBlocks;
import net.silentchaos512.gear.setup.SgTags;
import net.silentchaos512.gear.setup.gear.GearProperties;
import net.silentchaos512.gear.setup.gear.GearTypes;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CustomTraitsProvider extends TraitsProviderBase{

	
	public CustomTraitsProvider(DataGenerator generator) {
		super(generator, DragonGear.NAMESPACE);
	}

	public Collection<TraitBuilder> getTraits() {
		Collection<TraitBuilder> ret = new ArrayList<>();
		
		/*
		ret.add(TraitBuilder.of(net.silentchaos512.gear.util.Const.Traits.HEAVY, 5)
                .effects(AttributeTraitEffect.builder()
                        .build()
                )
                .withGearTypeCondition(GearTypes.ARMOR)
        );
		 */
		
		ret.add(TraitBuilder.of(Const.Traits.THICK_HIDE, 5)
                .withGearTypeCondition(GearTypes.ARMOR)
        );
		
		ret.add(TraitBuilder.of(Const.Traits.DRAGON_PROTECTION, 5)
                .withGearTypeCondition(GearTypes.ARMOR)
        );
		
		ret.add(TraitBuilder.of(Const.Traits.STYMPHALIAN, 1)
                .withGearTypeCondition(GearTypes.ARROW)
        );
		
		ret.add(TraitBuilder.of(Const.Traits.BLAZING_BLOOD, 1)
                .withGearTypeCondition(GearTypes.WEAPON, GearTypes.NONE)
        );
		
		ret.add(TraitBuilder.of(Const.Traits.FREEZING_BLOOD, 1)
                .withGearTypeCondition(GearTypes.WEAPON, GearTypes.NONE)
        );
		ret.add(TraitBuilder.of(Const.Traits.VOLTAIC_BLOOD, 1)
                .withGearTypeCondition(GearTypes.WEAPON, GearTypes.NONE)
        );
		ret.add(TraitBuilder.of(Const.Traits.LIFESTEAL, 5)
                .withGearTypeCondition(GearTypes.ARROW, GearTypes.SPEAR)
        );
		
		return ret;
	}
	
}
