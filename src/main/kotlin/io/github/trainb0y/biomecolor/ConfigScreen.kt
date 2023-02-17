package io.github.trainb0y.biomecolor

import dev.isxander.yacl.api.Binding
import dev.isxander.yacl.api.ConfigCategory
import dev.isxander.yacl.api.Option
import dev.isxander.yacl.api.OptionGroup
import dev.isxander.yacl.api.YetAnotherConfigLib
import dev.isxander.yacl.gui.controllers.ColorController
import io.github.trainb0y.biomecolor.mixin.BiomeMixin
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.registry.RegistryKeys
import net.minecraft.text.Text
import java.awt.Color
import kotlin.jvm.optionals.getOrDefault

fun openConfigScreen(parent: Screen?): Screen {
	val builder = YetAnotherConfigLib.createBuilder()
		.title(Text.translatable("config.biomecolor.title"))
		.save {
			MinecraftClient.getInstance().worldRenderer.reload()
		}

	val client = MinecraftClient.getInstance()
	val category = ConfigCategory.createBuilder().name(Text.literal("test"))
	client.world?.registryManager?.get(RegistryKeys.BIOME)?.forEach { biome ->
		val group = OptionGroup.createBuilder().name(Text.literal("test2"))
		group.option(
			Option.createBuilder(Color::class.java)
				.name(Text.literal("foliage color"))
				.controller { option ->
					ColorController(option)
				}
				.binding(
					Binding.generic(
						Color(biome.foliageColor),
						{
							Color(biome.foliageColor)
						},
						{ color ->
							(biome as BiomeMixin).effects =
								BiomeColor.effectBuilderFrom(biome).foliageColor(color.rgb).build()
						}
					)
				)
				.build()
		)
		group.option(
			Option.createBuilder(Color::class.java)
				.name(Text.literal("grass color"))
				.controller { option ->
					ColorController(option)
				}
				.binding(
					Binding.generic(
						Color(biome.effects.grassColor.getOrDefault(0)),
						{
							Color(biome.effects.grassColor.getOrDefault(0))
						},
						{ color ->
							(biome as BiomeMixin).effects =
								BiomeColor.effectBuilderFrom(biome).grassColor(color.rgb).build()
						}
					)
				)
				.build()
		)
		group.option(
			Option.createBuilder(Color::class.java)
				.name(Text.literal("sky color"))
				.controller { option ->
					ColorController(option)
				}
				.binding(
					Binding.generic(
						Color(biome.effects.skyColor),
						{
							Color(biome.effects.skyColor)
						},
						{ color ->
							(biome as BiomeMixin).effects =
								BiomeColor.effectBuilderFrom(biome).skyColor(color.rgb).build()
						}
					)
				)
				.build()
		)
		group.option(
			Option.createBuilder(Color::class.java)
				.name(Text.literal("fog color"))
				.controller { option ->
					ColorController(option)
				}
				.binding(
					Binding.generic(
						Color(biome.effects.fogColor),
						{
							Color(biome.effects.fogColor)
						},
						{ color ->
							(biome as BiomeMixin).effects =
								BiomeColor.effectBuilderFrom(biome).fogColor(color.rgb).build()
						}
					)
				)
				.build()
		)
		group.option(
			Option.createBuilder(Color::class.java)
				.name(Text.literal("water color"))
				.controller { option ->
					ColorController(option)
				}
				.binding(
					Binding.generic(
						Color(biome.effects.waterColor),
						{
							Color(biome.effects.waterColor)
						},
						{ color ->
							(biome as BiomeMixin).effects =
								BiomeColor.effectBuilderFrom(biome).waterColor(color.rgb).build()
						}
					)
				)
				.build()
		)
		group.option(
			Option.createBuilder(Color::class.java)
				.name(Text.literal("water fog color"))
				.controller { option ->
					ColorController(option)
				}
				.binding(
					Binding.generic(
						Color(biome.effects.waterFogColor),
						{
							Color(biome.effects.waterFogColor)
						},
						{ color ->
							(biome as BiomeMixin).effects =
								BiomeColor.effectBuilderFrom(biome).waterFogColor(color.rgb).build()
						}
					)
				)
				.build()
		)
		category.group(group.build())
	}


	builder.category(category.build())
	return builder.build().generateScreen(parent)
}
