package com.impax.mgeni.ocr

import com.impax.mgeni.ocr.camera.IdData


interface CardResult {
    fun cardDetails(card: IdData)
}