package sem3_collections

/*
Продолжаем дорабатывать домашнее задание из предыдущего семинара. За основу берём код решения из предыдущего домашнего задания.

— Измените класс Person так, чтобы он содержал список телефонов и список почтовых адресов, связанных с человеком.
— Теперь в телефонной книге могут храниться записи о нескольких людях. Используйте для этого наиболее подходящую структуру данных.
— Команда AddPhone теперь должна добавлять новый телефон к записи соответствующего человека.
— Команда AddEmail теперь должна добавлять новый email к записи соответствующего человека.
— Команда show должна принимать в качестве аргумента имя человека и выводить связанные с ним телефоны и адреса электронной почты.
— Добавьте команду find, которая принимает email или телефон и выводит список людей, для которых записано такое значение.
 */

private var dataBase: MutableList<Person> = mutableListOf()


fun main() {
    while (true) {
        val command = readCommand()
        //println("    Выполняется команда \u001B[94m" + command + "\u001B[0m...")
        when (command) {
            is CmdExit -> CmdExit.run()

            is CmdHelp -> CmdHelp.run()

            is CmdAdd -> {
                val newPerson = command.run()
                addPerson(newPerson)
                // println(dataBase)
            }

            is CmdShow -> command.run()

            is CmdFind -> command.run()

            is CmdShowAll -> command.run()
        }
    }
}



fun readCommand(): Command {
    print("\u001B[93mВведите команду > \u001B[0m")
    val userRequest = readlnOrNull()
    if (userRequest != null) {
        when {
            userRequest.trim().lowercase() == "exit" -> return CmdExit()

            userRequest.trim().lowercase() == "help" -> return CmdHelp()

            userRequest.trim().lowercase() == "showall" -> return CmdShowAll(dataBase)

            userRequest.trim().lowercase().startsWith("add") -> return CmdAdd(userRequest)

            userRequest.trim().lowercase().startsWith("show") -> return CmdShow(dataBase, userRequest)

            userRequest.trim().lowercase().startsWith("find") -> return CmdFind(dataBase, userRequest)
        }
    }
    return CmdHelp()
}

fun addPerson(newPerson: Person?) {
    if (newPerson != null) {
        if (dataBase.any { person -> person.name == newPerson.name }) {
            var continueFlag = true
            println("    Такое имя уже есть в базе. Введите \u001B[94m1\u001B[0m, чтобы всё равно добавить, " +
                    "или \u001B[94m0\u001B[0m, чтобы совместить записи.")
            while (continueFlag) {
                print("\u001B[93m    1 или 0 > \u001B[0m")
                val userRequest = readlnOrNull()
                when (userRequest) {
                    "1" -> {
                        dataBase.add(newPerson)
                        continueFlag = false
                    }

                    "0" -> {
                        val existPerson = dataBase.find { person -> person.name == newPerson.name }!!
                        existPerson.phones.addAll(newPerson.phones)
                        existPerson.emails.addAll(newPerson.emails)
                        continueFlag = false
                        println("    Запись успешно обновлена.")
                    }
                }
            }
        }
        else{
            dataBase.add(newPerson)
            println("    Запись успешно добавлена в базу.")
        }
    }
    else
        println("\u001B[31m    <<  !  >> Failed to add new record. \u001B[0m")
}

/*
add Константин_Рыжов phone +7-985-577-52-74 email kryzh@rabota.me email kryzh@mail.ru phone +7-999-123-25-48
add Вальдемар_Ростовский phone 8(952)9521515 email volondemort@biz.tv phone 35-54-78
add Ремонт phone +7(952)258-78-96 phone 35-54-78 phone 78-54-52
add РемонтАвто phone 8(900)2000-600
add Ремонт phone +7(952)258-78-96 phone 8(925)514-41-75 email vremonte@ya.ru
find ремонт
find 52
find biz.tv
find GOD
show Ремонт
show ремонт
 */

