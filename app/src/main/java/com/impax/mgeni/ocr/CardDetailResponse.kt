package com.impax.mgeni.ocr

import com.impax.mgeni.ocr.camera.IdData

interface CardDetailResponse {
    fun onCardRead(card: IdData)

    fun onCardReadingCancelled()

    fun onFailed(e: Exception)
}