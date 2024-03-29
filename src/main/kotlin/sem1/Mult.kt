package sem1

/*
Реализовать функции multiplyBy так, чтобы программа выводила следующие строчки.

null
12

multiplyBy принимает два числа типа Int и возвращает их произведение.
Вместо первого числа, можно передать null, в этом случае функция должна
вернуть null.

 */
fun main(){
    println(multiplyBy(null, 4))
    println(multiplyBy(3, 4))

    println(multiplyBy2(null, 4))
    println(multiplyBy2(4, null))
    println(multiplyBy2(3, 4))
}

fun multiplyBy(num1: Int?, num2: Int?): Int? {
    return if (num1 == null || num2 == null)
        null
    else
        num1 * num2
}

fun multiplyBy2(num1: Int?, num2: Int?): Int? {
    return num1?.let { n1 ->
        num2?.let {n2 ->
            n1 * n2
            }
        }
    /*
    В этом примере мы используем функцию let, которая позволяет нам выполнять блок кода,
    только если переменная не равна null. Каждая переменная num1, num2 проверяется на null с помощью let,
    и если она не равна null, результат сохраняется во временной переменной (например, n1, n2).
    Затем мы выполняем операции с этими переменными. Если хотя бы одна из переменных равна null,
    весь блок кода let возвращается как null, а result также станет null.

    В этом примере есть лямбда-выражение внутри другого лямбда-выражения. Это вполне допустимо в Kotlin.

    Здесь num1?.let { n1 -> ... } представляет вызов функции let для переменной num1.
    Переменная num1 проверяется на null, и если она не равна null, то переменная n1
    (это аргумент лямбда-выражения) получает значение num1, и выполняется код внутри блока,
    который следует за оператором ->.
     */
}

/*
null
12
null
null
12
 */
