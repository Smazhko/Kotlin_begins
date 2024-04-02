package sem2_v2;

/*
За основу берём код решения домашнего задания из предыдущего семинара и дорабатываем его.

— Создайте иерархию sealed классов, которые представляют собой команды. В корне иерархии интерфейс Command.
— В каждом классе иерархии должна быть функция isValid(): Boolean, которая возвращает true, если команда введена
с корректными аргументами. Проверку телефона и email нужно перенести в эту функцию.
— Напишите функцию readCommand(): Command, которая читает команду из текстового ввода, распознаёт её и возвращает
один из классов-наследников Command, соответствующий введённой команде.
— Создайте data класс Person, который представляет собой запись о человеке. Этот класс должен содержать поля:
name – имя человека
phone – номер телефона
email – адрес электронной почты
— Добавьте новую команду show, которая выводит последнее значение, введённой с помощью команды add.
Для этого значение должно быть сохранено в переменную типа Person. Если на момент выполнения команды show
не было ничего введено, нужно вывести на экран сообщение “Not initialized”.
— Функция main должна выглядеть следующем образом. Для каждой команды от пользователя:
Читаем команду с помощью функции readCommand
Выводим на экран получившийся экземпляр Command
Если isValid для команды возвращает false, выводим help. Если true, обрабатываем команду внутри when.
 */

fun main(){
    val continueFlag = true;
    var person: Person? = null;
    while(continueFlag) {
        val command = readCommand()
        println("    Выполняется команда \u001B[94m$command\u001B[0m...")
        if (command.isValid()) {
            when (command) {
                is CommandOption.CmdExit -> command.run()
                is CommandOption.CmdHelp -> command.run()
                is CommandOption.CmdAdd -> person = command.run()
                is CommandOption.CmdShow -> command.run(person)
            }
        }
        else {
            println("    \u001B[31mКоманда введена некорректно!\u001B[0m")
            CommandOption.CmdHelp("help").run()
        }
    }
}

fun readCommand(): Command {
    print("\u001B[93mВведите команду > \u001B[0m")
    var userRequest = readlnOrNull();
    if (userRequest != null) {
        userRequest = userRequest.trim()
        when (userRequest.split(" ").firstOrNull()){
            "exit" -> return CommandOption.CmdExit(userRequest)
            "help" -> return CommandOption.CmdHelp(userRequest)
            "add"  -> return CommandOption.CmdAdd(userRequest)
            "show" -> return CommandOption.CmdShow(userRequest)
        }
    }
    return CommandOption.CmdHelp("help")
}