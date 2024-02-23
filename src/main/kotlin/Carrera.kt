import kotlin.random.Random


class Carrera(var nombreCarrera:String, val distanciaTotal:Float, val participantes:List<Vehiculo>){
    var estadoCarrera:Boolean = false
    var historialAcciones:MutableMap<String,List<String>> = mutableMapOf()
    val posiciones = mutableMapOf<Int, String>()

    companion object{
        val listaDeAcciones:MutableList<String> = mutableListOf()
        val contadorDeRepostado = mutableMapOf<String,Int>()
        var vecesRepostado = 0
        var vecesFiligranas = 0
    }

    fun iniciarCarrera(){
        estadoCarrera = true
        participantes.forEach { avanzarVehiculo(it) }
        determinarGanador()
        obtenerResultado()
    }

    fun avanzarVehiculo(vehiculo: Vehiculo){
        listaDeAcciones.clear()
        var distancia = distanciaTotal
        var contadorDeTramos = (distancia/20).toInt()
        do {
            realizarFiligrana(vehiculo)
            when {

                contadorDeTramos != 0 -> {
                    val paraLlegar = vehiculo.realizaViaje(20f)

                    distancia -= if ( paraLlegar == 0f) {
                        20f
                    }else{
                        (20f-paraLlegar)
                    }
                }
                contadorDeTramos == 0 -> {
                    distancia = vehiculo.realizaViaje(distancia)
                }
            }

            listaDeAcciones.add("${vehiculo.nombre} a recorrido ${vehiculo.kilometrosActuales} y le quedan ${distancia.redondear(2)}")

            if (vehiculo.combustibleActual == 0f){
                repostarVehiculo(vehiculo)
            }
            contadorDeTramos--

        }while (contadorDeTramos != -1)
        actualizarPosiciones()
        historialAcciones.put(vehiculo.nombre, listaDeAcciones)
        contadorDeRepostado.put(vehiculo.nombre, vecesRepostado)
        vecesRepostado = 0
    }

    fun repostarVehiculo(vehiculo: Vehiculo){
        vecesRepostado++
        vehiculo.repostar()
        listaDeAcciones.add("${vehiculo.nombre} a repostado.")
    }

    fun realizarFiligrana(vehiculo: Vehiculo){
        val numeroRandom = Random.nextInt(1,3)
        when(numeroRandom){
            1 ->{
                when(vehiculo){
                    is Automovil -> vehiculo.realizaDerrape()
                    is Motocicleta -> vehiculo.realizarCaballito()
                }
                vecesFiligranas++
                listaDeAcciones.add("${vehiculo.nombre} a realizado una filigrana.")
            }

            2 -> {
                if (vehiculo.combustibleActual>2f){
                    when(vehiculo){
                        is Automovil -> {
                            vehiculo.realizaDerrape()
                            vehiculo.realizaDerrape()
                        }
                        is Motocicleta -> {
                            vehiculo.realizarCaballito()
                            vehiculo.realizarCaballito()
                        }
                    }
                    vecesFiligranas+=2
                    listaDeAcciones.add("${vehiculo.nombre} a realizado dos filigrana.")
                }else{
                    when(vehiculo){
                        is Automovil -> vehiculo.realizaDerrape()
                        is Motocicleta -> vehiculo.realizarCaballito()
                    }
                    vecesFiligranas++
                    listaDeAcciones.add("${vehiculo.nombre} a realizado una filigrana.")
                }
            }
        }
    }

    fun actualizarPosiciones(){
        val listaMezclada = participantes.shuffled()
        var posicion = 1
        for (participante in listaMezclada){
            posiciones.put(posicion,participante.nombre)
            posicion++
        }
        println(posiciones)
    }

    fun determinarGanador(){
        val ganador =posiciones.get(1)
        println(ganador)
    }

    fun obtenerResultado(){
        for (x in 1..6){
            println("$x º ${posiciones.get(x)} ha reccorrido $distanciaTotal  , ha repostado ${contadorDeRepostado.get(posiciones.get(x))} veces y ${historialAcciones.get(posiciones.get(x))} ")
        }
    }
}