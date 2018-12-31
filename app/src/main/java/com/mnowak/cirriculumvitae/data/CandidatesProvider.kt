package com.mnowak.cirriculumvitae.data

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.mnowak.cirriculumvitae.R
import com.mnowak.cirriculumvitae.data.model.*
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CandidatesProvider @Inject constructor(

        private val application: Application

) {

    @Inject
    lateinit var gson: Gson

    private val context: Context
        get() = application

    private val candidate: Candidate by lazy {
        context.resources.openRawResource(R.raw.personal_info).use { rawStream ->
            BufferedReader(InputStreamReader(rawStream)).use { bufferedReader ->
                gson.fromJson(bufferedReader, Candidate::class.java)
            }
        }
    }

    private val personalInfo: PersonalInfo by lazy {
        candidate
    }

    private val experience: List<CandidateJob> by lazy {
        candidate.experience
    }

    private val studiesActivities: List<StudiesActivity> by lazy {
        candidate.activities
    }

    private val skills: List<SkillsSet> by lazy {
        candidate.skills
    }
}
