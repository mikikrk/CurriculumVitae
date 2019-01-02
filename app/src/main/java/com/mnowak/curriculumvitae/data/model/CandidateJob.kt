package com.mnowak.curriculumvitae.data.model

data class CandidateJob(
        val id: Int,
        val name: String,
        val employmentTime: String,
        val position: String,
        val description: String,
        val iconUri: String
)
