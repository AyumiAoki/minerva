package com.example.minerva.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.minerva.service.constants.DataBaseConstants

class NotaDataBaseHelper(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    /**
     * Método executado somente uma vez quando o acesso ao banco de dados é feito pela primeira vez
     */
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_GUEST)
    }

    /**
     * Método executado quando a versão do DATABASE_VERSION é alterada
     * Dessa maneira, a aplicação sabe que o banco de dados foi alterado e é necessário rodar o script de update
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    companion object {
        private const val VERSION = 1
        private const val NAME = "Notas.db"

        private const val CREATE_TABLE_GUEST =
            ("create table " + DataBaseConstants.NOTA.TABLE_NAME + " ("
                    + DataBaseConstants.NOTA.COLUMNS.ID + " integer primary key autoincrement, "
                    + DataBaseConstants.NOTA.COLUMNS.TITULO + " text, "
                    + DataBaseConstants.NOTA.COLUMNS.TEXTO + " text);")
    }

}