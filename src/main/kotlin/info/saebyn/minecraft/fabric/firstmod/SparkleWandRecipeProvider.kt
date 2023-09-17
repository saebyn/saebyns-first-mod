package info.saebyn.minecraft.fabric.firstmod

import info.saebyn.minecraft.fabric.firstmod.SaebynsFirstMod.SPARKLEWAND_ITEM
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder
import net.minecraft.item.Items
import net.minecraft.recipe.book.RecipeCategory
import java.util.function.Consumer

class SparkleWandRecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
    override fun generate(exporter: Consumer<RecipeJsonProvider>?) {
        ShapedRecipeJsonBuilder.create(
            RecipeCategory.COMBAT,
            SPARKLEWAND_ITEM
        )
            .pattern("  *").pattern(" / ").pattern("/  ")
            .input(' ', Blocks.AIR)
            .input('*', Items.STICK)
            .input('/', Items.DIAMOND)
            .offerTo(exporter)
    }
}