package io.github.trainb0y.biomecolor

import io.github.trainb0y.biomecolor.mixin.BiomeMixin
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeEffects
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

object BiomeColor : ModInitializer {
    val LOGGER: Logger = LoggerFactory.getLogger("Biome Color Tweaks")

    override fun onInitialize(mod: ModContainer) {
		ClientTickEvents.START.register(ClientTickEvents.Start { client ->
			val biome = client.world?.getBiome(client.player?.blockPos)?.value() ?: return@Start
			(biome as BiomeMixin).effects = effectBuilderFrom(biome).grassColor(0xdeadbeef.toInt()).build()
		})
	}

	fun effectBuilderFrom(effects: BiomeEffects): BiomeEffects.Builder {
		val builder = BiomeEffects.Builder()
			.fogColor(effects.fogColor)
			.skyColor(effects.skyColor)
			.waterColor(effects.waterColor)
			.waterFogColor(effects.waterFogColor)
			.grassColorModifier(effects.grassColorModifier)

		effects.foliageColor.getOrNull()?.let { builder.foliageColor(it) }
		effects.grassColor.getOrNull()?.let { builder.grassColor(it) }
		effects.additionsSound.getOrNull()?.let { builder.additionsSound(it) }
		effects.loopSound.getOrNull()?.let { builder.loopSound(it) }
		effects.moodSound.getOrNull()?.let { builder.moodSound(it) }
		effects.music.getOrNull()?.let { builder.music(it) }
		effects.particleConfig.getOrNull()?.let { builder.particleConfig(it) }
		return builder
	}


	fun effectBuilderFrom(biome: Biome) = effectBuilderFrom(biome.effects)
}
