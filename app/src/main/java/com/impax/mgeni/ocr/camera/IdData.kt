package com.impax.mgeni.ocr.camera

import java.io.Serializable

data class IdData(
    var firstName: String,
    var middleName: String,
    var lastName: String,
    var gender:String,
    var documentNo:String,
    var dateOfBirth:String,
    var idNo: String,
    var nationality:String,
):Serializable