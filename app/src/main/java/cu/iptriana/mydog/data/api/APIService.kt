package cu.iptriana.mydog.data.api

import cu.iptriana.mydog.data.model.DogResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getDogsByBreads(@Url url:String): Response<DogResponse>
}