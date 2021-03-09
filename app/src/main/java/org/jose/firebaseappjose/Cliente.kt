package org.jose.firebaseappjose

class Cliente {

var id: String?=null
var nombre: String? = null
var emailc: String? = null
var telefono: String? = null
var mensaje: String? = null
var desde: String?=null

constructor() {
    this.id = ""
    this.nombre = ""
    this.emailc = ""
    this.telefono = ""
    this.mensaje = ""
    this.desde=""
}

constructor(id: String, nombre: String, emailc: String, telefono: String, mensaje: String, desde: String) {
    this.id = id
    this.nombre = nombre
    this.emailc = emailc
    this.telefono = telefono
    this.mensaje = mensaje
    this.desde=desde
}
}


