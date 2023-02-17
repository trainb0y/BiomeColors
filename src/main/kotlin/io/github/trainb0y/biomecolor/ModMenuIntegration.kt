package io.github.trainb0y.biomecolor

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import net.minecraft.client.gui.screen.Screen


class ModMenuIntegration : ModMenuApi {
	override fun getModConfigScreenFactory(): ConfigScreenFactory<*> {
		return ConfigScreenFactory { parent: Screen? -> openConfigScreen(parent) }
	}
}
