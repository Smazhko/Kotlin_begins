package tests

interface Command {
    fun isValid(): Boolean
    fun run(): Any?
}

sealed class CommandOption : Command {
    data class Add(val name: String, val phone: String, val email: String) : CommandOption() {
        override fun isValid(): Boolean {
            // Проверяем валидность команды add и введенных параметров
            return name.isNotEmpty() && phone.matches(Regex("\\+\\d{1,3}-\\d{3}-\\d{3}-\\d{2}-\\d{2}")) &&
                    email.matches(Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))
        }

        override fun run(): Any? {
            // Обрабатываем команду add
            return "Added $name with phone $phone and email $email"
        }
    }

    object Exit : CommandOption() {
        override fun isValid(): Boolean {
            // Команда exit всегда валидна
            return true
        }

        override fun run(): Any? {
            // Обрабатываем команду exit
            return "Exiting..."
        }
    }

    object Help : CommandOption() {
        override fun isValid(): Boolean {
            // Команда help всегда валидна
            return true
        }

        override fun run(): Any? {
            // Обрабатываем команду help
            return "Help message..."
        }
    }
}

fun readCommand(): CommandOption {
    println("Enter command:")
    val input = readLine() ?: ""
    val parts = input.split(" ")

    return when (parts.firstOrNull()) {
        "add" -> {
            if (parts.size == 5) {
                val name = parts[1]
                val phone = parts[3]
                val email = parts[4]
                CommandOption.Add(name, phone, email)
            } else {
                CommandOption.Help
            }
        }
        "exit" -> CommandOption.Exit
        else -> CommandOption.Help
    }
}

fun main() {
    while (true) {
        val command = readCommand()
        println("Received command: $command")
        if (command.isValid()) {
            val result = when (command) {
                is CommandOption.Add -> command.run()
                is CommandOption.Exit -> command.run()
                is CommandOption.Help -> command.run()
            }
            println("Result: $result")
            if (command is CommandOption.Exit) break
        } else {
            println("Invalid command! Use 'help' for available commands.")
        }
    }
}
