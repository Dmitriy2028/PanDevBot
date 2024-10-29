# Telegram Category Tree Bot

This project implements a Telegram bot that allows users to create, view, and delete a hierarchical category tree. The bot is built using Spring Boot, with category data stored in PostgreSQL.

## Key Features
- **/viewTree**: Displays the category tree in a structured format.
- **/addElement \<element name>**: Adds a new root element if no parent is specified.
- **/addElement \<parent> \<child>**: Adds a child element to an existing parent. If the parent element doesn’t exist, an error message is returned.
- **/removeElement \<element name>**: Deletes the specified element and all its children. Returns an error message if the element is not found.
- **/help**: Lists all available commands and their descriptions.

## Getting Started

### Prerequisites
1. PostgreSQL database.
2. Docker (but you also could start app without it).

### Running the Application

To make testing and setup easier, Docker and Docker Compose configurations are included. Follow these commands to start the bot:

1. Build the Docker image:
   ```bash
   docker build -t pandev .

2. Start the bot and PostgreSQL using Docker Compose:
   ```bash
   docker-compose up -d

## Configuration
The bot’s configuration, including name and token, are defined in application.yml for local testing and application-docker.yml for Docker. You’ll need to set the following environment variables in docker-compose.yml:
   ```yaml
   TELEGRAM_BOT_NAME: "<your_bot_name>"
   TELEGRAM_BOT_TOKEN: "<your_bot_token>"
   ```
## Project Structure
- **TelegramBot**: Main bot class responsible for handling Telegram interactions.
- **CommandsHandler**: Processes bot commands using the Command pattern.
- **BotProperties**: Loads bot-specific configurations from the application configuration.
- **Category**: Entity representing a category in the category tree, stored in PostgreSQL.