package org.jose.firebaseappjose

class Cliente {

var id: String?=null
var nombre: String? = null
var emailc: String? = null
var telefono: String? = null
var mensaje: String? = null

constructor() {
    this.id = ""
    this.nombre = ""
    this.emailc = ""
    this.telefono = ""
    this.mensaje = ""
}

constructor(id: String, nombre: String, emailc: String, telefono: String, mensaje: String) {
    this.id = id
    this.nombre = nombre
    this.emailc = emailc
    this.telefono = telefono
    this.mensaje = mensaje
}
}


