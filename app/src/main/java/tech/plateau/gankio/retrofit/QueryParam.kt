package tech.plateau.gankio.retrofit

/**
 * Created by sakura on 2018/1/6.
 */
class QueryParam(var count: Int = 10, var page: Int = 1) {

    private var lastPage: Int? = null

    fun resetPage() {
        lastPage = page
        page = 1
    }

    fun resumePage() {
        if (lastPage != null) {
            page = lastPage!!
        }
    }

    fun nextPage() {
        page++
    }

    fun prePage() {
        page--
        if (page < 1) {
            page = 1
        }
    }
}