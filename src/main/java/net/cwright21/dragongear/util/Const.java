package net.cwright21.dragongear.util;

import net.cwright21.dragongear.DragonGear;
import net.minecraft.resources.ResourceLocation;
import net.silentchaos512.gear.api.util.DataResource;
import net.silentchaos512.gear.gear.trait.Trait;

public final class Const {
	
	public static final class Traits {
        public static final DataResource<Trait> THICK_HIDE = DataResource.trait(modId("thick_hide"));
        public static final DataResource<Trait> DRAGON_PROTECTION = DataResource.trait(modId("dragon_protection"));
        public static final DataResource<Trait> STYMPHALIAN = DataResource.trait(modId("stymphalian"));
        public static final DataResource<Trait> BLAZING_BLOOD = DataResource.trait(modId("blazing_blood"));
        public static final DataResource<Trait> FREEZING_BLOOD = DataResource.trait(modId("freezing_blood"));
        public static final DataResource<Trait> VOLTAIC_BLOOD = DataResource.trait(modId("voltaic_blood"));
        public static final DataResource<Trait> LIFESTEAL = DataResource.trait(modId("lifesteal"));
        //public static final DataResource<Trait> LIGHTNING_RESISTANT = DataResource.trait(modId("lightning_resistant"));
	}
	
	private static ResourceLocation modId(String path) {
        return ResourceLocation.fromNamespaceAndPath(DragonGear.NAMESPACE, path);
    }
	
}
