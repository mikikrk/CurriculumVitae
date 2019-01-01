package com.mnowak.cirriculumvitae.data.model

data class StudiesActivity(
        val id: Int,
        val name: String,
        val description: String,
        val iconUri: String,
        val importantEvents: Int,
        val events: List<Event>
) {

    inner class Event(
            val name: String,
            val description: String?,
            val function: String?
    )
}
