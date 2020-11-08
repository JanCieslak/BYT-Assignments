package group_one

class Participant(
            val name: String,
    private val mediator: Mediator
) {
    init {
        mediator.participants.add(this)
    }

    fun receive(from: String, message: String) {
        println("$name got message from $from! Message content: $message")
    }

    fun say(message: String) {
        mediator.broadcast(name, message)
    }
}

class Mediator {
    val participants = ArrayList<Participant>()

    fun broadcast(from: String, message: String) {
        for (participant in participants) {
            if (participant.name != from) {
                participant.receive(from, message)
            }
        }
    }
}

fun main() {
    val mediator = Mediator()

    val participant1 = Participant("Bob", mediator)
    val participant2 = Participant("Ann", mediator)
    val participant3 = Participant("John", mediator)
    val participant4 = Participant("Mary", mediator)

    participant1.say("Hello world!")

    // OUTPUT:
//    Ann got message from Bob! Message content: Hello world!
//    John got message from Bob! Message content: Hello world!
//    Mary got message from Bob! Message content: Hello world!
}