package sem2_home

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
             print  ("\u001B[37m") // серый
             println("   Поддерживаемые команды:")
             println("       add <Имя> phone <Номер телефона>")
             println("       add <Имя> email <Адрес электронной почты>")
             println("       help")
             println("       exit")
             println("   Обратите внимание на то, что:")
             println("       - <ПАРАМЕТРЫ> не должны быть пустыми, пишутся без скобок <>")
             println("       - в имени должны использоваться буквы (кириллица, латиница) и цифры,")
             println("       - для разделения слов в имени используйте знак '_',")
             println("       - телефон должен состоять из цифр и может начинаться со знака '+',")
             print  ("       - e-mail должен быть в формате ххх@yyy.zzz (символы a-z A-Z _).")
             println("\u001B[0m") // возврат к обычному
         }
     }

    override fun toString(): String {
        return "help"
    }
}


sealed interface Parameter {
    fun isValid(param: String): Boolean
}

data object Name: Parameter {
    override fun isValid(param: String): Boolean {
        return param.matches(Regex("""[\p{L}\p{N}_\s-]+"""))
    }

}

data object Phone: Parameter {
    override fun isValid(param: String): Boolean {
        return param.matches(Regex("""^\+?\d+[\d-]*${'$'}"""))
    }
}

data object Email: Parameter {
    override fun isValid(param: String): Boolean {
        return param.matches(Regex("""^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,}${'$'}"""))
    }
}


class CmdAdd : Command {
    override fun toString(): String {
        return "add"
    }

    companion object {
        var request: String = ""
    }

    var name = ""
    var phone = ""
    var email = ""

    fun parseParams() {
        var params = request.split(" ")

        var ADD_CMD = "add"
        var PHONE_CMD = "phone"
        var EMAIL_CMD = "email"

        var i = 0
        while (i < params.size) {
            when (params[i]) {
                ADD_CMD -> {
                    if (Name.isValid(params[i + 1]))
                        name = params[i + 1]
                    else {
                        println("\u001B[31m    Некорректный формат имени\u001B[0m")
                        break
                    }
                    i++
                }

                PHONE_CMD -> {
                    if (Phone.isValid(params[i + 1]))
                        phone = params[i + 1]
                    else {
                        println("\u001B[31m    Некорректный формат номера телефона\u001B[0m")
                    }
                    i++
                }

                EMAIL_CMD -> {
                    if (Email.isValid(params[i + 1]))
                        email = params[i + 1]
                    else {
                        println("\u001B[31m    Некорректный формат адреса эл. почты\u001B[0m")
                    }
                    i++
                }
            }
            i++
        }
    }

    fun createRecord(): Person?{
        if (name.isNotEmpty()) {
            val newPerson = Person(name)
            if (phone.isNotEmpty())
                newPerson.phone.add(phone)
            if (email.isNotEmpty())
                newPerson.email.add(email)
            return newPerson
        }
        return null
    }
}


