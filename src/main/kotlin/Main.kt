import kotlinx.coroutines.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
var cubosActuales=0;
var lenaActual=0;
var ramasActuales=0;
var comidaActual=0;
const val CUBOS_NECESARIOS = 4
const val LENA_NECESARIA  = 2
const val RAMA_NECESARIA  = 1
const val COMIDA_NECESARIA  = 1

fun main(){
    comenzar()
    Thread.sleep(80000)
}
var mutexHacha=Mutex()
var mutexHamaca=Mutex()
fun comenzar() {
    runBlocking{
        val listOfDeferred = mutableListOf<Deferred<Unit>>()
        var resulAA= AmigoA()
        var resulAB=AmigoB()
        var resulAC =AmigoC()
        listOfDeferred.add(resulAA)
        listOfDeferred.add(resulAB)
        listOfDeferred.add(resulAC)
        listOfDeferred.awaitAll()

    }
    println("Barca construida y aprovisionada con exito")

}
fun AmigoA(): Deferred<Unit> {
    return GlobalScope.async (start= CoroutineStart.LAZY) {
        for(i in 1..CUBOS_NECESARIOS){
            println("El amigo A va a por un cubo de agua")
            delay(4000)
            println("El amigo A a vuelto con un cubo de agua")
            cubosActuales++
            println("El amigo A quiere descansar")
            cogerHamaca("Amigo A",1000)
            println("El amigo A deja de descansar")
        }


    }

}

fun AmigoB(): Deferred<Unit> {

    return GlobalScope.async (start= CoroutineStart.LAZY) {

        for (i in 1..LENA_NECESARIA){
            println("El amigo B va a por leña")
            cogerHacha("Amigo B",5000)
            println("El amigo B vuelve con leña ")
            lenaActual++
            println("El amigo B quiere descansar")
            cogerHamaca("Amigo B", 3000)
            println("El amigo B deja de descansár")
        }


    }

}

fun AmigoC(): Deferred<Unit> {

    return GlobalScope.async (start= CoroutineStart.LAZY) {
        for(i in 1..RAMA_NECESARIA){
            println("El amigo C va por ramas")
            delay(3000)
            println("El amigo C vuelve con ramas")
            ramasActuales++
        }
        for(i in 1..COMIDA_NECESARIA) {
            println("El amigo C se va a cazar comida")
            cogerHacha("Amigo C", 4000)
            println("El amigo C trae comida")
            comidaActual++
        }


    }
}

suspend fun cogerHamaca(amigo: String, espera: Long) {
    mutexHamaca.withLock {

        println("El amigo $amigo se tumba en la hamaca ")
        delay(espera)
        println("El amigo $amigo  se levanta de la hamaca ")
    }
}

suspend fun cogerHacha(amigo: String, espera: Long) {
    mutexHacha.withLock {

        println("El amigo $amigo coge el hacha ")
        delay(espera)
        println("El amigo $amigo  deja el hacha ")
    }
}
