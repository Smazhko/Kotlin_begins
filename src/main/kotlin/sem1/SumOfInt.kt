package sem1

/*
Написать простую программу, которая принимает в
качестве аргументов два целых числа и выводит на экран их
сумму. Всю программу можно написать внутри функции main.
 */

/*
Дописать функции
sumAll принимает переменное число аргументов типа Int. Возвращает сумму
всех чисел, либо 0, если не передан ни один аргумент.
 */

fun main(){
    println("Sum of null      = ${sum()}")
    println("Sum of 3         = ${sum(3)}")
    println("Sum of 2,5,15    = ${sum(2,5,15)}")
    println("Sum of 2,null,15 = ${sum(2,null,15)}")
}

fun sum(vararg nums: Int?): Int?{
    var total: Int = 0
    for (num in nums){
        if (num!= null) {
            total += num
        }
        else
            return null;
    }
   return total
}

/*
Sum of null      = 0
Sum of 3         = 3
Sum of 2,5,15    = 22
Sum of 2,null,15 = null
 */