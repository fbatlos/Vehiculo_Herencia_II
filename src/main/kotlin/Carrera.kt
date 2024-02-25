import kotlin.random.Random


class Carrera(var nombreCarrera:String, val distanciaTotal:Float, var participantes:List<Vehiculo>){
    var estadoCarrera:Boolean = false
    var historialAcciones:MutableMap<String,MutableList<String>> = mutableMapOf()
    val posiciones = mutableMapOf<Int, String>()
    var contadorDeRepostado = mutableMapOf<String,Int>()
    var paradasRespostaje = 0
    var vecesFiligranas = 0

    fun iniciarCarrera(){
        estadoCarrera = true
        do {
            participantes.forEach { avanzarVehiculo(it) }
        }while (determinarGanador() == false)
        actualizarPosiciones()
        obtenerResultado()
    }

    fun avanzarVehiculo(vehiculo: Vehiculo){
        var distancia = Random.nextInt(10,250).toFloat()
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
            registarAccion(vehiculo,"${vehiculo.nombre} a recorrido ${vehiculo.kilometrosActuales.redondear(2)} y le quedan ${distancia.redondear(2)}")

            if (vehiculo.combustibleActual == 0f){
                repostarVehiculo(vehiculo)
            }

            contadorDeTramos--

        }while (contadorDeTramos != -1)
    }

    fun registarAccion(corredor:Vehiculo,accion:String){
        historialAcciones.computeIfAbsent(corredor.nombre){ mutableListOf()}.add(accion)
    }

    fun repostarVehiculo(vehiculo: Vehiculo){

        contadorDeRepostado.compute(vehiculo.nombre){ _,paradasARepostar-> (paradasARepostar?:0) + 1 }
        vehiculo.repostar()
        registarAccion(vehiculo, ("${vehiculo.nombre} a repostado.*********************"))
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
                registarAccion(vehiculo,("${vehiculo.nombre} a realizado una filigrana."))
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
                    registarAccion(vehiculo, ("${vehiculo.nombre} a realizado dos filigrana."))
                }else{
                    when(vehiculo){
                        is Automovil -> vehiculo.realizaDerrape()
                        is Motocicleta -> vehiculo.realizarCaballito()
                    }
                    vecesFiligranas++
                    registarAccion(vehiculo,("${vehiculo.nombre} a realizado una filigrana."))
                }
            }
        }
    }
    //Actualiza las posiciones
    fun actualizarPosiciones(){
        //Seleccionamos el parametro para comparar
        val comparador = compareBy<Vehiculo> {it.kilometrosActuales}
        //Ordenamos la lista segun el parametro sekleccionado
        participantes = participantes.sortedWith( comparador)
        var posicion = 1
        //recorremos la lista ordenada segun los kilometros actuales de vehiculo y le asignamos una posicion.
        for (vehiculo in participantes){
            posiciones.put(posicion,vehiculo.nombre)
            ++posicion
        }

    }

    fun determinarGanador():Boolean{
        //Seleccionamos el parametro para comparar
        val comparador = compareBy<Vehiculo> {it.kilometrosActuales}
        //Ordenamos la lista segun el parametro sekleccionado
        val listaOrdenada = participantes.sortedWith( comparador)
        if (listaOrdenada[5].kilometrosActuales >= 1000f){
            return true
        }
        else{return false}
    }

    fun obtenerResultado(){
        var posicion = 1
        var lugarEnPosiciones = 6
        var numeroDelParticipante = 5
          //  for ()
           // val resultado = ResultadoCarrera(


         //   )
        while (posicion != 7 ) {
            val resultados: MutableList<ResultadoCarrera> = mutableListOf()
            println(
                "$posicion º ${posiciones.get(lugarEnPosiciones)} ha reccorrido ${participantes[numeroDelParticipante].kilometrosActuales.redondear(2)}  , ha repostado ${
                    contadorDeRepostado.get(
                        posiciones.get(lugarEnPosiciones)
                    )
                } veces y ${historialAcciones.get(posiciones.get(lugarEnPosiciones))} "
            )






            ++posicion
            --lugarEnPosiciones
            --numeroDelParticipante
        }
    }

    data class ResultadoCarrera(
        val vehiculo: Vehiculo,
        val posicion: Int,
        val kilometraje: Float,
        val paradasRepostaje: Int,
        val historialAcciones: List<String>
    )

}