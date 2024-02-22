import kotlin.random.Random


class Carrera(var nombreCarrera:String, val distanciaTotal:Float, val participantes:List<Vehiculo>){
    var estadoCarrera:Boolean = false
    var historialAcciones:MutableMap<String,List<String>> = mutableMapOf()
    val posiciones = mutableMapOf<String, Int>()

    companion object{
        var vecesRepostado = 0
        var vecesFiligranas = 0
    }

    fun iniciarCarrera(){
        estadoCarrera = true
        participantes.forEach { avanzarVehiculo(it) }
    }

    fun avanzarVehiculo(vehiculo: Vehiculo){
        do {
            var distancia = Random.nextInt(10, 200).toFloat()
            var contadorDeDerrapes = (distancia/20).toInt()
            do {
               // realizarFiligrana(vehiculo)

                val paraLlegar = vehiculo.realizaViaje(20f)

                when {
                    contadorDeDerrapes != 0 -> {
                        distancia -= if ( paraLlegar == 0f) {
                            20f
                        }else{
                            if (estadoCarrera == true){}
                            (20f-paraLlegar)
                        }
                    }
                    contadorDeDerrapes == 0 -> {
                        distancia = vehiculo.realizaViaje(distancia)
                    }

                }

                println("${vehiculo.nombre} a recorrido ${vehiculo.kilometrosActuales} y le quedan ${distancia.redondear(2)}")

                if (vehiculo.combustibleActual == 0f){
                    repostarVehiculo(vehiculo)
                }
                contadorDeDerrapes--
            }while (contadorDeDerrapes != -1)
            actualizarPosiciones(vehiculo)
        }while (distancia != 0f)
    }

    fun repostarVehiculo(vehiculo: Vehiculo){
        vecesRepostado++
        vehiculo.repostar()
        println("${vehiculo.nombre} a repostado.")
    }

    fun realizarFiligrana(vehiculo: Vehiculo){
        vecesFiligranas++
        when(vehiculo){
            is Automovil -> vehiculo.realizaDerrape()
            is Motocicleta -> vehiculo.realizarCaballito()
        }
        println("${vehiculo.nombre} a realizado una filigrana.")
    }

    fun actualizarPosiciones(vehiculo: Vehiculo){

    }

}