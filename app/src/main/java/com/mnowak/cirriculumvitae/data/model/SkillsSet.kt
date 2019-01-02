package com.mnowak.cirriculumvitae.data.model

data class SkillsSet(
        val id: Int,
        val label: String,
        val labelColor: String,
        val goodLevelSkills: List<String>,
        val mediumLevelSkills: List<String>,
        val lowLevelSkills: List<String>
)
