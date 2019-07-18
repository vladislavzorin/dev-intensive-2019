package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName : String?) : Pair<String?, String?> {
        val parts : List<String>? =if(fullName?.split(" ")?.get(0) == "") null else fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName to lastName
    }
    fun transliteration(payload: String, divider: String = " "): String{
        return payload.toCharArray().map{character -> if(character.isUpperCase()) transliteralCharacter(character.toLowerCase()).capitalize()
            else transliteralCharacter(character)}.joinToString("").replace(" ", divider)
        }

       fun transliteralCharacter(character: Char): String = when(character){
        'а'-> "a"

        'б'-> "b"

        'в'-> "v"

        'г'-> "g"

        'д'-> "d"

        'е'-> "e"

        'ё'-> "e"

        'ж'-> "zh"

        'з'-> "z"

        'и'-> "i"

        'й'-> "i"

        'к'-> "k"

        'л'-> "l"

        'м'-> "m"

        'н'-> "n"

        'о'-> "o"

        'п'-> "p"

        'р'-> "r"

        'с'-> "s"

        'т'-> "t"

        'у'-> "u"

        'ф'-> "f"

        'х'-> "h"

        'ц'-> "c"

        'ч'-> "ch"

        'ш'-> "sh"

        'щ'-> "sh'"

        'ъ'-> ""

        'ы'-> "i"

        'ь'-> ""

        'э'-> "e"

        'ю'-> "yu"

        'я'-> "ya"
        else -> character.toString()
    }


    fun toInitials(firstName: String?, lastName: String?): String?{
        val firstChar: String = firstName?.capitalize()?.getOrNull(0)?.toString()?:""
        val lastChar: String = lastName?.capitalize()?.getOrNull(0)?.toString()?:""
        return if(firstChar.plus(lastChar).isBlank()) null else "${firstChar.plus(lastChar)}"
    }
}