//package com.example.expenseapplication.Utils
//
//import android.util.Base64
//import com.example.expenseapplication.Constants.Constants.security_key
//import javax.crypto.Cipher
//import javax.crypto.KeyGenerator
//import javax.crypto.SecretKey
//import javax.crypto.spec.IvParameterSpec
//import javax.crypto.spec.SecretKeySpec
//
//object CipherUtil {
//
//
//    private val keyGenerator = KeyGenerator.getInstance("AES")
//    private val cipher = Cipher.getInstance("AES/GCM/NoPadding")
//
//    fun encrypt(data: String): String {
//        keyGenerator.init(128)
//        val secretKey = keyGenerator.generateKey()
//
//        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
//        val encryptedData = cipher.doFinal(data.toByteArray())
//
//        return Base64.getEncoder().encodeToString(encryptedData)
//    }
//
//    fun decrypt(encryptedData: String): String {
//        val decodedEncryptedData = Base64.getDecoder().decode(encryptedData)
//
//        keyGenerator.init(128)
//        val secretKey = keyGenerator.generateKey()
//
//        cipher.init(Cipher.DECRYPT_MODE, secretKey)
//        val decryptedData = cipher.doFinal(decodedEncryptedData)
//
//        return decryptedData.toString(Charsets.UTF_8)
//    }
//}
