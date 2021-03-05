package org.jose.firebaseappjose

import android.graphics.Bitmap
import android.widget.ImageView

class Piso(actualizado: Boolean = false, idpiso: String="", altura: String = "", bano: String = "", url: String = "", habitacion: String = "",
           nombre: String = "", precio: String = ""
           , supp: String = "", tipo: String = "", foto: Bitmap?=null) {

    var actualizado: Boolean? = actualizado
    var idpiso: String? =idpiso
    var altura: String? = altura
    var bano: String? = bano
    var url: String? = url
    var habitacion: String? = habitacion
    var nombre: String? = nombre
    var precio: String? = precio
    var supp: String? = supp
    var tipo: String? = tipo
    var foto : Bitmap?=foto

}


