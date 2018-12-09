package com.ichirotech.wisnu.Data


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Sensor(
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("nama")
        var nama: String? = null,
        @SerializedName("data")
        var data: String? = null,
        @SerializedName("satuan")
        var satuan: String? = null
) : Parcelable {
        constructor(source: Parcel) : this(
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString()
        )

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
                writeString(id)
                writeString(nama)
                writeString(data)
                writeString(satuan)
        }

        companion object {
                @JvmField
                val CREATOR: Parcelable.Creator<Sensor> = object : Parcelable.Creator<Sensor> {
                        override fun createFromParcel(source: Parcel): Sensor = Sensor(source)
                        override fun newArray(size: Int): Array<Sensor?> = arrayOfNulls(size)
                }
        }
}