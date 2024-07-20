package com.addhen.job.search.data.model

fun createTemplate(jobPosts: List<JobPost>): String {
    val title = "# Job Search Results\r\n\r\n"
  return title + """
      ${jobPosts.joinToString("\r\n\r\n") { jobPost ->
      """
        **Title:** ${jobPost.title}
        **Company:** ${jobPost.company}
        **Location:** ${jobPost.location}
        **Date:** ${jobPost.date}
        **Description:** ${jobPost.description}
        [Apply](${jobPost.url})
          """.trimIndent()
      }}
  """.trimIndent()
}