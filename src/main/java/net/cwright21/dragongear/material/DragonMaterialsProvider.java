package net.cwright21.dragongear.material;

import net.cwright21.dragongear.DragonGear;
import net.cwright21.dragongear.core.materials.IceAndFireMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import net.silentchaos512.gear.api.data.material.MaterialBuilder;
import net.silentchaos512.gear.api.data.material.MaterialsProviderBase;
import net.silentchaos512.gear.api.material.MaterialCraftingData;
import net.silentchaos512.gear.api.material.TextureType;
import net.silentchaos512.gear.api.property.NumberProperty;
import net.silentchaos512.gear.api.util.PartGearKey;
import net.silentchaos512.gear.core.BuiltinMaterials;
import net.silentchaos512.gear.gear.material.MaterialCategories;
import net.silentchaos512.gear.setup.gear.GearProperties;
import net.silentchaos512.gear.setup.gear.GearTypes;
import net.silentchaos512.gear.setup.gear.PartTypes;
import net.silentchaos512.gear.util.Const;
import net.silentchaos512.lib.util.Color;
import com.mojang.brigadier.Message;

import dev.architectury.registry.registries.RegistrySupplier;

import com.iafenvoy.iceandfire.data.DragonColor;
import com.iafenvoy.iceandfire.data.DragonType;
import com.iafenvoy.iceandfire.data.SeaSerpent;
import com.iafenvoy.iceandfire.data.TrollType;
import com.iafenvoy.iceandfire.item.ItemSeaSerpentScales;
import com.iafenvoy.iceandfire.registry.IafItems;



//import dev.architectury.registry.registries.RegistrySupplier;

import java.util.*;
public class DragonMaterialsProvider extends MaterialsProviderBase {
    public DragonMaterialsProvider(DataGenerator generator) {
        super(generator, DragonGear.NAMESPACE);
    }

	@Override
	protected Collection<MaterialBuilder<?>> getMaterials() {
		Collection<MaterialBuilder<?>> ret = new ArrayList<>();

        //example(ret); //example showing Iron and Bone materials
		addDragonBone(ret);
		addDragonSteel(ret);
		addDragonScales(ret);
		addTrollHides(ret);
		addShinyScales(ret);
		addDragonBloodCoatings(ret);
		addSeaSerpentScales(ret);
		addDeathWormChitin(ret);
		addMyrmexChitin(ret);
		addStymphalian(ret);
		addPixieWing(ret);
		addFangs(ret);
		
        return ret;
	}
    
    @SuppressWarnings("unused")
	private void example(Collection<MaterialBuilder<?>> ret) {
    	// Iron
        ret.add(MaterialBuilder.builtin(BuiltinMaterials.IRON)
                .craftingWithCommonRod(Tags.Items.INGOTS_IRON, MaterialCategories.METAL, MaterialCategories.INTERMEDIATE)
                .displayWithDefaultName(Color.VALUE_WHITE, TextureType.HIGH_CONTRAST)
                //main
                .mainStatsCommon(250, 15, 14, 20, 0.7f)
                .mainStatsHarvest(6)
                .mainStatsMelee(2, 1, 0.0f)
                .stat(PartGearKey.ofMain(GearTypes.AXE), GearProperties.ATTACK_SPEED, -0.1f)
                .stat(PartGearKey.ofMain(GearTypes.HOE), GearProperties.ATTACK_SPEED, 0f)
                .mainStatsRanged(1, 0.1f)
                .mainStatsProjectile(1.0f, 1.1f)
                .mainStatsArmor(2, 6, 5, 2, 0, 6) //15
                .trait(PartTypes.MAIN, Const.Traits.MALLEABLE, 3)
                //rod
                .trait(PartTypes.ROD, Const.Traits.FLEXIBLE, 2)
                //tip
                .stat(PartTypes.TIP, GearProperties.DURABILITY, 128, NumberProperty.Operation.ADD)
                .stat(PartTypes.TIP, GearProperties.ARMOR_DURABILITY, 4, NumberProperty.Operation.ADD)
                .harvestTierBuiltin(PartTypes.TIP)
                .stat(PartTypes.TIP, GearProperties.HARVEST_SPEED, 1, NumberProperty.Operation.ADD)
                .stat(PartTypes.TIP, GearProperties.ATTACK_DAMAGE, 1, NumberProperty.Operation.ADD)
                .stat(PartTypes.TIP, GearProperties.DRAW_SPEED, 0.2f, NumberProperty.Operation.ADD)
                .stat(PartTypes.TIP, GearProperties.RARITY, 8, NumberProperty.Operation.ADD)
                .trait(PartTypes.TIP, Const.Traits.MALLEABLE, 2)
                );
        
     // Bone
        ret.add(MaterialBuilder.builtin(BuiltinMaterials.BONE)
                .crafting(new MaterialCraftingData(
                        Ingredient.of(Items.BONE_BLOCK),
                        List.of(MaterialCategories.ORGANIC, MaterialCategories.BASIC),
                        List.of(),
                        Map.of(PartTypes.ROD.get(), Ingredient.of(Items.BONE)),
                        true
                ))
                .displayWithDefaultName(0xFCFBED, TextureType.LOW_CONTRAST)
                //main
                .mainStatsCommon(108, 4, 5, 8, 0.9f)
                .mainStatsHarvest(4)
                .mainStatsMelee(2, 1, 0.1f)
                .mainStatsRanged(1f, 0f, 0.9f, 1f)
                .mainStatsArmor(1, 2, 1, 1, 0, 1) //5
                .trait(PartTypes.MAIN, Const.Traits.CHIPPING, 2)
                //rod
                .stat(PartTypes.ROD, GearProperties.ATTACK_DAMAGE, 0.2f, NumberProperty.Operation.MULTIPLY_TOTAL)
                .trait(PartTypes.ROD, Const.Traits.FLEXIBLE, 2)
        );
    }
    