/*
Введите команду > add РемонтАвто phone 8(900)2000-600
    Запись успешно добавлена в базу.
[
    ╓──────────────────── ВИЗИТНАЯ  КАРТОЧКА ────────────────────
    ║ Name  : Константин Рыжов
    ║ Phone : [+7-985-577-52-74, +7-999-123-25-48]
    ║ e-mail: [kryzh@rabota.me, kryzh@mail.ru]
    ╙────────────────────────────────────────────────────────────,
    ╓──────────────────── ВИЗИТНАЯ  КАРТОЧКА ────────────────────
    ║ Name  : Вальдемар Ростовский
    ║ Phone : [8(952)9521515, 35-54-78]
    ║ e-mail: [volondemort@biz.tv]
    ╙────────────────────────────────────────────────────────────,
    ╓──────────────────── ВИЗИТНАЯ  КАРТОЧКА ────────────────────
    ║ Name  : Ремонт
    ║ Phone : [+7(952)258-78-96, 35-54-78, 78-54-52]
    ╙────────────────────────────────────────────────────────────,
    ╓──────────────────── ВИЗИТНАЯ  КАРТОЧКА ────────────────────
    ║ Name  : РемонтАвто
    ║ Phone : [8(900)2000-600]
    ╙────────────────────────────────────────────────────────────]

Введите команду > add Ремонт phone +7(952)258-78-96 phone 8(925)514-41-75 email vremonte@ya.ru
    Такое имя уже есть в базе. Введите 1, чтобы всё равно добавить, или 0, чтобы совместить записи.
    1 или 0 > 0
    Запись успешно добавлена в базу.
[
    ╓──────────────────── ВИЗИТНАЯ  КАРТОЧКА ────────────────────
    ║ Name  : Константин Рыжов
    ║ Phone : [+7-985-577-52-74, +7-999-123-25-48]
    ║ e-mail: [kryzh@rabota.me, kryzh@mail.ru]
    ╙────────────────────────────────────────────────────────────,
    ╓──────────────────── ВИЗИТНАЯ  КАРТОЧКА ────────────────────
    ║ Name  : Вальдемар Ростовский
    ║ Phone : [8(952)9521515, 35-54-78]
    ║ e-mail: [volondemort@biz.tv]
    ╙────────────────────────────────────────────────────────────,
    ╓──────────────────── ВИЗИТНАЯ  КАРТОЧКА ────────────────────
    ║ Name  : Ремонт
    ║ Phone : [+7(952)258-78-96, 35-54-78, 78-54-52, 8(925)514-41-75]
    ║ e-mail: [vremonte@ya.ru]
    ╙────────────────────────────────────────────────────────────,
    ╓──────────────────── ВИЗИТНАЯ  КАРТОЧКА ────────────────────
    ║ Name  : РемонтАвто
    ║ Phone : [8(900)2000-600]
    ╙────────────────────────────────────────────────────────────]
Введите команду > find ремонт
    Поиск "ремонт" по всем записям ...
    Найдены следующие люди:
        Ремонт
        РемонтАвто
Введите команду > find 52
    Поиск "52" по всем записям ...
    Найдены следующие люди:
        Константин Рыжов
        Вальдемар Ростовский
        Ремонт
Введите команду > find biz.tv
    Поиск "biz.tv" по всем записям ...
    Найдены следующие люди:
        Вальдемар Ростовский
Введите команду > find GOD
    Поиск "GOD" по всем записям ...
    << ! >> Нет совпадений по запросу "GOD".
Введите команду > show Ремонт

    ╓──────────────────── ВИЗИТНАЯ  КАРТОЧКА ────────────────────
    ║ Name  : Ремонт
    ║ Phone : [+7(952)258-78-96, 35-54-78, 78-54-52, 8(925)514-41-75]
    ║ e-mail: [vremonte@ya.ru]
    ╙────────────────────────────────────────────────────────────
Введите команду > show ремонт
    <<  !  >> Не могу найти "ремонт". В базе отсутствует такая запись.
Введите команду > exit
    Выход из программы ...

 */