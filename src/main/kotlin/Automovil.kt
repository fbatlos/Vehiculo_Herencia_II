class Automovil(var tipo:tipos,marca:String, modelo:String, capacidadCombistible:Int):Vehiculo(marca, modelo , capacidadCombistible){
    override fun calcularAutonomia(): Int {
        return super.calcularAutonomia()+100
    }
    override fun toString(): String {
        return "${super.toString()} , de tipo : $tipo"
    }
}