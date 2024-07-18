package com.addhen.job.search

import com.addhen.job.search.data.model.JobPost
import com.addhen.job.search.data.model.JobSearchConfig

interface JobSearch {
    fun authenticate(email: String, password: String)
    fun search(config: JobSearchConfig): List<JobPost>
}