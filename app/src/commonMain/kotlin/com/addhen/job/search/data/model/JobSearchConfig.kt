package com.addhen.job.search.data.model

data class JobSearchConfig(
    val jobTitles: List<String> = listOf("Senior Android Developer", "Staff Android Developer"),
    val locations: List<String> = listOf("Remote", "Seattle, WA"),
    val workplaceTypes: List<String> = listOf("Remote", "Hybrid"),
    val datePosted: String = "Past 24 hours",
    val maxResults: Int = 10
)
