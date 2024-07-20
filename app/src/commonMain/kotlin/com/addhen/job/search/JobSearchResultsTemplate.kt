package com.addhen.job.search

import com.addhen.job.search.data.model.JobPost

fun createTemplate(jobPosts: List<JobPost>): String {
    val title = "# Job Search Results\r\n\r\n"
  return title + """
      ${jobPosts.joinToString("\r\n\r\n") { jobPost ->
      """
        **Title:** ${jobPost.title} ${"\r\n"}
        **Company:** ${jobPost.company} ${"\r\n"}
        **Location:** ${jobPost.location} ${"\r\n"}
        **Date:** ${jobPost.date} ${"\r\n"}
        [Apply](${jobPost.url})
        ${"\r\n\r\n"}
          """.trimIndent()
      }}
  """.trimIndent()
}