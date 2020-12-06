package com.example.minerva.service.model

data class LembreteModel(val idFirebase: String?, val id: Int, var titulo: String, var cor:String, var ano: Int, var mes: Int, var dia:Int, var hora: Int, var minuto:Int ): Comparator<LembreteModel>{
    override fun compare(p0: LembreteModel, p1: LembreteModel): Int {
        if (p0.hora < p1.hora) {
            return 1
        } else if (p0.hora == p1.hora && p0.minuto < p1.minuto) {
            return 1
        } else if (p0.hora == p1.hora && p0.minuto == p1.minuto) {
            return 0
        }
        return -1
    }

}