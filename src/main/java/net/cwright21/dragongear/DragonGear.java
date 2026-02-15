package net.cwright21.dragongear;

import net.cwright21.dragongear.event.entity.livingentity.LivingEntityEvent;
import net.cwright21.dragongear.event.entity.projectile.ProjectileEvent;
import net.cwright21.dragongear.material.DragonMaterialsProvider;
import net.cwright21.dragongear.trait.CustomTraitsProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iafenvoy.uranus.event.LivingEntityEvents;


@Mod(DragonGear.NAMESPACE)
@EventBusSubscriber(modid = DragonGear.NAMESPACE)
public class DragonGear {
    public static final String NAMESPACE = "dragongear";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAMESPACE);
    
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var gen = event.getGenerator();
        gen.addProvider(true, new DragonMaterialsProvider(gen));
        gen.addProvider(true, new CustomTraitsProvider(gen));
        //event.createProvider(CustomLanguageProvider::new);
        
        LivingEntityEvents.DAMAGE.register(LivingEntityEvent::applyCustomProtectionTraits);
        //LivingEntityEvents.DAMAGE.register(LivingEntityEvent::applyDragonCoatingEffect);
        
    }
    
    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
    	ProjectileEvent.onArrowLoose(event);
    }
    
    
    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Post event) {
    	ProjectileEvent.onArrowTick(event);
    }
    
    @SubscribeEvent
    public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
    	LivingEntityEvent.applyDragonCoatingEffect(event);
    	LivingEntityEvent.applyLifestealEffects(event);
    }
}