package info.saebyn.minecraft.fabric.firstmod

import info.saebyn.minecraft.fabric.firstmod.SaebynsFirstMod.SPARKLEWAND_ITEM
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder
import net.minecraft.item.Items
import net.minecraft.recipe.book.RecipeCategory
import java.util.function.Consumer

class SparkleWandRecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
    override fun generate(exporter: Consumer<RecipeJsonProvider>) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, SPARKLEWAND_ITEM, 1)
            .input('*', Items.DIAMOND)
            .input('/', Items.STICK)
            .pattern("  *")
            .pattern(" / ")
            .pattern("/  ")
            .criterion("has_item", conditionsFromItem(SPARKLEWAND_ITEM))
            .offerTo(exporter)

    }
}