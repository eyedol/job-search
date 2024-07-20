// Copyright (C) 2024, Henry Addo
// SPDX-License-Identifier: Apache-2.0
package com.addhen.job.search

import com.addhen.job.search.data.model.JobPost
import com.addhen.job.search.data.model.JobSearchConfig

interface JobSearch {
  suspend fun authenticate(email: String, password: String)

  suspend fun search(config: JobSearchConfig): List<JobPost>
}
