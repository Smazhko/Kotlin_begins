package sem2

/*
Написать реализацию класса Holder и интерфейса
ValueChangeListener, таких, чтобы программа компилировалась, и при
выполнении функции main на экран было выведено "New value is 1".
fun main() {
    val holder = Holder.createHolder(Holder.DEFAULT_NUMBER)
    holder.number = 9
    holder.listener = object : ValueChangeListener {
        override fun onNewValue(number: Int) {
            println("New value is $number")
        }
    }
    holder.number = 1
}

 */

fun main() {
    // Создаётся неизменяемая переменная VAL holder.
    // Создание объекта происходит через сторонний метод, а не через конструктор.
    // Значит метод статический => значит помещаем его в companion object (контейнер всего статического в классе).
    // Этот метод должен возвращать объект типа Holder. Значит - у класса Holder не должно быть видимого конструктора.
    // Поэтому создаём классу Holder ПРИВАТНЫЙ конструктор:
    // в наименовании класса пишем private constructor(var number: Int).
    // Попутно мы сразу создаём публичное поле number.
    // Чтобы воспользоваться этим конструктором внутри класса Holder, используем Holder(какой-нибудь Int).
    // Далее ...
    // Конструкция Holder.DEFAULT_NUMBER обозначает обращение к статическому полю объекта HOLDER.
    // То есть нам необходимо добавить статическое поле DEFAULT_NUMBER
    // (т.к. статика - то поле будет доступно без создания экземпляра объекта).
    // Для этого в КОМПАНЬОНЕ (companion object) класса HOLDER создаём константу const val DEFAULT_NUMBER = 0

    val holder = Holder.createHolder(Holder.DEFAULT_NUMBER)

    // обращаемся к публичному полю объекта класса Holder
    holder.number = 9

    // ТУТ - с помощью конструкции object: ValueChangeListener создаётся анонимный объект
    // типа ValueChangeListener (интерфейс) и далее - переопределяется его метод onNewValue(number: Int).
    // Для того, чтобы эта конструкция была валидной:
    // - создаём интерфейс ValueChangeListener и наделяем его методом onNewValue(number: Int),
    //   (ВАЖНО! указать переменную - параметр метода)
    // - создаём в классе Holder поле listener типа "ValueChangeListener?". Используем знак вопроса, т.к.
    //    инициализацию этого поля внутри класса мы делаем с помощью null, ведь мы не будем реализовывать
    //    дефолтное поведение метода onNewValue (если это делать, значит надо создавать внутри класса новый объект
    //    типа ValueChangeListener и добавлять ему дефолтную реализацию интерфейса,
    //    но тогда изменения переменной NUMBER, которые произошли выше по коду, также будут выведены в консоль)
    holder.listener = object : ValueChangeListener {
        override fun onNewValue(number: Int) {
            println("New value is $number")
        }
    }

    // Чтобы при выполнении функции main на экран было выведено "New value is 1",
    // нам необходимо реализовать передачу изменившейся переменной в метод onNewValue.
    // SETTER поля класса вызывается автоматически при присвоении переменной значения.
    // Воспользуемся этим и напишем кастомный SETTER для переменной NUMBER:
    // в нём мы будем не только присваивать полю number новое переданное значение,
    // но и передадим это новое значение в метод - listener?.onNewValue(value)

    holder.number = 1

}


interface ValueChangeListener{
    fun onNewValue(number: Int)
}


class Holder private constructor(num: Int){
    var listener: ValueChangeListener? = null

    var number: Int = num // таким образом не нужно использовать блок INIT для инициализации объекта и этого поля
        set(value){
            field = value
            listener?.onNewValue(value)
        }

    companion object{
        const val DEFAULT_NUMBER = 0
        fun createHolder(num: Int): Holder{
            return Holder(num)
        }
    }
}

