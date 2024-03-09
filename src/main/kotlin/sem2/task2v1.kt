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
    printOperationResult1(
        Plus1(
            Value1(4.0),
            Multiply1(
                Value1(2.5),
                Value1(2.0)
            )
        )
    )

    // Соответствует формуле (1 + 3.5) * (2.5 + 2) = 20,25
    printOperationResult1(
        Multiply1(
            Plus1(
                Value1(1.0),
                Value1(3.5)
            ),
            Plus1(
                Value1(2.5),
                Value1(2.0)
            )
        )
    )

    // Соответствует формуле 10 = 10
    printOperationResult1(
        Value1(10.0)
    )

    // [(2.7 + 3.1) + 3.5] * [2.5 * (2.0 + 3.1 + 5.0) + 2.0)] * [(0.7 + 0.25) + 2.0 * 5.1 * 3.0]
    // 5,8 + 3,5 = 9,3			2,5 * 10,1 + 2,0 = 27,25				0,95 + 30,6 = 31,55
    // 9,3 * 27,25 * 31,55 = 7 995,55875
    // из-за неточноcти Double выдаёт 7995.558749999999
    printOperationResult1(
        Multiply1(
            Plus1(
                Plus1(
                    Value1(2.7),
                    Value1(3.1)
                ),
                Value1(3.5)
            ),
            Plus1(
                Multiply1(
                    Value1(2.5),
                    Plus1(
                        Value1(2.0),
                        Value1(3.1),
                        Value1(5.0)
                    )
                ),
                Value1(2.0)
            ),
            Plus1(
                Plus1(
                    Value1(0.7),
                    Value1(0.25)
                ),
                Multiply1(
                    Value1(2.0),
                    Value1(5.1),
                    Value1(3.0)
                )
            )
        )
    )
}

fun printOperationResult1(operation: OperationV2) {
    val result = operation.calculate()
    println(result)
}


interface OperationV2{
    var result: Double
    fun calculate(): Double
}


class Value1(num: Double): OperationV2{
    override var result: Double = num
    override fun calculate(): Double {
        return result
    }
}


class Multiply1 (vararg operations: OperationV2): OperationV2{
    override var result: Double = 1.0 // начальное значение при умножении 1

    init {
        for (oper in operations){
            result *= oper.calculate()
        }
    }
    override fun calculate(): Double {
        return result
    }
}


class Plus1(vararg operations: OperationV2): OperationV2{
    override var result: Double = 0.0

    init { // массив принятых переменных не принимается в методах, поэтому обрабатываем тут
        for (oper in operations){
            result += oper.calculate()
        }
    }

    override fun calculate(): Double {
        return result
    }
}