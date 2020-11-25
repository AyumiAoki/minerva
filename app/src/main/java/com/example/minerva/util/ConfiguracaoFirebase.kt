package com.example.minerva.util

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

object ConfiguracaoFirebase {
    private var database: DatabaseReference? = null
    private var auth: FirebaseAuth? = null
    private var storage: StorageReference? = null

    //retorna a instancia do FirebaseDatabase
    val firebaseDatabase: DatabaseReference?
        get() {
            if (database == null) {
                database = FirebaseDatabase.getInstance().reference
            }
            return database
        }

    //retorna a instancia do FirebaseAuth
    val firebaseAutenticacao: FirebaseAuth?
        get() {
            if (auth == null) {
                auth = FirebaseAuth.getInstance()
            }
            return auth
        }

    val firebaseStorage: StorageReference?
        get() {
            if (storage == null) {
                storage = FirebaseStorage.getInstance().reference
            }
            return storage
        }
}
