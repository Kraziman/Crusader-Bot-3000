# Discord Server Stats Tracker

A Discord bot that automatically updates server statistics in voice channels.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [How to Download](#how-to-download)
- [How to Use](#how-to-use)
  - [Running the Bot](#running-the-bot)
  - [Commands](#commands)
- [Security Notice](#security-notice)
- [License](#license)

## Features

- Real-time server statistics displayed in voice channels:
  - 👥 Total members
  - 🟢 Online members
  - 🤖 Bot count
- Slash command integration (`/ping`, `/members`, etc.)
- Text command support (`!help`, `!uptime`)
- Automatic updates every 5 seconds

## Technologies Used

- [Java](https://www.oracle.com/java/)
- [JDA (Java Discord API)](https://github.com/DV8FromTheWorld/JDA)
- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

## How to Download

Before you proceed, ensure that you have JDK 17 or higher installed. You can download JDK 17 [here](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or [click here](https://download.oracle.com/java/17/archive/jdk-17.0.10_windows-x64_bin.exe) for the Windows installer.

### If you have Git installed:

```bash
git clone https://github.com/yourusername/discord-stats-bot.git
cd discord-stats-bot
```

### If you don't have Git installed:

1. Click on the "Code" button on GitHub.
2. Choose "Download ZIP" from the dropdown.
3. Extract the ZIP file to a folder of your choice.

## How to Use

### Running the Bot

#### Step 1: Configure the Bot

Open `Main.java` and replace the placeholder with your actual Discord bot token:

```java
String token = "your_bot_token_here"; // ← Replace this with your token
```

#### Step 2: Run the Bot

**Using IntelliJ IDEA:**

- Right-click `Main.java` → Run

**Using Command Line:**

```bash
javac src/com/CrusaderBot3000/*.java
java -cp src com.CrusaderBot3000.Main
```

The bot will automatically create a `📊 Server Stats` category with voice channels for each stat.

### Commands

| Command     | Description            | Example   |
|-------------|------------------------|-----------|
| `/ping`     | Check bot latency      | `/ping`   |
| `/members`  | Show total members     | `/members`|
| `/uptime`   | Display bot uptime     | `/uptime` |
| `/invite`   | Get server invite link | `/invite` |
| `!help`     | Show text help         | `!help`   |

## Security Notice

⚠️ **Never commit your bot token to GitHub** ⚠️
- For production, consider using **environment variables** or an external config file.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
