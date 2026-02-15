package net.cwright21.dragongear;

import net.cwright21.dragongear.util.Const;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.silentchaos512.gear.api.util.DataResource;

public class CustomLanguageProvider extends LanguageProvider {
	
    public CustomLanguageProvider(PackOutput output) {
        super(
            // Provided by the `GatherDataEvent.Client`.
            output,
            // Your mod id.
            DragonGear.NAMESPACE,
            // The locale to use. You may use multiple language providers for different locales.
            "en_us"
        );
    }
    
    @Override
    protected void addTranslations() {

        // Adds a trait translations to the datapack
        this.add(getPath(Const.Traits.THICK_HIDE, "trait"), "Thick Hide");
        this.add(getPath(Const.Traits.DRAGON_PROTECTION, "trait"), "Dragon Protection");
    }
    
    private String getPath(DataResource<?> dataResource, String prefix) {
    	return prefix + "." + dataResource.getId().getNamespace() + "." + dataResource.getId().getPath();
    }
    
}
