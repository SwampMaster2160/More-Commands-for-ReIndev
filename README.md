# More Commands for ReIndev

More commands mod for [ReIndev](https://reindev.miraheze.org/wiki/Reindev_Wiki) using [FoxLoader](https://github.com/Fox2Code/FoxLoader).

Made by SwampMaster2160 and currently singleplayer only.

Syntax:

* `<argument>`: Compulsory Argument
* `[argument]`: Optional Argument

Commands Added:

* `/kill [targets]`
* `/listentities [targets]`
* `/seed`
* `/setWorldSpawn [x y z]`
* `/summon <entity name or id> [x y z]`
* `/toggledownfall`
* `/worldInfo <variable> [new value]`

Commands Changed:

* `/tp [targets] <x> <y> <z>`
* `/give <targets> <item id/name>[:metadata] [count]`
* `/clear [targets] [item id/name][:metadata] [max items to remove]`

World Info Variables:

* `seed`
* `generator`
* `features`
* `spawnX`
* `spawnY`
* `spawnZ`
* `worldTime`
* `sizeOnDisk`
* `dimension`
* `raining`
* `thundering`
* `rainTime`
* `thunderTime`
* `gameType`
* `saveVersion`
* `cheats`
* `hardcore`
* `lastTimePlayed`
* `highscore`
* `highestChunkOverworld`
* `highestChunkNether`
* `lowestChunkOverworld`
* `lowestChunkNether`
* `worldName`
* `spawnPos`

Entity Target Selectors:

* `@e`
* `@a`
* `@s`
* `@p`
* `@r`
* `#<entity instance ID>`
* `%<entity ID/name>`

Operators for Entity Target Selections
* `()`
* `!`
* `&`
* `^`
* `|`