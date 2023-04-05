package com.impax.mgeni.ocr

import com.impax.mgeni.ocr.camera.IdData


interface MRZResponse{

    fun cardResponse(card: IdData)

    fun cardReadResponse()

    fun failedToRead()
}