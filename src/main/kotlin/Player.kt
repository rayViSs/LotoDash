class Player(val name: String, private val cardsCount: Int) {
    var numberOfCoincidence = 0
    private val cards = createCards()

    private fun createCards(): MutableList<Array<Array<Int>>> {
        val cards = mutableListOf<Array<Array<Int>>>()
        for (i in 1..cardsCount) {
            cards.add(GameCard().createCard(name, i))
        }
        return cards
    }

    fun checkNumbers(number: Int) {
        cards.forEach { card ->
            card.forEach { line ->
                line.forEach { item ->
                    if (item == number) {
                        numberOfCoincidence++
                    }
                }
            }
        }
    }
}