// Copyright (C) 2024, Henry Addo
// SPDX-License-Identifier: Apache-2.0
package com.addhen.job.search.data.model

import com.addhen.job.search.createTemplate
import java.time.LocalDate

data class JobPost(
  val title: String,
  val company: String,
  val location: String,
  val date: LocalDate,
  val moment: String,
  val url: String,
)

fun List<JobPost>.markdown() = createTemplate(this)
