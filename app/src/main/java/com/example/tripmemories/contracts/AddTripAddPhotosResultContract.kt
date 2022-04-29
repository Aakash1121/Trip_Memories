package com.example.tripmemories.contracts

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract

class AddTripAddPhotosResultContract : ActivityResultContract<String, ArrayList<Uri>>() {

    override fun createIntent(context: Context, input: String?): Intent {
        var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): ArrayList<Uri> {
        val imageList = ArrayList<Uri>()
        val count = intent!!.clipData?.itemCount?.minus(1)

        if (count != null) {
            for (i in 0..count) {
                imageList.add(intent.clipData?.getItemAt(i)!!.uri)
            }
        }else{
            imageList.add(intent.data!!)
        }
        return imageList
    }

}