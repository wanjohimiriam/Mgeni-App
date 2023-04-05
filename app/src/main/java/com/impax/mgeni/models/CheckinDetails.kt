package com.impax.mgeni.models

import java.util.*

data class CheckinDetails(
    val IdNumber: String,
    val VisitorType: String,
    val CheckInMode: String,
    val AssetName: String,
    val AssetBarcode: String,
    val AssetSerialNo: String,
    val HostDepartment: String,
    val HostName: String,
    val Reason: String,
    val tenant_id: String,
    val Created_by:  String
)

