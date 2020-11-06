package com.example.minerva.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.minerva.service.constants.ErrorsFirebase

class Util {
    companion object {

        fun validarEmail(email: String): Boolean {
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            return email.matches(emailPattern.toRegex())
        }

        fun validarNome(nome: String): Boolean{
            for(a in nome){
                if(!a.isLetter() && !a.isWhitespace()){
                    return false
                }
            }
            return true
        }

        fun validarSenha(senha: String): Boolean{
            return senha.length < 6
        }

        fun errorFirebase(error: String): Int{
            return when {
                error.contains("address is badly") -> {
                    ErrorsFirebase.EMAIL_INVALIDO
                }

                error.contains("address is already") -> {
                    ErrorsFirebase.EMAIL_JA_CADASTRADO
                }

                error.contains("There is no user") -> {
                    ErrorsFirebase.EMAIL_NAO_CADASTRADO
                }

                error.contains("INVALID_EMAIL") -> {
                    ErrorsFirebase.EMAIL_INVALIDO
                }

                error.contains("EMAIL_NOT_FOUND") -> {
                    ErrorsFirebase.EMAIL_NAO_CADASTRADO
                }

                error.contains("least 6 characters") -> {
                   ErrorsFirebase.SENHA_INVALIDA
                }

                error.contains("password is invalid") -> {
                    ErrorsFirebase.SENHA_INCORRETA
                }

                error.contains("interrupted connection") -> {
                    ErrorsFirebase.SEM_CONEXAO
                }

                else -> {
                   ErrorsFirebase.DESCONHECIDO
                }

            }
        }

        fun statusInternetMoWi(context: Context): Boolean {
            val status: Boolean
            val conexao = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val recursosRede = conexao.getNetworkCapabilities(conexao.activeNetwork)
                if (recursosRede != null) { //VERIFICAMOS SE RECUPERAMOS ALGO
                    if (recursosRede.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {

                        //VERIFICAMOS SE DISPOSITIVO TEM 3G
                        return true
                    } else if (recursosRede.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {

                        //VERIFICAMOS SE DISPOSITIVO TEM WIFFI
                        return true
                    }

                    //NÃO POSSUI UMA CONEXAO DE REDE VÁLIDA
                    return false
                }
            } else { //COMECO DO ELSE

                // PARA DISPOSTIVOS ANTIGOS  (PRECAUÇÃO)         MESMO CODIGO
                val informacao = conexao.activeNetworkInfo
                status = informacao != null && informacao.isConnected
                return status
            }
            return false
        }
    }
}