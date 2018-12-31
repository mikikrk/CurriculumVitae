package com.mnowak.cirriculumvitae.data.model

import java.io.Serializable

data class Candidate(
        override val name: String,
        override val position: String,
        override val birthDate: String,
        override val phone: String,
        override val email: String,
        override val location: String,
        override val photoUri: String,
        val experience: List<Company>,
        val activities: List<StudiesActivity>,
        val skills: List<SkillsSet>
) : PersonalInfo, Serializable
