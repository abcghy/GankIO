package tech.plateau.gankio.retrofit

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by sakura on 2017/12/3.
 */
interface ClientService {
    @POST("a/login")
    fun logIn(@Body loginRequest: LoginRequest): Observable<Response<Void>>
}