# Bilking

Milk all the mobs, and some players too. Integrates with [my fork of Wildfire's Female Gender mod](https://github.com/jlortiz0/WildfireFemaleGenderMod) to give different kinds of milk depending on the player's visible attributes. Without the mod, all players will be treated as female.

## Bugs

This is not compatible with [Milk All The Mobs](https://www.curseforge.com/minecraft/mc-mods/milk-all-the-mobs) and using them together may result in losing your bucket.

The kind of milk is not preserved if the milk is placed down using a mod usch as [Milk+](https://modrinth.com/mod/milk_plus).

## Usage

Right click on some mobs or players with an empty bucket to give yourself milk. The milk has different names depending on the source, stored in the damage value. In singleplayer, you can milk yourself by looking into the distance and sneak-right clicking with an empty bucket.

If you want to use a specific type of milk in crafting, use the following as your item
```json
{
  "item": "minecraft:milk_bucket",
  "data": n
}
```
where n is the ordinal of a `BilkType`. A value of 0 corresponds to standard cow milk.

## License

This mod is under the MIT license. You may use it in your own modpacks. Just know that your players will probably think you're weird for including this mod unless they're the same kind of degenerates as the group I made this for.
