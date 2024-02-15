class Motocicleta(var cilindrada:Int,marca:String, modelo:String, capacidadCombistible:Int):Vehiculo(marca, modelo, capacidadCombistible){
    override fun calcularAutonomia(): Int {
        return super.calcularAutonomia()-40
    }

    override fun toString(): String {
        return "${super.toString()} , de cilindada : $cilindrada"
    }
}