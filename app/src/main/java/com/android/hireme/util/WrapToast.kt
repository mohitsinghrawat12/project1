package com.android.hireme.util

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.android.hireme.R

const val TOAST_ERROR_TYPE = 0
const val TOAST_SUCCESS_TYPE = 1

@SuppressLint("UseCompatLoadingForDrawables")
@RequiresApi(Build.VERSION_CODES.M)
fun Toast.showCustomToast(messageType: Int, message: String, activity: Activity) {
    val layout = activity.layoutInflater.inflate(
        R.layout.toast_layout,
        activity.findViewById(R.id.mainLayout)
    )
    val mainLayout = layout.findViewById<ConstraintLayout>(R.id.mainLayout)
    val toastStatusText = layout.findViewById<AppCompatTextView>(R.id.toastStatusText)
    val toastMessage = layout.findViewById<AppCompatTextView>(R.id.toastMessage)
    val toastStatusImage = layout.findViewById<AppCompatImageView>(R.id.toastStatusImage)

    if (messageType == TOAST_SUCCESS_TYPE) {
        toastStatusText.text = activity.getText(R.string.success_toast)
        mainLayout.setBackgroundColor(activity.getColor(R.color.sucess_color))
        toastStatusImage.background = activity.getDrawable(R.drawable.ic_success_check)
    } else {
        toastStatusText.text = activity.getText(R.string.error_toast)
        mainLayout.setBackgroundColor(activity.getColor(R.color.error_color))
        toastStatusImage.background = activity.getDrawable(R.drawable.ic_error_cross)
    }

    toastMessage.text = message

    this.apply {
        setGravity(Gravity.TOP, 0, 0)
        duration = Toast.LENGTH_LONG
        view = layout
        show()
    }
}