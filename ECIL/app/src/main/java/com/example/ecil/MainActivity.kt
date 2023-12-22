package com.example.ecil

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.core.Amplify
import com.example.ecil.MobAuth.phoneAuth
import com.example.ecil.Navigation.Home
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var mail:EditText
    private lateinit var pswd:EditText
    private lateinit var lgnBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        auth=FirebaseAuth.getInstance()

        mail=findViewById(R.id.mail)
        pswd=findViewById(R.id.pswd)
        lgnBtn=findViewById(R.id.lgnBtn)
        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(AWSApiPlugin())
            Amplify.configure(applicationContext)
            Log.i("MyAmplifyApp", "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error)
        }
        var currentUser = auth.currentUser
        if(currentUser != null) {
            Log.i("USER",currentUser.toString())
            startActivity(Intent(applicationContext, Home::class.java))
            finish()
        }
        lgnBtn.setOnClickListener {
            Log.i("Clicked","Login Button")
            Log.i("Mail",mail.text.toString().trim())
            Log.i("Password",pswd.text.toString().trim())
            auth.signInWithEmailAndPassword(mail.text.toString().trim(),pswd.text.toString().trim())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.i("USER",auth.currentUser.toString())
                        Log.d(TAG, "signInWithEmail:success")
                        auth.signOut()
                        Log.i("USER",auth.currentUser.toString())
                        var intent = Intent(applicationContext, phoneAuth::class.java)
                        startActivity(intent)
                    }
                    else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}