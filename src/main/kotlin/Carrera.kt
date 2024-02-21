import kotlin.random.Random


class Carrera(var nombreCarrera:String, val distanciaTotal:Float, val participantes:List<Vehiculo>){
    var estadoCarrera:Boolean = false
    var historialAcciones:MutableMap<String,List<String>> = mutableMapOf()
    //var posiciones


    fun iniciarCarrera(){
        estadoCarrera = true
    }

    fun avanzarVehiculo(vehiculo: Vehiculo){
        do {
            val distancia = Random.nextInt(10, 200).toFloat()
            var necesitaRepostar = vehiculo.realizaViaje(distancia)
        }while (necesitaRepostar == 0f)
    }
}