    private void addDragonBone(Collection<MaterialBuilder<?>> ret) {
        
    	// Dragon Bone
        ret.add(MaterialBuilder.simple(IceAndFireMaterials.DRAGON_BONE.getMaterial())
                .crafting(IafItems.DRAGON_BONE.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                .displayWithDefaultName(0xFCFBED, TextureType.LOW_CONTRAST)
                //main
                .mainStatsCommon(1660, 0, 10, 8, 1f)
                .mainStatsHarvest(9)
                .mainStatsMelee(4, 1, 0.1f)
                .mainStatsRanged(1.1f, 0f, 0.9f, 1.1f)
                .mainStatsArmor(3, 4, 4, 3, 0, 5)
                .trait(PartTypes.MAIN, Const.Traits.CHIPPING, 2)
                //rod
                .trait(PartTypes.ROD, Const.Traits.FLEXIBLE, 2)
        );
        // Witherbone
        ret.add(MaterialBuilder.simple(IceAndFireMaterials.WITHER_BONE.getMaterial())
                .crafting(IafItems.WITHERBONE.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED)
                .displayWithDefaultName(0x0e0c0f, TextureType.LOW_CONTRAST)
                //rod
                .stat(PartTypes.ROD, GearProperties.ATTACK_DAMAGE, 0.35f, NumberProperty.Operation.MULTIPLY_TOTAL)
                .trait(PartTypes.ROD, Const.Traits.BRITTLE, 2)
        );
    
    }
    
    private void addDragonSteel(Collection<MaterialBuilder<?>> ret) {
        
    	// lightning (more magic damage)
        ret.add(MaterialBuilder.simple(IceAndFireMaterials.LIGHTNING_DRAGONSTEEL.getMaterial())
                .crafting(IafItems.DRAGONSTEEL_LIGHTNING_INGOT.get(), MaterialCategories.METAL, MaterialCategories.ENDGAME )
                .displayWithDefaultName(0x3e2547, TextureType.LOW_CONTRAST)
                //main
                .mainStatsCommon(8000, 4, 20, 16, 1.1f)
                .mainStatsHarvest(12)
                .mainStatsArmor(7, 12, 9, 6, 6, 10)
                .mainStatsMelee(20, 12, 0.1f)
                .mainStatsRanged(1.30f, -0.2f, 0.8f, 0.9f)
                .trait(PartTypes.MAIN, Const.Traits.HEAVY, 1)
                .trait(PartTypes.MAIN, net.cwright21.dragongear.util.Const.Traits.DRAGON_PROTECTION, 2)
                .trait(PartTypes.MAIN, net.cwright21.dragongear.util.Const.Traits.VOLTAIC_BLOOD, 1)

        );
        
        // ice
        ret.add(MaterialBuilder.simple(IceAndFireMaterials.ICE_DRAGONSTEEL.getMaterial())
                .crafting(IafItems.DRAGONSTEEL_ICE_INGOT.get(), MaterialCategories.METAL, MaterialCategories.ENDGAME )
                .displayWithDefaultName(0x73c9e2, TextureType.LOW_CONTRAST)
                //main
                .mainStatsCommon(8000, 4, 20, 16, 1.1f)
                .mainStatsHarvest(12)
                .mainStatsArmor(7, 12, 9, 6, 6, 10)
                .mainStatsMelee(24, 8, 0.1f)
                .mainStatsRanged(1.30f, -0.2f, 0.8f, 0.9f)
                .trait(PartTypes.MAIN, Const.Traits.HEAVY, 1)
                .trait(PartTypes.MAIN, net.cwright21.dragongear.util.Const.Traits.DRAGON_PROTECTION, 2)
                .trait(PartTypes.MAIN, net.cwright21.dragongear.util.Const.Traits.FREEZING_BLOOD, 1)

        );
        
        // fire
        ret.add(MaterialBuilder.simple(IceAndFireMaterials.FIRE_DRAGONSTEEL.getMaterial())
                .crafting(IafItems.DRAGONSTEEL_FIRE_INGOT.get(), MaterialCategories.METAL, MaterialCategories.ENDGAME )
                .displayWithDefaultName(0xce4646, TextureType.LOW_CONTRAST)
                //main
                .mainStatsCommon(8000, 4, 20, 13, 1.1f)
                .mainStatsHarvest(12)
                .mainStatsArmor(7, 12, 9, 6, 6, 10)
                .mainStatsMelee(24, 8, 0.1f)
                .mainStatsRanged(1.30f, -0.2f, 0.8f, 0.9f)
                .trait(PartTypes.MAIN, Const.Traits.HEAVY, 1)
                .trait(PartTypes.MAIN, net.cwright21.dragongear.util.Const.Traits.DRAGON_PROTECTION, 2)
                .trait(PartTypes.MAIN, net.cwright21.dragongear.util.Const.Traits.BLAZING_BLOOD, 1)

        );
        
    }
    
    private void addDragonScales(Collection<MaterialBuilder<?>> ret) {
    	List<DragonColor> allDragons = DragonColor.values();
    	
    	for(DragonColor dragon : allDragons) {
    		if(dragon.dragonType() == DragonType.LIGHTNING) {
        		ret.add(MaterialBuilder.simple(IceAndFireMaterials.getColorMaterialInstance(IceAndFireMaterials.DRAGON_SCALE, dragon.name()))
                        .crafting(dragon.getScaleItem(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                        .displayWithDefaultName(dragon.color().getColor(), TextureType.LOW_CONTRAST)
                        //main
                        .mainStatsCommon(500, 4, 12, 8, 0.9f)
                        .mainStatsArmor(5, 9, 7, 5, 1.5f, 20)
                        .mainStatsMelee(4, 1, 0.1f)
                        .mainStatsRanged(1.1f, 0f, 0.9f, 1.1f)
                        .trait(PartTypes.MAIN, Const.Traits.FLEXIBLE, 1)
                        .trait(PartTypes.MAIN, net.cwright21.dragongear.util.Const.Traits.DRAGON_PROTECTION, 1)
                        .trait(PartTypes.MAIN, Const.Traits.FIREPROOF, 1)
                );
    		}
    		else if(dragon.dragonType() == DragonType.FIRE) {
        		ret.add(MaterialBuilder.simple(IceAndFireMaterials.getColorMaterialInstance(IceAndFireMaterials.DRAGON_SCALE, dragon.name()))
                        .crafting(dragon.getScaleItem(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                        .displayWithDefaultName(dragon.color().getColor(), TextureType.LOW_CONTRAST)
                        //main
                        .mainStatsCommon(500, 4, 12, 8, 0.9f)
                        .mainStatsArmor(5, 9, 7, 5, 2, 15)
                        .mainStatsMelee(4, 1, 0.1f)
                        .mainStatsRanged(1.1f, 0f, 0.9f, 1.1f)
                        .trait(PartTypes.MAIN, Const.Traits.FLEXIBLE, 1)
                        .trait(PartTypes.MAIN, net.cwright21.dragongear.util.Const.Traits.DRAGON_PROTECTION, 1)
                        .trait(PartTypes.MAIN, Const.Traits.FIREPROOF, 1)
                );
    		}
    		else if(dragon.dragonType() == DragonType.ICE) {
        		ret.add(MaterialBuilder.simple(IceAndFireMaterials.getColorMaterialInstance(IceAndFireMaterials.DRAGON_SCALE, dragon.name()))
                        .crafting(dragon.getScaleItem(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                        .displayWithDefaultName(dragon.color().getColor(), TextureType.LOW_CONTRAST)
                        //main
                        .mainStatsCommon(500, 4, 12, 8, 0.9f)
                        .mainStatsArmor(5, 9, 7, 5, 2, 15)
                        .mainStatsMelee(4, 1, 0.1f)
                        .mainStatsRanged(1.1f, 0f, 0.9f, 1.1f)
                        .trait(PartTypes.MAIN, Const.Traits.FLEXIBLE, 1)
                        .trait(PartTypes.MAIN, net.cwright21.dragongear.util.Const.Traits.DRAGON_PROTECTION, 1)
                        .trait(PartTypes.MAIN, Const.Traits.FIREPROOF, 1)
                );
    		}	
    	}
        
    
    }
    
    private void addTrollHides(Collection<MaterialBuilder<?>> ret) {
        ret.add(MaterialBuilder.simple(IceAndFireMaterials.TROLL_LEATHER_FROST.getMaterial())
                .crafting(TrollType.FROST.leather.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                .displayWithDefaultName(0xce4646, TextureType.LOW_CONTRAST)
                //main
                .mainStatsCommon(300, 4, 12, 8, 0.9f)
                .mainStatsArmor(5, 8, 6, 5, 2, 10)
                .trait(PartTypes.MAIN, Const.Traits.FLEXIBLE, 1)
                .trait(PartTypes.MAIN, net.cwright21.dragongear.util.Const.Traits.THICK_HIDE, 1)
                .trait(PartTypes.GRIP, Const.Traits.STURDY, 1)
                .trait(PartTypes.LINING, net.cwright21.dragongear.util.Const.Traits.THICK_HIDE, 1)
        );
    	
        ret.add(MaterialBuilder.simple(IceAndFireMaterials.TROLL_LEATHER_FOREST.getMaterial())
                .crafting(TrollType.FOREST.leather.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                .displayWithDefaultName(0xce4646, TextureType.LOW_CONTRAST)
                //main
                .mainStatsCommon(300, 4, 12, 8, 0.9f)
                .mainStatsArmor(5, 8, 6, 5, 2, 10)
                .trait(PartTypes.MAIN, Const.Traits.FLEXIBLE, 1)
                .trait(PartTypes.MAIN, net.cwright21.dragongear.util.Const.Traits.THICK_HIDE, 1)
                .trait(PartTypes.GRIP, Const.Traits.STURDY, 1)
                .trait(PartTypes.LINING, net.cwright21.dragongear.util.Const.Traits.THICK_HIDE, 1)
        );
    	
        ret.add(MaterialBuilder.simple(IceAndFireMaterials.TROLL_LEATHER_MOUNTAIN.getMaterial())
                .crafting(TrollType.MOUNTAIN.leather.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                .displayWithDefaultName(0xce4646, TextureType.LOW_CONTRAST)
                //main
                .mainStatsCommon(300, 4, 12, 8, 0.9f)
                .mainStatsArmor(5, 8, 6, 5, 2, 10)
                .trait(PartTypes.MAIN, Const.Traits.FLEXIBLE, 1)
                .trait(PartTypes.MAIN, net.cwright21.dragongear.util.Const.Traits.THICK_HIDE, 1)
                .trait(PartTypes.GRIP, Const.Traits.STURDY, 1)
                .trait(PartTypes.LINING, net.cwright21.dragongear.util.Const.Traits.THICK_HIDE, 1)
        );
    	
    }
    
    private void addShinyScales(Collection<MaterialBuilder<?>> ret) {
        ret.add(MaterialBuilder.simple(IceAndFireMaterials.SHINY_SCALES.getMaterial())
                .crafting(IafItems.SHINY_SCALES.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                .displayWithDefaultName(0xce4646, TextureType.LOW_CONTRAST)
                
                .stat(PartTypes.LINING, GearProperties.ENCHANTMENT_VALUE, 2f)
                .trait(PartTypes.LINING, Const.Traits.AQUATIC, 1)
        );
    }
    
    private void addDragonBloodCoatings(Collection<MaterialBuilder<?>> ret) {
    	ret.add(MaterialBuilder.simple(IceAndFireMaterials.FIRE_DRAGON_BLOOD.getMaterial())
                .crafting(IafItems.FIRE_DRAGON_BLOOD.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                .displayWithDefaultName(0xce4646, TextureType.LOW_CONTRAST)
                
                .stat(PartTypes.COATING, GearProperties.MAGIC_DAMAGE, 8f, NumberProperty.Operation.ADD)
            	.trait(PartTypes.COATING, net.cwright21.dragongear.util.Const.Traits.BLAZING_BLOOD, 1)
        );
    	
    	ret.add(MaterialBuilder.simple(IceAndFireMaterials.ICE_DRAGON_BLOOD.getMaterial())
                .crafting(IafItems.ICE_DRAGON_BLOOD.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                .displayWithDefaultName(0xce4646, TextureType.LOW_CONTRAST)
                
                .stat(PartTypes.COATING, GearProperties.MAGIC_DAMAGE, 8f, NumberProperty.Operation.ADD)
                .trait(PartTypes.COATING, net.cwright21.dragongear.util.Const.Traits.FREEZING_BLOOD, 1)
        );
    	
    	ret.add(MaterialBuilder.simple(IceAndFireMaterials.LIGHTNING_DRAGON_BLOOD.getMaterial())
                .crafting(IafItems.LIGHTNING_DRAGON_BLOOD.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                .displayWithDefaultName(0xce4646, TextureType.LOW_CONTRAST)
                
                .stat(PartTypes.COATING, GearProperties.MAGIC_DAMAGE, 8f, NumberProperty.Operation.ADD)
                .trait(PartTypes.COATING, net.cwright21.dragongear.util.Const.Traits.VOLTAIC_BLOOD, 1)
        );
    	
    }
    
    private void addSeaSerpentScales(Collection<MaterialBuilder<?>> ret) {
    	List<SeaSerpent> seaSerpents = SeaSerpent.values();
    	for(SeaSerpent serpent : seaSerpents) {
    		ret.add(MaterialBuilder.simple(IceAndFireMaterials.getColorMaterialInstance(IceAndFireMaterials.SEA_SERPENT_SCALES, serpent.getName()))
                    .crafting(serpent.scale.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                    .displayWithDefaultName(serpent.getColor().getColor(), TextureType.LOW_CONTRAST)
                    //main
                    .mainStatsCommon(300, 4, 12, 8, 0.9f)
                    .mainStatsArmor(4, 8, 7, 4, 2.5f, 12.5f)
                    .mainStatsMelee(4, 1, 0.1f)
                    .trait(PartTypes.MAIN, Const.Traits.FLEXIBLE, 1)
                    .trait(PartTypes.MAIN, Const.Traits.AQUATIC, 2)
            );
    	}
    }
    
    private void addDeathWormChitin(Collection<MaterialBuilder<?>> ret) {	
    	ret.add(MaterialBuilder.simple(IceAndFireMaterials.DEATH_WORM_CHITIN_YELLOW.getMaterial())
                .crafting(IafItems.DEATH_WORM_CHITIN_YELLOW.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                .displayWithDefaultName(0xce4646, TextureType.LOW_CONTRAST)
                //main
                .mainStatsCommon(220, 4, 12, 8, 0.9f)
                .mainStatsArmor(7, 12, 9, 6, 1, 10)
                .mainStatsMelee(4, 1, 0.1f)
                .mainStatsRanged(1.1f, 0f, 0.9f, 1.1f)
                .trait(PartTypes.MAIN, Const.Traits.BRITTLE, 1)
                .trait(PartTypes.MAIN, Const.Traits.FIREPROOF, 1)
        );
    	
        ret.add(MaterialBuilder.simple(IceAndFireMaterials.DEATH_WORM_CHITIN_WHITE.getMaterial())
                .crafting(IafItems.DEATH_WORM_CHITIN_WHITE.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                .displayWithDefaultName(0xce4646, TextureType.LOW_CONTRAST)
                //main
                .mainStatsCommon(220, 4, 12, 8, 0.9f)
                .mainStatsArmor(7, 12, 9, 6, 1, 10)
                .mainStatsMelee(4, 1, 0.1f)
                .mainStatsRanged(1.1f, 0f, 0.9f, 1.1f)
                .trait(PartTypes.MAIN, Const.Traits.BRITTLE, 1)
                .trait(PartTypes.MAIN, Const.Traits.FIREPROOF, 1)
        );
    	
        ret.add(MaterialBuilder.simple(IceAndFireMaterials.DEATH_WORM_CHITIN_RED.getMaterial())
                .crafting(IafItems.DEATH_WORM_CHITIN_RED.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                .displayWithDefaultName(0xce4646, TextureType.LOW_CONTRAST)
                //main
                .mainStatsCommon(220, 4, 12, 8, 0.9f)
                .mainStatsArmor(3, 7, 5, 2, 1.5f, 12)
                .mainStatsMelee(4, 1, 0.1f)
                .mainStatsRanged(1.1f, 0f, 0.9f, 1.1f)
                .trait(PartTypes.MAIN, Const.Traits.BRITTLE, 1)
                .trait(PartTypes.MAIN, Const.Traits.FIREPROOF, 1)
        );
    }
    
    private void addMyrmexChitin(Collection<MaterialBuilder<?>> ret) {
    	//myrmex removed from IafCE pending rework 
    	return;
    }
    private void addStymphalian(Collection<MaterialBuilder<?>> ret) {
    	
    	ret.add(MaterialBuilder.simple(IceAndFireMaterials.STYMPHALIAN_FEATHER.getMaterial())
                .crafting(IafItems.STYMPHALIAN_BIRD_FEATHER.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                .displayWithDefaultName(0xce4646, TextureType.LOW_CONTRAST)
                
                .stat(PartTypes.FLETCHING, GearProperties.PROJECTILE_SPEED, 1.1f, NumberProperty.Operation.MULTIPLY_TOTAL)
                .trait(PartTypes.FLETCHING, net.cwright21.dragongear.util.Const.Traits.STYMPHALIAN, 1)
        );
    }
    
    private void addPixieWing(Collection<MaterialBuilder<?>> ret) {
    	ret.add(MaterialBuilder.simple(IceAndFireMaterials.PIXIE_WINGS.getMaterial())
                .crafting(IafItems.PIXIE_WINGS.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                .displayWithDefaultName(0xce4646, TextureType.LOW_CONTRAST)
                
                .stat(PartTypes.FLETCHING, GearProperties.PROJECTILE_ACCURACY, 1.1f, NumberProperty.Operation.MULTIPLY_TOTAL)
                .trait(PartTypes.FLETCHING, Const.Traits.CURSED, 1)
                .trait(PartTypes.FLETCHING, net.cwright21.dragongear.util.Const.Traits.LEVITATE, 2)
        );
    }
    
    private void addFangs(Collection<MaterialBuilder<?>> ret) {
    	ret.add(MaterialBuilder.simple(IceAndFireMaterials.HYRDRA_FANG.getMaterial())
                .crafting(IafItems.HYDRA_FANG.get(), MaterialCategories.ORGANIC, MaterialCategories.ENDGAME )
                //.noProperties(PartTypes.MAIN)
                .displayWithDefaultName(0xce4646, TextureType.LOW_CONTRAST)
                .mainStatsRanged(0.9f, 1.1f, 1.2f, 1.0f)
                .trait(PartTypes.MAIN, Const.Traits.VENOM, 1)
                .trait(PartTypes.MAIN, net.cwright21.dragongear.util.Const.Traits.LIFESTEAL, 2)
                .trait(PartTypes.TIP, Const.Traits.VENOM, 1)
                .trait(PartTypes.TIP, net.cwright21.dragongear.util.Const.Traits.LIFESTEAL, 1)

        );
    	
    }
    
}