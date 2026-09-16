# Milk Bottle

[English](README.md) | [简体中文](README.zh-CN.md)

A NeoForge 1.21.1 mod that adds drinkable and throwable milk bottles for clearing status effects.

## Introduction

This mod adds two new items:

- **Milk Bottle** — a drinkable bottle. Drinking it clears all active status effects (like a vanilla milk bucket) and leaves behind an empty glass bottle.
- **Splash Milk Bottle** — a throwable bottle. On impact, it clears the active status effects of every affected mob within splash range (like a splash potion).

## Crafting Recipes

| Output | Recipe |
|--------|--------|
| Milk Bottle | Glass Bottle + Milk Bucket |
| Splash Milk Bottle | Milk Bottle + Gunpowder |

## Configuration

The mod provides a config file `milkbottle-common.toml` (adjustable via the in-game Mods screen config):

- **Max Effects Cleared (`maxEffectsCleared`)** — the maximum number of status effects cleared per use. `0` clears all.

## Requirements

- Minecraft **1.21.1**
- NeoForge **21.1.229** or newer

## Development

```bash
# Launch client/server test environments
gradlew runClient
gradlew runServer

# Build the mod, output to build/libs
gradlew build
```

## License

This project is licensed under the **MIT License**.

## Acknowledgements

- Based on the [MDK-1.21.1-ModDevGradle](https://github.com/NeoForgeMDKs/MDK-1.21.1-ModDevGradle) template.
- Community docs: https://docs.neoforged.net/
