import kotlin.math.pow
import kotlin.math.roundToInt

open class Vehiculo(open val nombre: String,open val marca: String, open val modelo: String, open val capacidadCombustible: Float, open var combustibleActual: Float, open var kilometrosActuales:Float){
    init {
        comprobarNombre(nombre)
        require(capacidadCombustible>=combustibleActual && capacidadCombustible > 0){"Datos no valido"}
        require(combustibleActual>0){"no valido"}
    }

    open fun calcularAutonomia():Float{
        return (combustibleActual* KM_Litros_GAS).redondear(2)
    }

    open fun realizaViaje(distancia:Int ):Int{
        val DistanciaTotal = combustibleActual* KM_Litros_GAS
        if (DistanciaTotal>distancia) {
            combustibleActual -=(distancia/ KM_Litros_GAS)
            kilometrosActuales +=distancia
            return 0
        }
        combustibleActual = 0F
        kilometrosActuales += (distancia - DistanciaTotal).toInt()
        return (distancia - DistanciaTotal).toInt()
    }

    open fun repostar(cantida:Float):Float{
        if (capacidadCombustible<(combustibleActual+cantida) ||cantida<= 0f){
            combustibleActual = capacidadCombustible
            return (capacidadCombustible-cantida.redondear(2))
        }
        combustibleActual += cantida
        return cantida.redondear(2)
    }

    companion object{
        val listadonombres:MutableList<String> = mutableListOf()
        fun comprobarNombre(nombre:String){
            require(!listadonombres.contains(nombre)){"No se puede repetir los nombres."}
            listadonombres.add(nombre)
        }


        const val KM_Litros_GAS = 10f
    }

    override fun toString(): String {
        return "$nombre tu $marca $modelo , con una autonomia ${calcularAutonomia()}"
    }

}

const val diez = 10.0
fun Float.redondear(posiciones:Int):Float{
    val coma = (diez.pow(posiciones)).toFloat()
    val numero = this
    val redondeo = (numero*coma).roundToInt()/coma
    return redondeo
}