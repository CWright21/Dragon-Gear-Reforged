package net.cwright21.dragongear.event.entity.projectile;

import net.cwright21.dragongear.DragonGear;
import net.cwright21.dragongear.util.Const;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.silentchaos512.gear.entity.projectile.GearArrowEntity;
import net.silentchaos512.gear.util.TraitHelper;
public class ProjectileEvent {

	
	public static void onArrowLoose(EntityJoinLevelEvent event) {
		Entity entity = event.getEntity();
	    if (!(entity instanceof GearArrowEntity arrow) || event.getLevel().isClientSide) {
	        return;
	    }

	    if (arrow.getOwner() instanceof LivingEntity shooter) {
	    	ItemStack ammo = shooter.getProjectile(shooter.getMainHandItem());
	    	if(TraitHelper.hasTrait(ammo, Const.Traits.STYMPHALIAN)) {
	    		arrow.setNoGravity(true);
	    		arrow.getPersistentData().putBoolean("stymphalianDGRArrow", true);
	    	}
	    	if(TraitHelper.hasTrait(ammo, Const.Traits.LIFESTEAL)) {
	    		int lifestealLevel = TraitHelper.getTraitLevel(ammo,  Const.Traits.LIFESTEAL);
	    		arrow.setNoGravity(true);
	    		arrow.getPersistentData().putByte("lifestealDGR", (byte)lifestealLevel);
	    	}
	    }
	    
	    
	}
	
	public static void onArrowTick(EntityTickEvent event) {
		
	    Entity entity = event.getEntity();
	    
	    if(entity instanceof AbstractArrow arrow) {
	    	if(arrow.getPersistentData().getBoolean("stymphalianDGRArrow")) {
	    		int ticks = arrow.getPersistentData().getInt("floatTicks");
		    	Vec3 vel = arrow.getDeltaMovement();
		    	double combinedVel = Math.abs(vel.x) + Math.abs(vel.z);
	            arrow.getPersistentData().putInt("floatTicks", ticks + 1);
	            
	            if (ticks > 60 || combinedVel < 2f) { // after 3 seconds
	                arrow.setNoGravity(false);
	                arrow.getPersistentData().remove("stymphalianDGRArrow");
	            }
	    	}
	    }
	    else {
	    	return;
	    }
	}

}
