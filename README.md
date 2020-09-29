# SimpleChat v1.1
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-red)](https://github.com/willperes/SimpleChat/blob/master/LICENSE.md) [![Spigot](https://img.shields.io/badge/Spigot%20Page-English-red)](https://www.spigotmc.org/resources/simplechat.84282/)

I created this plugin just to test some stuff. I am still a beginner in Java, and I still have a lot of stuff to learn, so I started to practice a bit how to code using the Spigot library

The plugin only has one command, that I will show in a bit. The main functions of the plugin are:

- Local chat: The player will be able to send local messages to players that are up to 25 blocks far from you (this value can be changed in the plugin folder file named config.yml).
- Global chat: The player will be able to use the command /g (message) to send messages to all online players.

## Important
To use this plugin, you need to install the Vault plugin as well. It is needed, if you don't install Vault, the plugin won't work.

Link to Vault: https://www.spigotmc.org/resources/vault.34315/

## Commands
There is only one command so far:

- /g (message)

## Permissions

- simplechat.local.use - allow the player or group to use the local chat
- simplechat.global.use - allow the player or group to use the global chat

## Group prefix

To show a group prefix, you will need a permissions plugin that allows you to create groups and add a prefix to it.

The plugin was tested using LuckPerms (https://www.spigotmc.org/resources/luckperms.28140/), but you can use any other permissions plugin, as long as the permissions plugin
can interact with Vault.

## Local Chat
When you send a message in the local chat, it will be shown like this:
Example with and without a group prefix.

![Imagem 1](https://i.imgur.com/XJOo5Fd.png)

### In case that there is no one in the local chat block radius:
![Imagem](https://i.imgur.com/ywGeAHB.png)

### If you want to change the block radius:
Go to the folder SimpleChat that will be generated in your plugins folder when you run the plugin for the first time, and open the config.yml file with any text editor.
Once you are inside the config.yml folder, just change the value to the one you want (the default value is 25)
![Imagem](https://i.imgur.com/oTRQR6U.png)

## Global Chat
When you send a message in the global chat (using the command: /g), it will be sent like this:
Example with and without a group prefix.

![Imagem 2](https://i.imgur.com/n3wzJKn.png)

## Colored messages and formatting
You can color and format your messages

**Example:**

- &aSim&2pl&9eC&3hat v.&61.1
- /g &aSim&2pl&9eC&3hat v.&61.1

![Imagem](https://i.imgur.com/dtqr2Ci.png)
![Imagem](https://i.imgur.com/Dt409w9.png)

### All colors and formatting available

![Imagem 4](https://i.imgur.com/YsSI3py.png)

- &k: random symbols, letters and numbers
- &r: reset formatting/color
- &l: bold
- &o: italic
- &n: underline
- &m: crossed out
