pluginManagement {
	repositories {
		maven("https://maven.fabricmc.net/")
		gradlePluginPortal()
	}
}

val modName: String by settings
rootProject.name = modName
