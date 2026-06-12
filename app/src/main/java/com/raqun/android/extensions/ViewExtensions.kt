package com.raqun.android.extensions

import android.app.ProgressDialog
import android.content.Context
import androidx.annotation.DrawableRes
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.raqun.android.R
import com.raqun.android.binding.ImageBindingAdapter
import com.raqun.android.data.DataState
import com.raqun.android.util.DividerDecorator
import com.squareup.picasso.Picasso
import java.net.MalformedURLException
import java.net.URL

/**
 * Created by tyln on 12/10/2017.
 */
fun RecyclerView.setup(context: Context, orientation: Int = LinearLayoutManager.VERTICAL) {
    val layoutManager = LinearLayoutManager(context)
    layoutManager.orientation = orientation
    this.layoutManager = layoutManager
    this.setHasFixedSize(true)
}

fun RecyclerView.decorate() {
    this.addItemDecoration(DividerDecorator(this.context))
}

fun BottomNavigationView.disableShiftingMode() {
    labelVisibilityMode = BottomNavigationView.LABEL_VISIBILITY_LABELED
}

fun ProgressDialog.init(state: DataState) {
    when (state) {
        DataState.FETCHING -> {
            if (!this.isShowing) this.show()
        }

        DataState.ERROR -> {
            if (this.isShowing) this.dismiss()
        }

        DataState.SUCCESS -> {
            if (this.isShowing) this.dismiss()
        }
    }
}

fun ImageView.loadImage(url: String?) {
    if (url == null || url.isEmpty()) {
        showEmptyImage()
    } else {
        Picasso.with(context.applicationContext)
                .load(url)
                .placeholder(R.drawable.ic_photo_camera)
                .error(R.drawable.ic_photo_camera)
                .into(this)
    }
}

fun ImageView.showEmptyImage() {
    setImageResource(R.drawable.ic_photo_camera)
}

fun EditText.pasteFromClipBoard() {
    var text = ""
    val manager: android.content.ClipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE)
            as android.content.ClipboardManager
    manager.primaryClip?.let {
        val item = it.getItemAt(0)
        text = item.text.toString()
    }

    if (!TextUtils.isEmpty(text)) setText(text)
}

fun EditText.clear() {
    setText("")
}

fun EditText.getUrl(): String? {
    return try {
        URL(text.toString())
        text.toString()
    } catch (e: MalformedURLException) {
        context.alert(context.getString(R.string.error_message_product_url))
        null
    }
}

fun CoordinatorLayout.snackThat(meesage: CharSequence,
                                buttonText: CharSequence,
                                singleShot: View.OnClickListener?) {
    val sb = Snackbar.make(this, meesage, Snackbar.LENGTH_INDEFINITE)
    if (singleShot != null) {
        sb.setAction(buttonText, singleShot)
    } else {
        sb.setAction(buttonText) { sb.dismiss() }
    }
    sb.show()
}
