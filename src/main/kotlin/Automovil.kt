class Automovil(var tipo:tipos,
                marca:String,
                modelo:String,
                val esElectrico :Boolean,
                var condicionBritanica:Boolean = false,
                capacidadCombistible:Float,
                combustibleActual:Float,
                kilometrosActuales: Int):Vehiculo(marca , modelo , capacidadCombistible , combustibleActual ,kilometrosActuales
){
    fun cambiarCondicionBritania(nuevaCondicion:Boolean){
        condicionBritanica = cambiarABritanica()
    }

    override fun calcularAutonomia(): Int {
        if (esElectrico){
            return (combustibleActual*15).toInt()
        }
        return (combustibleActual*10).toInt()
    }

    fun realizaDerrape():Float{
        combustibleActual-=0.5f
        return capacidadCombistible
    }


    override fun realizaViaje(distancia: Int): Int {
        if (esElectrico){
            val DistanciaTotal = calcularAutonomia()
            if (DistanciaTotal>distancia) {
                combustibleActual -=(distancia/15)
                kilometrosActuales +=distancia
                return 0
            }
            combustibleActual = 0F
            kilometrosActuales += (distancia - DistanciaTotal).toInt()
            return (distancia - DistanciaTotal).toInt()
        }else {
            val DistanciaTotal = calcularAutonomia()
            if (DistanciaTotal > distancia) {
                combustibleActual -= (distancia / 10)
                kilometrosActuales += distancia
                return 0
            }
            combustibleActual = 0F
            kilometrosActuales += (distancia - DistanciaTotal).toInt()
            return (distancia - DistanciaTotal).toInt()
        }
    }
    override fun toString(): String {
        return "${super.toString()} , de tipo : $tipo"
    }
    companion object{
        fun cambiarABritanica():Boolean {
            return true
        }
    }
}