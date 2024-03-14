package sem4_exceptions


private val data = mutableListOf<Any>()
// если поместить внутрь класса - сохраняется только последний человек из списка
// fun json начинает строительство строки - передаёт управление в класс JsonBuilder
// далее управление переходит к классу PersonBuilder и обратно по цепочке.

class JsonBuilder {

    fun person(init: PersonBuilder.() -> Unit) {
        val personBuilder = PersonBuilder().apply(init)
        data.add(personBuilder.build())
    }

    fun toJsonString(): String {
        return data.joinToString(prefix = "[", postfix = "]") { it.toString() }
    }

}

class PersonBuilder {
    private var name: String = ""
    private val phones = mutableListOf<String>()
    private val emails = mutableListOf<String>()

    fun name(name: String) {
        this.name = name
    }

    fun phone(phone: MutableSet<String>) {
        phones.addAll(phone)
    }

    fun email(email: MutableSet<String>) {
        emails.addAll(email)
    }

    fun build(): String {
        return buildString {
            appendLine("{")
            appendLine("""  "name": "$name",""")
            appendLine("""  "phones": [${phones.joinToString(",") { "\"$it\"" }}],""")
            appendLine("""  "emails": [${emails.joinToString(",") { "\"$it\"" }}]""")
            append("}")
        }
    }
}

fun json(init: JsonBuilder.() -> Unit): String {
    val jsonBuilder = JsonBuilder().apply(init)
    // apply - функция видимости, которая возвращает объект на котором вызвана (прим когда надо выполнить к-л действия над объектом
    // init - лямбда функция, которая инициализирует объект
    return jsonBuilder.toJsonString()
}

