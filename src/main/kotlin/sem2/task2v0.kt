package sem2

/*
Задание №2 “Иерархия классов”
Семинар 2. Классы, объекты и интерфейсы в Kotlin.
Построить иерархию классов таким образом, чтобы после выполнения функции main был выведен
корректный результат вычисления трёх формул.
fun printOperationResult(operation: Operation) {
 val result = operation.calculate()
 println(result)
}
fun main() {
 // Формулы 1-3
}
*/


fun main() {
    // Соответствует формуле 4 + 2.5 * 2 = 9
    printOperationResult(
        Plus(
            Value(4.0),
            Multiply(
                Value(2.5),
                Value(2.0)
            )
        )
    )

    // Соответствует формуле (1 + 3.5) * (2.5 + 2) = 20,25
    printOperationResult(
        Multiply(
            Plus(
                Value(1.0),
                Value(3.5)
            ),
            Plus(
                Value(2.5),
                Value(2.0)
            )
        )
    )

    // Соответствует формуле 10 = 10
    printOperationResult(
        Value(10.0)
    )

    // [(2.7 + 3.1) + 3.5] * [2.5 * (2.0 + 3.1 + 5.0) + 2.0)] * [(0.7 + 0.25) + 2.0 * 5.1 * 3.0]
    // 5,8 + 3,5 = 9,3			2,5 * 10,1 + 2,0 = 27,25				0,95 + 30,6 = 31,55
    // 9,3 * 27,25 * 31,55 = 7 995,55875
    // из-за неточноcти Double выдаёт 7995.558749999999
    printOperationResult(
        Multiply(
            Plus(
                Plus(
                    Value(2.7),
                    Value(3.1)
                ),
                Value(3.5)
            ),
            Plus(
                Multiply(
                    Value(2.5),
                    Plus(
                        Value(2.0),
                        Value(3.1),
                        Value(5.0)
                    )
                ),
                Value(2.0)
            ),
            Plus(
                Plus(
                    Value(0.7),
                    Value(0.25)
                ),
                Multiply(
                    Value(2.0),
                    Value(5.1),
                    Value(3.0)
                )
            )
        )
    )
}

fun printOperationResult(operation: Operation) {
    val result = operation.calculate()
    println(result)
}


// Исходя из предоставленных данных приходим к выводу, что все операции и сама Value
// должны реализовывать интерфейс OPERATION ().
// В интерфейсе должен быть метод calculate(), который ничего не принимает, но возвращает результат.
// Какой должен быть тип у результата ?? Рассуждаем ...

// Класс VALUE принимает DOUBLE и при проведении calculate() возвращает самого себя.
// Классы операций сложения и умножения - принимают произвольный список других операций
// и должны по результатам вычислений возвращать результат класса OPERATION,
// чтобы этот результат можно было подставлять в другие вычисления.

// ЗНАЧИТ возвращаемый тип у метода calculate() должен быть OPERATION.
// Поскольку операции необходимо проводить не с классами, а всё же с числами,
// добавляем к интерфейсу переменную RESULT, чтобы она была у ВСЕХ участников.

// В классах сложения и умножения пробегаемся по списку операций, принятых в конструкторе,
// и добавляем к итоговому RESULT результат вычисления каждого из операндов.

// логика НЕВЕРНА... !!!
//
//
// Все операции, кроме VALUE должны принимать другие операции,
// и ВСЕ должны возвращать DOUBLE, чтобы с результатами работы функции можно было обрабатывать.
// Поэтому считаем код ниже - костыльным и смотрим на файл task2v1


interface Operation{
    var result: Double
    fun calculate(): Operation
}


class Value(num: Double): Operation{
    override var result: Double = num
    override fun calculate(): Operation {
        return this
    }

    override fun toString(): String {
        return result.toString() // ... костыль для вывода результата в консоль
    }
}


class Multiply (vararg operations: Operation): Operation{
    override var result: Double = 1.0 // начальное значение при умножении 1

    init {
        for (oper in operations){
            result *= oper.calculate().result
        }
    }
    override fun calculate(): Operation {
        return Value(result)
    }
}


class Plus(vararg operations: Operation): Operation{
    override var result: Double = 0.0 // начальное значение при умножении 0

    init {
        for (oper in operations){
            result += oper.calculate().result
        }
    }
    override fun calculate(): Operation {
        return Value(result)
    }
}