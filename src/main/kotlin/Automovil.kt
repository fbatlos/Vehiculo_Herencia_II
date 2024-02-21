class Automovil(
                nombre:String,
                marca:String,
                modelo:String,
                val esElectrico :Boolean,
                var condicionBritanica:Boolean = false,
                capacidadCombustible:Float,
                combustibleActual:Float,
                kilometrosActuales: Float):Vehiculo(nombre,marca , modelo , capacidadCombustible , combustibleActual ,kilometrosActuales
){

    fun cambiarCondicionBritania(nuevaCondicion:Boolean){
        condicionBritanica = cambiarABritanica()
    }

    override fun calcularAutonomia(): Float {
        if (esElectrico){
            return (combustibleActual* KM_Litro_Hibrido).redondear(2)
        }
        return (combustibleActual* KM_Litros_GAS).redondear(2)
    }

    fun realizaDerrape():Float{
        combustibleActual-=0.5f
        return combustibleActual
    }


    override fun realizaViaje(distancia: Int): Int {
        if (esElectrico){
            val DistanciaTotal = calcularAutonomia()
            if (DistanciaTotal>distancia) {
                combustibleActual -=(distancia/ KM_Litro_Hibrido)
                kilometrosActuales +=distancia
                return 0
            }
            combustibleActual = 0F
            kilometrosActuales += (distancia - DistanciaTotal).toInt()
            return (distancia - DistanciaTotal).toInt()
        }else {
            val DistanciaTotal = calcularAutonomia()
            if (DistanciaTotal > distancia) {
                combustibleActual -= (distancia / KM_Litros_GAS)
                kilometrosActuales += distancia
                return 0
            }
            combustibleActual = 0F
            kilometrosActuales += (distancia - DistanciaTotal).toInt()
            return (distancia - DistanciaTotal).toInt()
        }
    }

    override fun toString(): String {
        return "${super.toString()}"
    }

    companion object{
        const val KM_Litro_Hibrido = 15f
        fun cambiarABritanica():Boolean {
            return true
        }
    }
}