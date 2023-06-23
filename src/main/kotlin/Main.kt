import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

fun main() {
    val players: MutableList<Player> = mutableListOf()
    val workCor = mutableListOf<Job>()
    println("Введите количество игроков")
    val countOfPlayers = readLine()?.toIntOrNull() ?: 2
    println("Введите количество карточек")
    val countOfCards = readLine()?.toIntOrNull() ?: 1
    for (i in 1..countOfPlayers) {
        players.add(Player("player $i", countOfCards))
        workCor.add(Job())
    }

    runBlocking {
        players.forEachIndexed { indexJob, player ->
            workCor[indexJob] = launch(Dispatchers.Default) {
                HostOfTheGame.sharedFlow.collect { number ->
                    player.checkNumbers(number)
                    println("У игрока ${player.name} -- ${player.numberOfCoincidence} совпадений")
                    if (player.numberOfCoincidence == countOfCards * 15) {
                        println("\nИгрок ${player.name} выйграл\n")
                        yield()
                        cancel()
                        workCor.forEach { it.cancel() }
                    }
                }
            }
        }
    }
}

object HostOfTheGame {
    private val scope = CoroutineScope(Job() + Dispatchers.Default)
    private val _sharedFlow = MutableSharedFlow<Int>()
    val sharedFlow = _sharedFlow.asSharedFlow()
    private val kegs = mutableListOf<Int>()

    init {
        for (i in 1..90) {
            kegs.add(i)
        }
        val shufflekegs = kegs.shuffled()
        scope.launch {
            var i = 0
            repeat(90) {
                delay(100L)
                println("Раунд №${i+1} \t Бочонок ${shufflekegs[i]}")
                _sharedFlow.emit(shufflekegs[i])
                i++
            }
        }
    }
}