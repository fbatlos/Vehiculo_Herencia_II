fun main(){
    val coche1=Automovil(tipos.SUV , "wosl" , "23z", false,false,200f,20.00F, 0)
    val moto = Motocicleta(2,"kuzuaki","5M4",10f,8F,2)
   println(coche1.combustibleActual)
    println(coche1.realizaViaje(170))
    println(coche1.combustibleActual)
    println(coche1.realizaDerrape())
    coche1.cambiarCondicionBritania(true)
    println(coche1.toString())/*
    println(moto.calcularAutonomia())
    println(moto.realizarCaballito())
    println(moto.realizaViaje(100))
    println(moto.calcularAutonomia())*/
}

