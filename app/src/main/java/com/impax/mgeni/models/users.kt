package com.impax.mgeni.models

data class users(
    val id: String,
    val username: String,
    val password: String,
    val first_name: String,
    val last_name: String,
    val email: String,
    val phone_number: String,
    val otp: String,
    val employee_code: String,
    val firebase_token:  String,
    val gender: String,
    val create_date: String,
    val update_date: String,
    val created_by: String,
    val tenant_id: String,
    val status:  Boolean
)
