package sem2_v2

import kotlin.system.exitProcess

sealed interface Command {
    fun isValid(): Boolean
}

sealed class CommandOption : Command {
    class CmdAdd(request: String) : Command {
        private var name = ""
        private var phone = ""
        private var email = ""
        private var params = request.split(" ")
        private val nameRegEx: Regex = Regex("""[\p{L}\p{N}_\s-]+""")
        private val phoneRegEx: Regex = Regex("""^\+?\d+[\d-]*${'$'}""")
        private val emailRegEx: Regex = Regex("""^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,}${'$'}""")


        override fun isValid(): Boolean {
            var i = 0
            if (params.size > 1) {
                while (i < params.size) {
                    when (params[i]) {
                        "add" -> {
                            if (params[i + 1].matches(nameRegEx))
                                name = params[i + 1]
                            else {
                                println("\u001B[31m    Некорректный формат имени\u001B[0m")
                                return false
                            }
                            i++
                        }
                        "phone" -> {
                            if (params[i + 1].matches(phoneRegEx))
                                phone = params[i + 1]
                            else {
                                println("\u001B[31m    Некорректный формат номера телефона\u001B[0m")
                            }
                            i++
                        }
                        "email" -> {
                            if (params[i + 1].matches(emailRegEx))
                                email = params[i + 1]
                            else {
                                println("\u001B[31m    Некорректный формат адреса эл. почты\u001B[0m")
                            }
                            i++
                        }
                    }
                    i++
                }
            } else return false
            return true
        }

        fun run(): Person? {
            if (name.isNotEmpty()) {
                val newPerson = Person(name)
                if (phone.isNotEmpty())
                    newPerson.phone.add(phone)
                if (email.isNotEmpty())
                    newPerson.email.add(email)
                println("    Запись $name успешно добавлена")
                return newPerson
            }
            return null
        }

        override fun toString(): String {
            return "add"
        }
    }


    class CmdShow(private val request: String) : CommandOption() {
        override fun isValid(): Boolean {
            return (request.split(" ").size == 1 && request == "show")
        }

        fun run(person: Person?) {
            if (person != null)
                println(person)
            else
                println("    \u001B[31mNot initialized! Ни одной записи ещё внесено не было.\u001B[0m")
        }

        override fun toString(): String {
            return "show"
        }
    }


    class CmdExit(private val request: String) : CommandOption() {
        override fun isValid(): Boolean {
            return (request.split(" ").size == 1 && request == "exit")
        }

        fun run() {
            println("    Выход из программы...")
            exitProcess(0)
        }

        override fun toString(): String {
            return "exit"
        }
    }


    class CmdHelp(private val request: String) : CommandOption() {
        override fun isValid(): Boolean {
            return (request.split(" ").size == 1 && request == "help")
        }

        fun run() {
            val result = StringBuilder()
            result.append("\u001B[37m")
            result.append("   Поддерживаемые команды: \n")
            result.append("       add <Имя> phone <Номер телефона>\n")
            result.append("       add <Имя> email <Адрес электронной почты>\n")
            result.append("       show\n")
            result.append("       help\n")
            result.append("       exit\n")
            result.append("   Обратите внимание на то, что:\n")
            result.append("       - <ПАРАМЕТРЫ> не должны быть пустыми, пишутся без скобок <>;\n")
            result.append("       - в имени должны использоваться буквы (кириллица, латиница) и цифры;\n")
            result.append("       - для разделения слов в имени используйте знак '_';\n")
            result.append("       - телефон должен состоять из цифр, дефисов и знаков скобок (), может начинаться со знака '+';\n")
            result.append("       - если хотя бы имя введено корректно, то запись добавляется;\n")
            result.append("       - команда show показывает последнюю введенную запись.\u001B[0m")
            println(result)
        }

        override fun toString(): String {
            return "help"
        }
    }
}