package com.impax.mgeni.retrofit

import com.impax.mgeni.models.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface ServiceInterface
{

    @POST("CheckIn/CheckinVisitors")
    fun CheckinUser( @Body info: CheckinDetails): Call<ResponseBody>

    @POST("Visitors/saveVisitors")
    fun saveVisitor( @Body info: Visitor_details): Call<ResponseBody>

    @GET("Visitors/GetVisitorDetails/{IdNumber}")
    fun getVisitorsById(@Path("IdNumber") id: String?): Call<visitorView>

    @GET("Visitors/GetCheckInData/{tenant_id}")
    fun getCheckInData( @Path("tenant_id") id: String? ): Call<List<visitorView>>

    @GET("Visitors/GetCheckOutData/{tenant_id}")
    fun getCheckOutData(@Path("tenant_id") id: String?): Call<List<visitorView>>

    @GET("Visitors/GetVisitsData/{tenant_id}")
    fun getVisitsData(@Path("tenant_id") id: String?): Call<List<visitorView>>

    @GET("Visitors/GetCheckInDetails/{IdNumber}")
    fun getCheckInsById(@Path("IdNumber") id: String?): Call<visitorView>

    @GET("User/GetUsersByEmail/{Email}")
    fun getUsersByEmail(@Path("Email") Email: String?): Call<users>?

    @GET("User/Login/{Email}/{Password}")
    fun login( @Path("Email") Email: String?,@Path("Password") Password: String?): Call<User_Model>?

    @PUT("CheckIn/CheckOut/{IdNumber}/{CheckOutTime}")
    fun checkout( @Path("IdNumber") IdNumber: String?,@Path("CheckOutTime") CheckOutTime: String?): Call<CheckinDetails>?

    @PUT("User/UpdatePassword/{email}/{password}/{otp}")
    @FormUrlEncoded
    fun updatePassword(@Path("email") email: String?,@Path("password") password: String?, @Field("otp") otp: String? ): Call<users>?


}