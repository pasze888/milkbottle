# 奶瓶 (Milk Bottle)

---

## 中文

一个基于 **NeoForge 1.21.1** 的模组，添加了可饮用和可投掷的奶瓶，用于解除状态效果。

### 简介

本模组添加了两个物品：

- **奶瓶 (Milk Bottle)** — 可饮用的奶瓶。饮用后解除全部状态效果（与原版牛奶桶类似），并留下一个空的玻璃瓶。
- **喷溅奶瓶 (Splash Milk Bottle)** — 可投掷的奶瓶。击中后解除一定范围内所有生物的生效状态效果（类似喷溅药水）。

### 合成配方

| 产物 | 配方 |
|------|------|
| 奶瓶 | 玻璃瓶 + 牛奶桶 |
| 喷溅奶瓶 | 奶瓶 + 火药 |

### 配置

模组提供一个配置文件 `milkbottle-common.toml`（可在游戏内 Mods 界面的配置中调整）：

- **解除状态效果数量 (`maxEffectsCleared`)** — 奶瓶一次最多解除的状态效果数量。`0` 表示解除全部。

### 运行要求

- Minecraft **1.21.1**
- NeoForge **21.1.229** 或更高

### 开发构建

```bash
# 启动客户端/服务端测试
gradlew runClient
gradlew runServer

# 构建产物，输出到 build/libs
gradlew build
```

---

## English

A NeoForge 1.21.1 mod that adds drinkable and throwable milk bottles for clearing status effects.

### Introduction

This mod adds two new items:

- **Milk Bottle** — a drinkable bottle. Drinking it clears all active status effects (like a vanilla milk bucket) and leaves behind an empty glass bottle.
- **Splash Milk Bottle** — a throwable bottle. On impact, it clears the active status effects of every affected mob within splash range (like a splash potion).

### Crafting Recipes

| Output | Recipe |
|--------|--------|
| Milk Bottle | Glass Bottle + Milk Bucket |
| Splash Milk Bottle | Milk Bottle + Gunpowder |

### Configuration

The mod provides a config file `milkbottle-common.toml` (adjustable via the in-game Mods screen config):

- **Max Effects Cleared (`maxEffectsCleared`)** — the maximum number of status effects cleared per use. `0` clears all.

### Requirements

- Minecraft **1.21.1**
- NeoForge **21.1.229** or newer

### Development

```bash
# Launch client/server test environments
gradlew runClient
gradlew runServer

# Build the mod, output to build/libs
gradlew build
```

---

## License

This project is licensed under the **MIT License**.

## Acknowledgements

- Based on the [MDK-1.21.1-ModDevGradle](https://github.com/NeoForgeMDKs/MDK-1.21.1-ModDevGradle） template.
- Community docs: https://docs.neoforged.net/
