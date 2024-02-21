fun main(){
    val coche1=Automovil("Jose", "wosl" , "23z", false,false,200f,20.00F, 0f)
    val moto = Motocicleta(2,"luis","kuzuaki","5M4",10f,8F,2f)
    println(coche1.combustibleActual)
    println(coche1.realizaViaje(170))
    println(coche1.combustibleActual)
    println(coche1.realizaDerrape())
    println(coche1.repostar(0f))
    coche1.cambiarCondicionBritania(true)
    println(coche1.toString())
    println(moto.calcularAutonomia())
    println(moto.realizarCaballito())
    println(moto.realizaViaje(100))
    println(moto.calcularAutonomia())
    println(moto.repostar(0f))
    println(moto.calcularAutonomia())
}

