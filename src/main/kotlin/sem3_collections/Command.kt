package sem3_collections

import kotlin.system.exitProcess

sealed interface Command


class CmdExit : Command {
    companion object {
        fun run() {
            println("    Выход из программы ... ")
            exitProcess(0)
        }
    }

    override fun toString(): String {
        return "exit"
    }
}


class CmdHelp : Command {
     companion object{
         fun run() {
             var result = StringBuilder()
             result.append("\u001B[37m")
             result.append("   Поддерживаемые команды: \n")
             result.append("       add <Имя> phone <Номер телефона>\n")
             result.append("       add <Имя> email <Адрес электронной почты>\n")
             result.append("       show <Имя>\n")
             result.append("       showall\n")
             result.append("       find <любая строка>\n")
             result.append("       help\n")
             result.append("       exit\n")
             result.append("   Обратите внимание на то, что:\n")
             result.append("       - <ПАРАМЕТРЫ> не должны быть пустыми, пишутся без скобок <>;\n")
             result.append("       - в имени должны использоваться буквы (кириллица, латиница) и цифры;\n")
             result.append("       - для разделения слов в имени используйте знак '_';\n")
             result.append("       - телефон должен состоять из цифр, дефисов и знаков скобок (), может начинаться со знака '+';\n")
             result.append("       - если вы хотите добавить сразу несколько номеров или email,\n")
             result.append("         перед каждым параметром используйте соответствующую команду:\n")
             result.append("         add <Имя> phone <Номер телефона> phone <Номер телефона>;\n")
             result.append("       - команда show выдаст только первый результат, ТОЧНО совпадающий с запросом;\n")
             result.append("       - showall выводит все записи из телефонной книги.\u001B[0m") // возврат к обычному
             println(result)
         }
     }

    override fun toString(): String {
        return "help"
    }
}


sealed interface Parameter {
    fun isValid(param: String): Boolean
}


object Name: Parameter {
    override fun isValid(param: String): Boolean {
        return param.matches(Regex("""[\p{L}\p{N}_\s-]+"""))
    }
}


object Phone: Parameter {
    override fun isValid(param: String): Boolean {
        return param.matches(Regex("""^\+?\d+[\d()-]*${'$'}"""))
    }
}


object Email: Parameter {
    override fun isValid(param: String): Boolean {
        return param.matches(Regex("""^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,}${'$'}"""))
    }
}


class CmdAdd(private var request: String) : Command {
    override fun toString(): String {
        return "add"
    }

    fun run(): Person? {
        var params = request.split(" ")

        var ADD_CMD = "add"
        var PHONE_CMD = "phone"
        var EMAIL_CMD = "email"
        lateinit var newPerson: Person

        var i = 0
        while (i < params.size) {
            val currCmd = params[i]
            val currValue = params[i + 1]
            when (currCmd) {
                ADD_CMD -> {
                    if (Name.isValid(currValue)) {
                        newPerson = Person(currValue.replace("_", " "))
                    }
                    else {
                        println("\u001B[31m    Некорректный формат имени \"$currValue\"\u001B[0m")
                        return null
                    }
                    i++
                }

                PHONE_CMD -> {
                    if (Phone.isValid(currValue))
                        newPerson.phones.add(currValue)
                    else {
                        println("\u001B[31m    Некорректный формат номера телефона \"$currValue\".\u001B[0m")
                    }
                    i++
                }

                EMAIL_CMD -> {
                    if (Email.isValid(currValue))
                        newPerson.emails.add(currValue)
                    else {
                        println("\u001B[31m    Некорректный формат адреса эл. почты \"$currValue\"\u001B[0m")
                    }
                    i++
                }
            }
            i++
        }
        return newPerson
    }
}


class CmdShow(private val dataBase: MutableList<Person>, private val request: String): Command {
    fun run() {
        if (request.split(" ").size > 1) {
            val name = request.split(" ")[1].replace("_", " ")
            if (dataBase.any { person -> person.name == name })
                println(dataBase.find { person -> person.name == name }.toString())
            else
                println("\u001B[31m    <<  !  >> Не могу найти \"\u001B[94m$name\u001B[31m\". Поиск имени в точном соответствии с запросом ничего не дал.\u001B[0m")
        }
        else
            println("\u001B[31m    <<  !  >> Ввод команды некорректный - команда введена без параметров.")
    }
}


class CmdShowAll(private val dataBase: MutableList<Person>): Command {
    fun run() {
        if (dataBase.isEmpty())
            println("\u001B[31m    <<  !  >> База пуста, выводить нечего.")
        else
            println(dataBase.toString().replace(Regex("""[\[\],]"""), ""))
    }
}


class CmdFind(private val dataBase: MutableList<Person>, private val request: String): Command{
    fun run(){
        val query = request.split(" ")[1]
        println("    Поиск \"\u001B[94m$query\u001B[0m\" по всем записям ...")
        val foundPeople = dataBase.filter { person ->
            person.name.contains(query, ignoreCase = true) ||
                    person.phones.any { it.contains(query, ignoreCase = true) } ||
                    person.emails.any { it.contains(query, ignoreCase = true) }
        }

        if (foundPeople.isEmpty()) {
            println("\u001B[31m    << ! >> Нет совпадений по запросу \"$query\".\u001B[0m")
        } else {
            println("    Найдены следующие люди:")
            foundPeople.forEach { println("        " + it.name) }
        }
    }
}


