import kotlin.random.Random


class Carrera(var nombreCarrera:String, val distanciaTotal:Float, val participantes:List<Vehiculo>){
    var estadoCarrera:Boolean = false
    var historialAcciones:MutableMap<String,List<String>> = mutableMapOf()
    val posiciones = mutableListOf<String>()

    companion object{
        var vecesRepostado = 0
    }

    fun iniciarCarrera(){
        estadoCarrera = true
        participantes.forEach { return avanzarVehiculo(it) }
    }

    fun avanzarVehiculo(vehiculo: Vehiculo){
        do {
            var distancia = Random.nextInt(10, 200).toFloat()
            var contadorDeDerrapes = (Random.nextInt(10, 200).toFloat()/20).toInt()
            do {
                distancia=vehiculo.realizaViaje(distancia)
                when(vehiculo){
                    is Automovil -> vehiculo.realizaDerrape()
                    is Motocicleta -> vehiculo.realizarCaballito()
                }
                contadorDeDerrapes--
            }while (contadorDeDerrapes == 0)
        }while (distancia == 0f)
        repostarVehiculo(vehiculo)
    }

    fun repostarVehiculo(vehiculo: Vehiculo){
        vecesRepostado++
        vehiculo.repostar()
    }

}