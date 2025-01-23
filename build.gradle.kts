plugins {
	alias(libs.plugins.fabric.loom)
}

dependencies {
	minecraft(libs.minecraft)
	mappings(libs.fabric.yarn)
	modImplementation(libs.fabric.loader)
	modImplementation(libs.fabric.api)
}

fun String.toCase(sep: String) = replace("(?<=\\p{Lower})(?=\\p{Upper})".toRegex(), sep).lowercase()

val modGroup: String by project
val modName: String by project
val modVersion: String by project
val modDescription: String by project

val modId = modName.toCase("-")

group = modGroup
version = "${modVersion}+${libs.versions.minecraft.get()}"
description = modDescription
base.archivesName = "${modId}-fabric"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(libs.versions.java.get())
	}
}

tasks {
	processResources {
		filesMatching(listOf("fabric.mod.json", "${modId}.mixins.json")) {
			expand(
				"mod" to mapOf(
					"name" to modName,
					"description" to modDescription,
					"version" to modVersion,
					"id" to modId,
					"pkg" to "${modGroup}.${modName.toCase("_")}",
				),
				"versions" to mapOf(
					"java" to libs.versions.java.get(),
					"minecraft" to libs.versions.minecraft.get(),
					"fabricLoader" to libs.versions.fabric.loader.get(),
					"fabricApi" to libs.versions.fabric.api.get(),
				)
			)
		}
	}

	jar {
		from("LICENSE") {
			rename {
				"LICENSE-${modId}"
			}
		}
	}

	withType<JavaCompile>().configureEach {
		options.encoding = "UTF-8"
	}
}
