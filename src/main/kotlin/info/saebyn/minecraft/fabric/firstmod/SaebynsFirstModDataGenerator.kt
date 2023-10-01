package info.saebyn.minecraft.fabric.firstmod

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import org.slf4j.LoggerFactory

object SaebynsFirstModDataGenerator : DataGeneratorEntrypoint {
    private val logger = LoggerFactory.getLogger("SaebynsFirstModDataGenerator")

    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        logger.info("Initializing data generator")
        val pack = fabricDataGenerator.createPack()

        pack.addProvider { output: FabricDataOutput ->
            SparkleWandRecipeProvider(output)
        }
    }
}