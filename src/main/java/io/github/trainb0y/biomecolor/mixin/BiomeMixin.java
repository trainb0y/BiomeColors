package io.github.trainb0y.biomecolor.mixin;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;


@Mixin(Biome.class)
public interface BiomeMixin {
	@Mutable
	@Accessor("effects")
	void setEffects(BiomeEffects effects);

	@Accessor("effects")
	BiomeEffects getEffects();
}
