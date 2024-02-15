open class Vehiculo(open val marca: String, open val modelo: String, open val capacidadCombistible: Int){

    override fun toString(): String {
        return "Tu $marca $modelo , con una capacidad ${calcularAutonomia()}"
    }
    open fun calcularAutonomia():Int{
        return capacidadCombistible*10
    }

    open fun realizaViaje(distancia:Int):Int{
        distancia/10*
    }

}

fun Float.redondear(posiciones:Int):Float{
    if (posiciones<0){posiciones = 0
    for ()
}