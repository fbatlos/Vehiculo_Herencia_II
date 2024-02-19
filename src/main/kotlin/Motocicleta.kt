class Motocicleta(var cilindrada:Int, marca:String, modelo:String, capacidadCombistible:Float, combustibleActual: Float, kilometrosActuales: Int
):Vehiculo(marca, modelo, capacidadCombistible,
    combustibleActual, kilometrosActuales
){
    override fun calcularAutonomia(): Int {
        return (combustibleActual*20).toInt()
    }

    fun realizarCaballito():Float{
        combustibleActual-=0.5F
        return combustibleActual
    }
    override fun realizaViaje(distancia: Int): Int {
            val DistanciaTotal = calcularAutonomia()
            if (DistanciaTotal > distancia) {
                combustibleActual -= (distancia / 20)
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
}