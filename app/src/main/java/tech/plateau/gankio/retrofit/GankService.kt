package tech.plateau.gankio.retrofit

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

public interface GankService {
    /**
     *
     */
    @GET("day/{year}/{month}/{day}")
    fun getDataByDay(@Path("year") year: Int, @Path("month") month: Int, @Path("day") day: Int): Observable<Response<Void>>

    /**
     * 分类数据
     */
    @GET("data/{category}/{count}/{page}")
    fun getDataByCategory(@Path("category") category: String, @Path("count") count: Int, @Path("page") page: Int): Observable<Response<List<CategoryResponse>>>

    /**
     *
     */
    @GET("random/{category}/{count}/{page}")
    fun getRandomData(@Path("category") category: String, @Path("count") count: Int, @Path("page") page: Int): Observable<Response<Void>>
}