class Motocicleta(var cilindrada:Int,nombre:String,marca:String, modelo:String, capacidadCombustible:Float, combustibleActual: Float, kilometrosActuales: Float
):Vehiculo(nombre,marca, modelo, capacidadCombustible,
    combustibleActual, kilometrosActuales
){

    override fun calcularAutonomia(): Float {
        return (combustibleActual* KM_Litros_Motos).redondear(2)
    }

    fun realizarCaballito():Float{
        combustibleActual-=0.5F
        return combustibleActual
    }
    override fun realizaViaje(distancia: Int): Int {
            val DistanciaTotal = calcularAutonomia()
            if (DistanciaTotal > distancia) {
                combustibleActual -= (distancia / KM_Litros_Motos)
                kilometrosActuales += distancia
                return 0
            }
            combustibleActual = 0F
            kilometrosActuales += (distancia - DistanciaTotal).toInt()
            return (distancia - DistanciaTotal).toInt()
    }

    override fun toString(): String {
        return "${super.toString()} , de cilindada : $cilindrada"
    }

    companion object{
        const val KM_Litros_Motos = 20
    }
}