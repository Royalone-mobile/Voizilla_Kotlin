package bednarczuk.voizilla

import android.content.Context
import android.graphics.*
import bednarczuk.voizilla.data.client.model.GeoPoint
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import android.graphics.Paint.ANTI_ALIAS_FLAG
import com.google.android.gms.maps.model.BitmapDescriptor
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.graphics.Color.DKGRAY
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import bednarczuk.voizilla.data.client.model.Vehicle
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException


fun GeoPoint.latlng(): LatLng {
    return LatLng(latitude, longitude)
}

fun Context.bitmapDescriptorFromtext(resourceId: Int, text: String, color: Int): BitmapDescriptor? {

    try {
        val resources = this.resources
        val scale = resources.displayMetrics.density
        var bitmap = BitmapFactory.decodeResource(resources, resourceId)

        var bitmapConfig: android.graphics.Bitmap.Config? = bitmap.config
        if (bitmapConfig == null) {
            bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888
        }
        bitmap = bitmap.copy(bitmapConfig, true)

        val canvas = Canvas(bitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = color
        paint.textSize = (12 * scale)

        val bounds = Rect()
        paint.getTextBounds(text, 0, text.length, bounds)
        val x = (bitmap.width - bounds.width()) / 6
        val y = (bitmap.height + bounds.height()) / 5

        canvas.drawText(text, x * scale, y * scale, paint)

        return BitmapDescriptorFactory.fromBitmap(bitmap)

    } catch (e: Exception) {
        return null
    }

}

fun Vehicle.toJSON(): String {
    val gson = Gson()
    return gson.toJson(this)
}

fun String.vehicleFromJSONorNull(): Vehicle? {
    val gson = Gson()
    try {
        return gson.fromJson(this, Vehicle::class.java)
    } catch (e: JsonSyntaxException) {
        return null
    }
}