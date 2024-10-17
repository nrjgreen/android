package nl.nrjhub.companion.android.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import nl.nrjhub.companion.android.R
import nl.nrjhub.companion.android.databinding.ViewLoadingBinding

class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    init {
        val binding = ViewLoadingBinding.inflate(LayoutInflater.from(context), this, true)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.LoadingView)
        binding.loadingText.text = attributes.getString(R.styleable.LoadingView_loading_text)
        attributes.recycle()
    }
}
