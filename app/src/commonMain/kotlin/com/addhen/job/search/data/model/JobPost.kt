// Copyright (C) 2024, Henry Addo
// SPDX-License-Identifier: Apache-2.0
package com.addhen.job.search.data.model

import java.time.LocalDateTime

data class JobPost(
    val title: String,
    val company: String,
    val location: String,
    val date: LocalDateTime,
    val description: String,
    val url: String
)
