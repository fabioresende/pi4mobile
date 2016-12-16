package professorangoti.com.artistas.webservice;


import java.util.List;

import professorangoti.com.artistas.modelo.Curso;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CursoService {

    @GET("cursos")
    Call<List<Curso>> todos();

    @GET("cursos/{id}")
    Call<Curso> getCurso(@Path("id") int id);

    @POST("cursos")
    Call<Curso> criar(@Body Curso curso);

    @DELETE("cursos/{id}")
    Call<Void> deletar(@Path("id") int id);

    @PUT("cursos/{id}")
    Call<Void> atualizar(@Path("id") int id, @Body Curso curso);



}
