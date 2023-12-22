package com.sewain.mobileapp.utils

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val FILENAME_FORMAT = "yyyyMMdd_HHmmss"
private val timeStamp: String = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(Date())

fun createCustomTempFile(context: Context): File {
    val filesDir = context.externalCacheDir
    return File.createTempFile(timeStamp, ".jpg", filesDir)
}

fun uriToFile(selectedImageUri: Uri, context: Context): File {
    val myFile = createCustomTempFile(context)
    val inputStream = context.contentResolver.openInputStream(selectedImageUri) as InputStream
    val outputStream = FileOutputStream(myFile)
    val buffer = ByteArray(1024)
    var length: Int
    while (inputStream.read(buffer).also { length = it } > 0) outputStream.write(buffer, 0, length)
    outputStream.close()
    inputStream.close()
    return myFile

//    val contentResolver = context.contentResolver
//    val file = File(context.cacheDir, contentResolver.getFileName(selectedImageUri))
//    val inputStream = contentResolver.openInputStream(selectedImageUri)
//    val outputStream = FileOutputStream(file)
//    inputStream?.copyTo(outputStream)
//    return file
}

//@SuppressLint("Range")
//fun ContentResolver.getFileName(uri: Uri): String {
//    var name = ""
//    val cursor = this.query(uri, null, null, null, null)
//    cursor?.use {
//        it.moveToFirst()
//        name = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
//    }
//    return name
//}
