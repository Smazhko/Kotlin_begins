package sem4_exceptions

data class Person(var name: String){
    var phones: MutableSet<String> = mutableSetOf()
    var emails: MutableSet<String> = mutableSetOf()

    override fun toString(): String {
        var result: String
        if (name.isEmpty() && phones.isEmpty() && emails.isEmpty())
            result = "Person is empty."
        else{
            result = "    Name  : $name\n"
            if (phones.isNotEmpty())
                result += "    Phone : $phones\n"
            if (emails.isNotEmpty())
                result += "    e-mail: $emails\n"
        }
        return result
    }
}
