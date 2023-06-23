import kotlin.random.Random

class GameCard {

    fun createCard(name: String, number: Int): Array<Array<Int>> {
        val cardString = listOf(0, 0, 0, 0, 1, 1, 1, 1, 1) //создание двумерного массива
        var card = arrayOf<Array<Int>>()
        for (i in 0..1) {
            var line = arrayOf<Int>()
            val newCardString = cardString.shuffled()
            for (j in 0..8) {
                line += newCardString[j]
            }
            card += line
        }
        var line3 = arrayOf<Int>()
        var countOfOne = 0
        for (j in 0..8) {
            if ((card[0][j] * card[1][j] == 1) || countOfOne == 5)
                line3 += 0
            else {
                line3 += 1
                countOfOne++
            }

        }
        card += line3

        val numbers = getNumbersForCard()  //заполнение массива
        for (j in 0..8) {
            for (i in 0..2) {
                if (card[i][j] == 1)
                    card[i][j] = numbers.first()
                numbers.removeFirst()
            }
        }

        println(                                //вывод карточки в консоль
            "Игровая карточка №$number $name:\n" +
                    "___________________________________"
        )
        for (line in card) {
            for (value in line) {
                print("$value\t")
            }
            println()
        }
        println("___________________________________")
    return card
    }

    private fun getNumbersForCard(): MutableList<Int> {            // получение чисел для карточки
        val tensOfNumbers = mutableMapOf(
            "0-9" to 0, "10-19" to 0, "20-29" to 0, "30-39" to 0, "40-49" to 0, "50-59" to 0,
            "60-69" to 0, "70-79" to 0, "80-90" to 0
        )
        val availableNumbers = mutableListOf<Int>()
        while (availableNumbers.size < 27) {
            val value = Random.nextInt(1, 90)
            if (!(availableNumbers.contains(value))) {
                when (value) {
                    in 1..9 -> if (tensOfNumbers["0-9"]!! < 3) {
                        availableNumbers.add(value)
                        tensOfNumbers["0-9"] = tensOfNumbers["0-9"]!! + 1
                    }

                    in 10..19 -> if (tensOfNumbers["10-19"]!! < 3) {
                        availableNumbers.add(value)
                        tensOfNumbers["10-19"] = tensOfNumbers["10-19"]!! + 1
                    }

                    in 20..29 -> if (tensOfNumbers["20-29"]!! < 3) {
                        availableNumbers.add(value)
                        tensOfNumbers["20-29"] = tensOfNumbers["20-29"]!! + 1
                    }

                    in 30..39 -> if (tensOfNumbers["30-39"]!! < 3) {
                        availableNumbers.add(value)
                        tensOfNumbers["30-39"] = tensOfNumbers["30-39"]!! + 1
                    }

                    in 40..49 -> if (tensOfNumbers["40-49"]!! < 3) {
                        availableNumbers.add(value)
                        tensOfNumbers["40-49"] = tensOfNumbers["40-49"]!! + 1
                    }

                    in 50..59 -> if (tensOfNumbers["50-59"]!! < 3) {
                        availableNumbers.add(value)
                        tensOfNumbers["50-59"] = tensOfNumbers["50-59"]!! + 1
                    }

                    in 60..69 -> if (tensOfNumbers["60-69"]!! < 3) {
                        availableNumbers.add(value)
                        tensOfNumbers["60-69"] = tensOfNumbers["60-69"]!! + 1
                    }

                    in 70..79 -> if (tensOfNumbers["70-79"]!! < 3) {
                        availableNumbers.add(value)
                        tensOfNumbers["70-79"] = tensOfNumbers["70-79"]!! + 1
                    }

                    in 80..90 -> if (tensOfNumbers["80-90"]!! < 3) {
                        availableNumbers.add(value)
                        tensOfNumbers["80-90"] = tensOfNumbers["80-90"]!! + 1
                    }
                }
            }
        }
        availableNumbers.sort()
        return availableNumbers
    }
}


