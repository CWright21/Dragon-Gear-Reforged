package net.cwright21.dragongear.event.entity.livingentity;

import net.cwright21.dragongear.DragonGear;
import net.cwright21.dragongear.util.Const;

import com.iafenvoy.iceandfire.entity.util.IVillagerFear;
import com.iafenvoy.iceandfire.item.armor.ItemDragonSteelArmor;
import com.iafenvoy.iceandfire.item.armor.ItemScaleArmor;
import com.iafenvoy.iceandfire.item.armor.ItemTrollArmor;
import com.iafenvoy.iceandfire.registry.*;

import it.unimi.dsi.fastutil.doubles.DoubleDoubleImmutablePair;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.silentchaos512.gear.api.item.GearTool;
import net.silentchaos512.gear.util.TraitHelper;

import java.util.UUID;
import java.util.function.Predicate;

import javax.annotation.Nullable;

public class LivingEntityEvent {
    
	public static float applyCustomProtectionTraits(LivingEntity entity, DamageSource source, float amount) {
		
		//reduce projectile damage by 5% per level of Thick Hide
        if (source.is(net.minecraft.tags.DamageTypeTags.IS_PROJECTILE)) {
            int helmetThickHide = TraitHelper.getTraitLevel(entity.getItemBySlot(EquipmentSlot.HEAD), Const.Traits.THICK_HIDE);
            int chestThickHide = TraitHelper.getTraitLevel(entity.getItemBySlot(EquipmentSlot.HEAD), Const.Traits.THICK_HIDE);
            int legsThickHide = TraitHelper.getTraitLevel(entity.getItemBySlot(EquipmentSlot.HEAD), Const.Traits.THICK_HIDE);
            int feetThickHide = TraitHelper.getTraitLevel(entity.getItemBySlot(EquipmentSlot.HEAD), Const.Traits.THICK_HIDE);
            amount *= 1f - (helmetThickHide + chestThickHide + legsThickHide + feetThickHide) * 0.05f;
        }
        
        //reduce dragon breath damage by 5% per level of Dragon Protection (or all ender dragon damage)
        if (source.is(IafDamageTypes.DRAGON_FIRE_TYPE) || source.is(IafDamageTypes.DRAGON_ICE_TYPE) || source.is(IafDamageTypes.DRAGON_LIGHTNING_TYPE) || source.getEntity() instanceof EnderDragon) {
        	int helmetDragProt = TraitHelper.getTraitLevel(entity.getItemBySlot(EquipmentSlot.HEAD), Const.Traits.DRAGON_PROTECTION);
            int chestDragProt = TraitHelper.getTraitLevel(entity.getItemBySlot(EquipmentSlot.HEAD), Const.Traits.DRAGON_PROTECTION);
            int legsDragProt = TraitHelper.getTraitLevel(entity.getItemBySlot(EquipmentSlot.HEAD), Const.Traits.DRAGON_PROTECTION);
            int feetDragProt = TraitHelper.getTraitLevel(entity.getItemBySlot(EquipmentSlot.HEAD), Const.Traits.DRAGON_PROTECTION);
            amount *= 1f - (helmetDragProt + chestDragProt + legsDragProt + feetDragProt) * 0.05f;
        }
        
        return amount;
    }
	
