package org.jose.firebaseappjose


import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class serviciomensajes : FirebaseMessagingService() {



//si llega notificacion en primer plano cuando usamos la app. manejarla con un TOAST

    override fun onMessageReceived(remotomsg: RemoteMessage) {

        super.onMessageReceived(remotomsg)


        Looper.prepare()

      Handler().post{

          Toast.makeText(baseContext, remotomsg.notification?.title, Toast.LENGTH_SHORT).show()
      }

Looper.loop()
    }
}