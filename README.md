# Mindustry integration for Archipelago (v0.0.2)
(https://github.com/ArchipelagoMW/Archipelago)

 This repo is a fork of Anuken's Mindustry (https://github.com/Anuken/Mindustry) and has been modified to be used with Archipelago. Visit their repo for more information.

 To generate a World, you will need to add the Mindustry World to your Archipelago folder. You can find the Mindustry World here -> (https://github.com/JohnMahglass/Archipelago-Mindustry)

 Please be aware that Linux build has not been tested yet.
 
### Version 0.0.2 changelog

- Removed unused feature to prevent confusion.
- Added client commands. To view available commands, use '/help'.
- Improved feedback given to player on their connection status when a change occurs.
- Updated Java Client to version 0.1.19.
- Added "Faster production", "Disable invasions" and "Death link" options when generating a game.
- The "Erekir" and "All" campaign are now available to choose when generating a game.

## What has been changed?

- Save data are separated from vanilla game so that playing Archipelago doesn't erase your vanilla saves. (You should still backup your saves as this is in developement)
- **Randomizer is working for Serpulo's campaign only at the moment.**
- **Currently, only the "Skip Tutorial" option has been implemented**, you can change the other option in the YAML but they will most likely make the game unstable right now.
- Most node from the research tree has been replaced with location checks.
- A "Victory" node has been added, researching this node will inform Archipelago that the player has finished their World. (A placeholder is being used as requirement to unlock this node at the moment)
- A new menu has been added in Settings to configure Archipelago's settings.
- You can use the chat to send messages to other client (If they support it).
- Use '/help' in the client to list all client commands.


## Setup guide.

### Windows
1. Download the latest release.

2. Extract the downloaded file in a directory.

3. Run "Mindustry.exe"

3. Go to Settings -> Archipelago and enter your game information to connect. (Or use the chat's client commands)

4. Have fun.

### Linux
	Linux build has not been tested yet.

## Known bugs

- Sometimes when unlocking a research from a new category, the selectable block UI will not update until you exit the sector and enter again or receive another item.

- Upon unlocking a node wich auto-unlock other nodes (Like the Tank Fabricator unlocking also the Stell node) The error icon will appear in the toast announcing the new research to the player.

- Selecting a starter inventory in your .yaml will fail the generation of the APworld.

## Report a bug.
You can report bugs that you find in the game's thread in the Archipelago Discord server, you can find the Discord invite on the Archipelago website. You can find the game's thread by searching "Mindustry" in the "future-game-design" section.