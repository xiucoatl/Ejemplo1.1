package com.example.ejemplo11

import com.google.gson.annotations.SerializedName

data class Resultado(
    @SerializedName("title") var title: String,
    @SerializedName("url") var url: String,
    @SerializedName("published_date") var published_date: String,
    @SerializedName("media") var media: List<Media>,
)

data class Media(
    @SerializedName("media-metadata") var mediaMetadata: List<MediaData>
)

data class MediaData(
    @SerializedName("url") var urlMetadata: String
)



