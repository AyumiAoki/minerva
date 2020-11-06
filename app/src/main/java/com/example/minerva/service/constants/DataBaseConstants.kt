package com.example.minerva.service.constants

/**
 * Todas as constantes utilizadas no banco de dados
 * Tabelas, Colunas
 */
class DataBaseConstants private constructor() {

    /**
     * Tabelas disponíveis no banco de dados com suas colunas
     */
    object NOTA {
        const val TABLE_NAME = "Nota"

        object COLUMNS {
            const val ID = "id"
            const val TITULO = "titulo"
            const val TEXTO = "texto"
        }
    }
}