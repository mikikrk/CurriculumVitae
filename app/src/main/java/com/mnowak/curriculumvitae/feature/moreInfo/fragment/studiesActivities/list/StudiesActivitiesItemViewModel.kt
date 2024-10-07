package com.mnowak.curriculumvitae.feature.moreInfo.fragment.studiesActivities.list

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import androidx.annotation.ColorInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import com.mnowak.curriculumvitae.data.model.StudiesActivity
import com.mnowak.curriculumvitae.liveData.*

private const val DIVIDER = " - "

abstract class StudiesActivitiesItemViewModel : ViewModel() {

    abstract val id: LiveData<Int>
    abstract val name: LiveData<String>
    abstract val description: LiveData<String>
    abstract val iconUri: LiveData<String>
    abstract val importantEvents: LiveData<List<EventViewModel>>
    abstract val redundantEvents: LiveData<List<EventViewModel>>

    abstract val expanded: LiveData<Boolean>
    abstract val moreBtnRotation: LiveData<Int>

    abstract val redundantEventsVisibility: LiveData<Int>
    abstract val moreBtnVisibility: LiveData<Int>

    abstract fun setColors(@ColorInt primaryColor: Int, @ColorInt regularColor: Int, @ColorInt redundantColor: Int)
    abstract fun toggleExpanded()

    abstract inner class EventViewModel {

        abstract val eventText: LiveData<Spannable>
    }
}

class StudiesActivitiesItemViewModelImpl(

        private val studiesActivity: StudiesActivity

) : StudiesActivitiesItemViewModel() {

    override val id = InitializedLiveData(studiesActivity.id)
    override val name = InitializedLiveData(studiesActivity.name)
    override val description = InitializedLiveData(studiesActivity.description)
    override val iconUri = InitializedLiveData(studiesActivity.iconUri)
    override val importantEvents = InitializedLiveData(
            studiesActivity.events
                    .subList(0, studiesActivity.importantEvents)
                    .map { event ->
                        EventViewModelImpl(event) as EventViewModel
                    })
    override val redundantEvents = InitializedLiveData(
            studiesActivity.events
                    .subList(studiesActivity.importantEvents, studiesActivity.events.size)
                    .map { event ->
                        EventViewModelImpl(event) as EventViewModel
                    })

    override val expanded = InitializedLiveData(false)
    override val moreBtnRotation = expanded.map {
        if (it == true) 180 else 0
    }

    override val redundantEventsVisibility = expanded.map {
        if (it == true) View.VISIBLE else View.GONE
    }
    override val moreBtnVisibility = redundantEvents.map {
        if (it?.size ?: 0 > 0) View.VISIBLE else View.GONE
    }

    override fun toggleExpanded() {
        expanded.postValue(expanded.value?.not())
    }

    override fun setColors(@ColorInt primaryColor: Int, @ColorInt regularColor: Int, @ColorInt redundantColor: Int) {
        this.primaryColor.postValue(primaryColor)
        this.regularColor.postValue(regularColor)
        this.redundantColor.postValue(redundantColor)
    }

    private val primaryColor = MutableLiveData<Int>()
    private val regularColor = MutableLiveData<Int>()
    private val redundantColor = MutableLiveData<Int>()
    private val spannableColors by lazy {
        combine(primaryColor, regularColor, redundantColor) { primaryColor, regularColor, redundantColor ->
            Triple(primaryColor, regularColor, redundantColor)
        }
    }

    inner class EventViewModelImpl(

            private val event: StudiesActivity.Event

    ) : EventViewModel() {

        private val reducedText by lazy {
            spannableColors.map {
                prepareSpannableText(expanded = false)
            }
        }
        private val expandedText by lazy {
            spannableColors.map {
                prepareSpannableText(expanded = true)
            }
        }

        override val eventText by lazy {
            expanded.switchMap {
                if (it == true) expandedText else reducedText
            }
        }

        private fun prepareSpannableText(expanded: Boolean): Spannable {
            val displayedString = buildDisplayedString(expanded)
            val spannable = SpannableString(displayedString)
            val nameEnd = event.name.length
            val descriptionWithDividerLength = event.description?.length?.let { it + DIVIDER.length } ?: 0
            val descriptionEnd = if (!event.description.isNullOrEmpty() || !expanded) nameEnd else nameEnd + descriptionWithDividerLength
            setSpans(displayedString, spannable, nameEnd, descriptionEnd)
            return spannable
        }

        private fun buildDisplayedString(expanded: Boolean): String =
                buildString {
                    append(event.name)
                    if (expanded) {
                        appendStringAfterPauseIfNotEmpty(event.description)
                    }
                    appendStringAfterPauseIfNotEmpty(event.function)
                }

        private fun StringBuilder.appendStringAfterPauseIfNotEmpty(string: String?) {
            if (!string.isNullOrEmpty()) {
                append(DIVIDER)
                append(string)
            }
        }

        private fun setSpans(displayedString: String, spannable: Spannable, nameEnd: Int, descriptionEnd: Int) {
            spannable.setSpan(StyleSpan(Typeface.BOLD), 0, nameEnd, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
            primaryColor.value?.let { primaryColor -> spannable.setSpan(ForegroundColorSpan(primaryColor), 0, nameEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) }
            redundantColor.value?.let { redundantColor -> spannable.setSpan(ForegroundColorSpan(redundantColor), nameEnd, descriptionEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) }
            regularColor.value?.let { regularColor -> spannable.setSpan(ForegroundColorSpan(regularColor), descriptionEnd, displayedString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) }
        }
    }

    override fun equals(other: Any?): Boolean =
            if (other is StudiesActivitiesItemViewModelImpl) {
                studiesActivity == other.studiesActivity
            } else {
                super.equals(other)
            }

    override fun hashCode(): Int {
        return studiesActivity.hashCode()
    }
}
