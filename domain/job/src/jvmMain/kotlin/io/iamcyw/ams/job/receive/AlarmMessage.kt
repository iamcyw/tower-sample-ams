package io.iamcyw.ams.job.receive

import kotlinx.serialization.json.*

fun JsonElement.toKV(): Any? {
    return when (this) {
        is JsonObject -> {
            val jsonObj = mutableMapOf<String, Any?>()
            this.jsonObject.forEach { key, value ->
                jsonObj[key] = value.toKV()
            }
            jsonObj
        }
        JsonNull -> null
        is JsonArray -> {
            val jsonObj = mutableListOf<Any?>()
            this.jsonArray.forEach {
                jsonObj.add(it.toKV())
            }
            jsonObj
        }
        is JsonPrimitive -> {
            this.jsonPrimitive.contentOrNull
        }
    }
}

