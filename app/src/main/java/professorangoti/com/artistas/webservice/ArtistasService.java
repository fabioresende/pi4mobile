package professorangoti.com.artistas.webservice;


import java.util.List;

import professorangoti.com.artistas.modelo.Artista;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ArtistasService {

    @GET("artistas")
    Call<List<Artista>> todos();

    @GET("artistas/{id}")
    Call<Artista> getArtista(@Path("id") int id);

    @POST("artistas")
    Call<Artista> criar(@Body Artista artista);

    @DELETE("artistas/{id}")
    Call<Void> deletar(@Path("id") int id);

    @PUT("artistas/{id}")
    Call<Void> atualizar(@Path("id") int id, @Body Artista artista);



}
