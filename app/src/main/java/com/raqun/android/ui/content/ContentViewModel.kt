package com.raqun.android.ui.content

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raqun.android.data.DataBean
import com.raqun.android.data.source.ResourceRepository
import com.raqun.android.extensions.getError
import com.raqun.android.model.Content
import com.raqun.android.model.UiDataBean
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class ContentViewModel @Inject constructor(private val resourceRepository: ResourceRepository)
    : ViewModel() {

    private val contentLiveData = MutableLiveData<DataBean<Content>>()

    private var contentId = -1

    fun getContentLiveData() = contentLiveData

    fun setContentId(contentId: Int) {
        if (this.contentId == contentId) {
            return
        }

        this.contentId = contentId
        viewModelScope.launch {
            try {
                val content = resourceRepository.getContent(contentId)
                contentLiveData.value = UiDataBean.success(content)
            } catch (e: Exception) {
                contentLiveData.value = UiDataBean.error(null, e.getError())
            }
        }
    }
}
