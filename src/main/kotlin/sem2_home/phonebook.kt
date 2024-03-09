package sem2_home;

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
    var continueFlag: Boolean = true;
    while(continueFlag) {
        var command = readCommand()
        println("    Выполняется команда \u001B[94m" + command + "\u001B[0m...")
        when (command){
            is CmdExit -> CmdExit.run()

            is CmdHelp -> CmdHelp.run()

            is CmdAdd -> {
                var cmdAdd = CmdAdd()
                cmdAdd.parseParams()
                val newPerson: Person? = cmdAdd.createRecord()
                if (newPerson != null)
                    newPerson.show()
                else
                    println("\u001B[31m    <<  !  >> Person is not initialized yet.\u001B[0m")
            }
        }
    }
}

fun readCommand(): Command {
    print("\u001B[93mВведите команду > \u001B[0m")
    var userRequest = readlnOrNull();
    if (userRequest != null) {
        when {
            userRequest.trim().lowercase() == "exit" -> return CmdExit()

            userRequest.trim().lowercase() == "help" -> return CmdHelp()

            userRequest.trim().lowercase().startsWith("add") -> {
                CmdAdd.request = userRequest
                return CmdAdd()
            }
        }
    }
    return CmdHelp()
}

/*
add dasd phone 123123 email asd@asd.we
add d%=asd phone 123fg123 email asd@asd
add dasd
phone 123123 email asd@asd.we
add dasd email asd@asd.we phone 123123
help
exit


Введите команду > add dasd phone 123123 email asd@asd.we
    Выполняется команда add...
    +------------- НОВАЯ ЗАПИСЬ -------------
    | Name  : dasd
    | Phone : [123123]
    | e-mail: [asd@asd.we]
    +----------------------------------------
Введите команду > add d%=asd phone 123fg123 email asd@asd
    Выполняется команда add...
    Некорректный формат имени
    <<  !  >> Person is not initialized yet.
Введите команду > add dasd
    Выполняется команда add...
    +------------- НОВАЯ ЗАПИСЬ -------------
    | Name  : dasd
    +----------------------------------------
Введите команду > phone 123123 email asd@asd.we
    Выполняется команда help...
   Поддерживаемые команды:
       add <Имя> phone <Номер телефона>
       add <Имя> email <Адрес электронной почты>
       help
       exit
   Обратите внимание на то, что:
       - <ПАРАМЕТРЫ> не должны быть пустыми, пишутся без скобок <>
       - в имени должны использоваться буквы (кириллица, латиница) и цифры,
       - для разделения слов в имени используйте знак '_',
       - телефон должен состоять из цифр и может начинаться со знака '+',
       - e-mail должен быть в формате ххх@yyy.zzz (символы a-z A-Z _).
Введите команду > add dasd email asd@asd.we phone 123123
    Выполняется команда add...
    +------------- НОВАЯ ЗАПИСЬ -------------
    | Name  : dasd
    | Phone : [123123]
    | e-mail: [asd@asd.we]
    +----------------------------------------
Введите команду > help
    Выполняется команда help...
   Поддерживаемые команды:
       add <Имя> phone <Номер телефона>
       add <Имя> email <Адрес электронной почты>
       help
       exit
   Обратите внимание на то, что:
       - <ПАРАМЕТРЫ> не должны быть пустыми, пишутся без скобок <>
       - в имени должны использоваться буквы (кириллица, латиница) и цифры,
       - для разделения слов в имени используйте знак '_',
       - телефон должен состоять из цифр и может начинаться со знака '+',
       - e-mail должен быть в формате ххх@yyy.zzz (символы a-z A-Z _).
Введите команду > exit
    Выполняется команда exit...
    Выход из программы ...

 */






