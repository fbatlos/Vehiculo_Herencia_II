import kotlin.math.pow
import kotlin.math.roundToInt

open class Vehiculo(open val marca: String, open val modelo: String, open val capacidadCombistible: Float, open var combustibleActual: Float, open var kilometrosActuales:Int){
    init {
        require(capacidadCombistible>=combustibleActual && capacidadCombistible > 0){"Datos no valido"}
        require(combustibleActual>0){"no valido"}
    }
    override fun toString(): String {
        return "Tu $marca $modelo , con una autonomia ${calcularAutonomia()}"
    }
    open fun calcularAutonomia():Int{
        return (combustibleActual*10).toInt()
    }

    open fun realizaViaje(distancia:Int ):Int{
        val DistanciaTotal = combustibleActual*10
        if (DistanciaTotal>distancia) {
            combustibleActual -=(distancia/10)
            kilometrosActuales +=distancia
            return 0
        }
        combustibleActual = 0F
        kilometrosActuales += (distancia - DistanciaTotal).toInt()
        return (distancia - DistanciaTotal).toInt()
    }



    open fun repostar(cantida:Float):Float{
        if (cantida>capacidadCombistible|| cantida>(combustibleActual+cantida) ||cantida<= 0){
            combustibleActual = capacidadCombistible.toFloat()
        }
        combustibleActual += cantida
        return cantida.redondear(2)
    }

}

const val diez = 10.0
fun Float.redondear(posiciones:Int):Float{
    val coma = (diez.pow(posiciones)).toFloat()
    val numero = this
    val redondeo = (numero*coma).roundToInt()/coma
    return redondeo
}