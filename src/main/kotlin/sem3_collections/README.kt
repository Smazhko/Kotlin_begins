package sem3_collections


/* РЕГУЛЯРКА
name.matches(Regex("""[\p{L}\p{N}_-]+"""))

- [\p{L}\p{N}_-]: Это символьный класс, который соответствует любому символу буквы (как кириллической, так и латинской)
    или цифре, а также символам - и _.
- +: Это квантификатор, который указывает, что предыдущий символьный класс должен повторяться один или более раз.


phone.matches(Regex("""^\+?\d+[\d-]*${'$'}"""))

В данном регулярном выражении:

- ^ и $ обозначают начало и конец строки соответственно, чтобы гарантировать, что номер телефона соответствует всей
    строке, а не является частью большего текста.
- \+? означает, что символ + может быть там, но он не обязателен (знак ? означает, что предыдущий символ
    может быть там ноль или один раз).
- \d+ соответствует одной или более цифрам.
- [\d-]* соответствует нулю или более цифрам или символам -. Символ - помещен в квадратные скобки, чтобы он не
    интерпретировался как диапазон символов.


email.matches(Regex("""^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,}${'$'}"""))

В данном регулярном выражении:

- ^\w+ соответствует одному или более буквенно-цифровым символам в начале строки (первая часть email адреса).
- @ обозначает символ @.
- [a-zA-Z_]+? соответствует одной или более буквенным символам во второй части адреса после символа @.
- \. обозначает символ точки '.'. Обратный слэш \ используется для экранирования точки, потому что в регулярных
    выражениях точка обозначает любой символ.
- [a-zA-Z]{2,} соответствует двум или более буквенным символам в конце адреса (например, домену верхнего уровня,
    такому как com, net и т.д.).
 */

/* ЛОГ РАБОТЫ ПРОГРАММЫ

Введите команду > add asdasd_asdas email qwe12@sdf.sdf
    Имя: asdasd asdas, e-mail: qwe12@sdf.sdf
Введите команду > add asdasd_asdas emil qwe12@sdf.sdf
    Некорректный ввод команд. Используйте команду help.
Введите команду > aasd
    Неизвестная команда. Используйте help.
Введите команду > help

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

Введите команду > add  phone
    Некорректный ввод команд. Используйте команду help.
Введите команду > add phone phone add
    Некорректный формат номера телефона
Введите команду > add Boris phone +8-952-512-52-25
    Имя: Boris, Телефон: +8-952-512-52-25
Введите команду > add Boris email boris@mail.ru
    Имя: Boris, e-mail: boris@mail.ru
Введите команду > add Boris email boris@mail.
    Некорректный формат адреса эл. почты
Введите команду > add Boris email boris@m.ru
    Имя: Boris, e-mail: boris@m.ru
Введите команду > add Bor*is email boris@mail.ru
    Некорректный формат имени
Введите команду > add Boris phone 8944554x
    Некорректный формат номера телефона
 */