	public static void applyDragonCoatingEffect(LivingIncomingDamageEvent event) {
        LivingEntity target = event.getEntity();
        if (target.level().isClientSide()) return;

        DamageSource source = event.getSource();
        @Nullable Entity attacker = source.getEntity();

        ItemStack weapon = attacker instanceof LivingEntity attackingLivingEntity
                ? attackingLivingEntity.getItemBySlot(EquipmentSlot.MAINHAND)
                : ItemStack.EMPTY;

        //DragonGear.LOGGER.info("sourceEntity.getMainHandItem(): " + weapon.toString());
        // Traits that effect the entity being attacked
        if (attacker instanceof Player attackingPlayer && weapon.getItem() instanceof GearTool) {
        	
        	//Blazing blood fire and knockback
        	if (TraitHelper.getTraitLevel(weapon,  Const.Traits.BLAZING_BLOOD) >= 1 ) {
             	//DragonGear.LOGGER.info("Hit " + target.getDisplayName() + " with a Blazing Blood tool");
             	double entityX = 0.0;
                double entityY = 0.0;
             	if (source.getDirectEntity() instanceof Projectile projectile) {
                    DoubleDoubleImmutablePair doubledoubleimmutablepair = projectile.calculateHorizontalHurtKnockbackDirection(target, source);
                    entityX = -doubledoubleimmutablepair.leftDouble();
                    entityY = -doubledoubleimmutablepair.rightDouble();
                } else if (source.getSourcePosition() != null) {
                	entityX = source.getSourcePosition().x() - target.getX();
                 	entityY = source.getSourcePosition().z() - target.getZ();
                }
             	target.knockback(0.5f, entityX, entityY);
             	target.setRemainingFireTicks(60);
             	target.setSharedFlagOnFire(true);
            }
        	
        	//cold freeze effect
        	if (TraitHelper.getTraitLevel(weapon,  Const.Traits.FREEZING_BLOOD) >= 1 ) {
                // 141 ticks = ~7 seconds. 
                // Note: At 140 ticks, the mob will start taking freeze damage automatically.
                target.setTicksFrozen(141);

                // 'setTicksFrozen' makes them shake, but they can still walk. 
                // To stop movement, apply Slowness 255 for 5 seconds (100 ticks).
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 255, false, false));
        	}
        	 
        	//lightning strike
        	if (TraitHelper.getTraitLevel(weapon,  Const.Traits.VOLTAIC_BLOOD) >= 1 ) {
        		if(source.getDirectEntity() instanceof LightningBolt) return;
        		
        		@Nullable ServerLevel serverLevel = target.level() instanceof ServerLevel ? (ServerLevel) target.level() : null;
                LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(serverLevel);
                
                if (lightning != null) {
                    lightning.moveTo(target.getX(), target.getY(), target.getZ());
                    
                    lightning.setCause((ServerPlayer) event.getSource().getEntity());

                    serverLevel.addFreshEntity(lightning);
                }
        	}	
        }    
	}
	
	public static void applyLifestealEffects(LivingIncomingDamageEvent event) {
    	LivingEntity target = event.getEntity();
        if (target.level().isClientSide()) return;

        DamageSource source = event.getSource();
        @Nullable Entity attacker = source.getEntity();

        ItemStack weapon = attacker instanceof LivingEntity attackingLivingEntity
                ? attackingLivingEntity.getItemBySlot(EquipmentSlot.MAINHAND)
                : ItemStack.EMPTY;

        //DragonGear.LOGGER.info("sourceEntity.getMainHandItem(): " + weapon.toString());
        if (attacker instanceof LivingEntity attackerLivingEntity && weapon.getItem() instanceof GearTool) {
        	if(source.getDirectEntity() instanceof AbstractArrow projectile) {
        		if(projectile.getPersistentData().getByte("lifestealDGR") > 0) {
        			int lifestealLevel = projectile.getPersistentData().getByte("lifestealDGR");
    				attackerLivingEntity.heal(event.getAmount() * (lifestealLevel * 0.05f));
        		}
        	}
        	else{
        		int lifestealLevel = TraitHelper.getTraitLevel(weapon,  Const.Traits.LIFESTEAL);
    			if( lifestealLevel >= 1 ) {
    				attackerLivingEntity.heal(event.getAmount() * (lifestealLevel * 0.05f));
    			}
        	}
        }
    }
	
	public static void applyPixieWingEffects(LivingIncomingDamageEvent event) {
		LivingEntity target = event.getEntity();
        if (target.level().isClientSide()) return;

        DamageSource source = event.getSource();
        @Nullable Entity attacker = source.getEntity();

        ItemStack weapon = attacker instanceof LivingEntity attackingLivingEntity
                ? attackingLivingEntity.getItemBySlot(EquipmentSlot.MAINHAND)
                : ItemStack.EMPTY;
        
        if (attacker instanceof LivingEntity attackerLivingEntity && weapon.getItem() instanceof GearTool) {
        	if(source.getDirectEntity() instanceof AbstractArrow projectile) {
        		if(projectile.getPersistentData().getByte("pixieDGR") > 0) {
        			int levitateLevel = projectile.getPersistentData().getByte("pixieDGR");
    				target.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60, levitateLevel-1));
        		}
        	}
        	else{
        		int levitateLevel = TraitHelper.getTraitLevel(weapon,  Const.Traits.LEVITATE);
    			if( levitateLevel >= 1 ) {
    				target.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60, levitateLevel-1));
    			}
        	}
        }
	}
	
	

	
}
