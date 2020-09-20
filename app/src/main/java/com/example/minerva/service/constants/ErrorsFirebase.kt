package com.example.minerva.service.constants

class ErrorsFirebase private constructor(){
    companion object{
        const val NENHUM =0
        const val EMAIL_INVALIDO = 1
        const val EMAIL_JA_CADASTRADO = 2
        const val EMAIL_NAO_CADASTRADO = 3
        const val SENHA_INVALIDA = 4
        const val SENHA_INCORRETA = 5
        const val SEM_CONEXAO = 6
        const val DESCONHECIDO =7
    }
}