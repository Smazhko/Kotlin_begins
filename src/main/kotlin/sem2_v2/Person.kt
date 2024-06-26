package sem2_v2

data class Person(var name: String){
    var phone: MutableList<String> = mutableListOf()
    var email: MutableList<String> = mutableListOf()

    override fun toString(): String {
        var result: String
        if (name.isEmpty() && phone.isEmpty() && email.isEmpty())
            result = "Person is empty."
        else{
            result  = "    +------------- НОВАЯ ЗАПИСЬ -------------\n"
            result += "    | Name  : $name\n"
            if (phone.isNotEmpty())
                result += "    | Phone : $phone\n"
            if (email.isNotEmpty())
                result += "    | e-mail: $email\n"
            result += "    +" + "-".repeat(40)
        }
        return result
    }
}
