package info.saebyn.minecraft.fabric.firstmod

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class SparkleWandItem(settings: Settings?) : Item(settings) {
    // handle item use on the client
    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if (world.isClient) {
            // spawn particle where the player is
            world.addParticle(
                SaebynsFirstMod.SPARKLE,
                user.x,
                user.y,
                user.z,
                0.0,
                0.0,
                0.0
            )
        }
        return TypedActionResult.success(user.getStackInHand(hand))
    }
}