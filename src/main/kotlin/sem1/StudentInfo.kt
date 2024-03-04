package sem1

/*
Реализовать функции createOutputString так, чтобы программа выводила следующие строчки.

Alice has age of 42
Bob has age of 23
student Carol has age of 19
Daniel has age of 32
null
12

• createOutputString формирует строку, используя параметры name, age и
isStudent. У параметров age и isStudent есть значения по умолчанию.
 */

fun main(){
    println(createOutputString("Alice"))
    println(createOutputString("Bob", 23))
    println(createOutputString(isStudent = true, name = "Carol", age = 19))
    println(createOutputString("Daniel", 32, isStudent = null))
}

fun createOutputString(name: String, age: Int = 42, isStudent: Boolean? = false): String {
    val result: String = "$name has age of $age"
    return if (isStudent != null && isStudent == true)
        "student $result"
    else
        result
}
