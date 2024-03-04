package sem1

/*
Написать программу, выводящую на экран фигуру из звёздочек
a – количество звёздочек на первой строчке фигуры;
b – количество строк от первой до центральной и от
центральной до последней;
c – количество звёздочек, на которое увеличивается
последовательность с каждой строкой

a=5, b=2, c=2
*****
*******
*********
*******
*****

 */

fun main(){
    drawFigure(5,2,2)
    drawFigure(1,3,2)
    drawFigure(1,2,4)
}

fun drawFigure(start: Int, mediana: Int, step: Int){
//    for (i in 0..mediana * 2)
//        if (i <= mediana)
//            println("*".repeat(start) + "*".repeat(step * i))
//        else
//            println("*".repeat(start) + "*".repeat(step * (mediana * 2 - i)))
//    println()
    // лучше разбить на 2 цикла - от начала до середины и от середины до конца - так будет меньше действий
    // и надо меньше думать над формулой в repeat
    for (i in 0..mediana)
        println("*".repeat(start) + "*".repeat(step * i))
    for (i in mediana-1 downTo 0)
        println("*".repeat(start) + "*".repeat(step * i))
    println()

}

/*

*****
*******
*********
*******
*****

*
***
*****
*******
*****
***
*

*
*****
*********
*****
*

 */