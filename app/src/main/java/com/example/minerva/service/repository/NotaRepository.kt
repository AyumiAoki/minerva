package com.example.minerva.service.repository

import android.content.ContentValues
import android.content.Context
import com.example.minerva.service.constants.DataBaseConstants
import com.example.minerva.service.model.NotaModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception
import java.util.ArrayList

class NotaRepository private constructor(context: Context) {

    // Acesso ao banco de dados
    private var mNotaDataBaseHelper: NotaDataBaseHelper = NotaDataBaseHelper(context)

    /**
     * Singleton
     */
    companion object {
        private lateinit var repository: NotaRepository

        fun getInstance(context: Context): NotaRepository {
            if (!::repository.isInitialized) {
                repository = NotaRepository(context)
            }
            return repository
        }
    }

    /**
     * Carrega convidado
     */
    fun get(id: Int): NotaModel? {

        var guest: NotaModel? = null
        return try {
            val db = mNotaDataBaseHelper.readableDatabase

            // Colunas que serão retornadas
            val projection = arrayOf(
                DataBaseConstants.NOTA.COLUMNS.TITULO,
                DataBaseConstants.NOTA.COLUMNS.TEXTO
            )

            // Filtro
            val selection = DataBaseConstants.NOTA.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(
                DataBaseConstants.NOTA.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                null
            )

            // Verifica se existem dados no cursor
            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()

                val TITULO = cursor.getString(cursor.getColumnIndex(DataBaseConstants.NOTA.COLUMNS.TITULO))
                val presence = cursor.getString(cursor.getColumnIndex(DataBaseConstants.NOTA.COLUMNS.TEXTO))

                guest = NotaModel(id, TITULO, presence)
            }

            cursor?.close()

            guest
        } catch (e: Exception) {
            guest
        }
    }

    /**
     * Insere convidado
     */
    fun save(guest: NotaModel): Boolean {
        return try {

            val  reference = FirebaseDatabase.getInstance().reference
            reference.child("notas").push().setValue(guest)

            println("oiiiiiiiiiiiii")
            println( FirebaseAuth.getInstance().currentUser!!.uid.toString())
            // writableDatabase - Para fazer escrita de dados
            val db = mNotaDataBaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DataBaseConstants.NOTA.COLUMNS.TITULO,guest.titulo)
            contentValues.put(DataBaseConstants.NOTA.COLUMNS.TEXTO, guest.texto)
            db.insert(DataBaseConstants.NOTA.TABLE_NAME, null, contentValues)
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Faz a listagem de todos os convidados
     */
    fun getAll(): List<NotaModel> {
        val list: MutableList<NotaModel> = ArrayList()
        return try {
            val db = mNotaDataBaseHelper.readableDatabase

            // Colunas que serão retornadas
            val projection = arrayOf(
                DataBaseConstants.NOTA.COLUMNS.ID,
                DataBaseConstants.NOTA.COLUMNS.TITULO,
                DataBaseConstants.NOTA.COLUMNS.TEXTO
            )

            // Linha única
            // Cursor cursor = db.rawQuery("select * from Guest", null);

            // Faz a seleção
            val cursor = db.query(
                DataBaseConstants.NOTA.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.NOTA.COLUMNS.ID))
                    val TITULO = cursor.getString(cursor.getColumnIndex(DataBaseConstants.NOTA.COLUMNS.TITULO))
                    val presence = cursor.getString(cursor.getColumnIndex(DataBaseConstants.NOTA.COLUMNS.TEXTO))

                    println(TITULO)
                    println(presence)

                    val guest = NotaModel(id, TITULO, presence)
                    list.add(guest)
                }

                // Como verificar se um valor é nulo
                // cursor.isNull(cursor.getInt(cursor.getColumnIndex(DataBaseConstants.NOTA.COLUMNS.PRESENCE))
            }

            cursor?.close()

            list
        } catch (e: Exception) {
            println(e.message)
            list
        }
    }

    /**
     * Faz a listagem de todos os convidados presentes
     *//*
    fun getPresent(): List<NotaModel> {
        val list: MutableList<NotaModel> = ArrayList()
        return try {
            val db = mNotaDataBaseHelper.readableDatabase

            val cursor = db.rawQuery("SELECT id, TITULO, presence FROM Guest WHERE presence = 1", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.NOTA.COLUMNS.ID))
                    val TITULO = cursor.getString(cursor.getColumnIndex(DataBaseConstants.NOTA.COLUMNS.TITULO))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.NOTA.COLUMNS.PRESENCE)) == 1)

                    val guest = NotaModel(id, TITULO, presence)
                    list.add(guest)
                }
            }

            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }*/
/*
    /**
     * Faz a listagem de todos os convidados presentes
     */
    fun getAbsent(): List<NotaModel> {
        val list: MutableList<NotaModel> = ArrayList()
        return try {
            val db = mNotaDataBaseHelper.readableDatabase

            val cursor = db.rawQuery("SELECT id, TITULO, presence FROM Guest WHERE presence = 0", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.NOTA.COLUMNS.ID))
                    val TITULO = cursor.getString(cursor.getColumnIndex(DataBaseConstants.NOTA.COLUMNS.TITULO))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.NOTA.COLUMNS.PRESENCE)) == 1)

                    val guest = NotaModel(id, TITULO, presence)
                    list.add(guest)
                }
            }

            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }*/

    /**
     * Atualiza convidado
     */
    fun update(guest: NotaModel): Boolean {
        return try {
            val db = mNotaDataBaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DataBaseConstants.NOTA.COLUMNS.TITULO, guest.titulo)
            contentValues.put(DataBaseConstants.NOTA.COLUMNS.TEXTO, guest.texto)

            // Critério de seleção
            val selection = DataBaseConstants.NOTA.COLUMNS.ID + " = ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.NOTA.TABLE_NAME, contentValues, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Remove convidado
     */
    fun delete(id: Int): Boolean {
        return try {
            val db = mNotaDataBaseHelper.writableDatabase
            val selection = DataBaseConstants.NOTA.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.NOTA.TABLE_NAME, selection, args)

            true
        } catch (e: Exception) {
            false
        }
    }

}