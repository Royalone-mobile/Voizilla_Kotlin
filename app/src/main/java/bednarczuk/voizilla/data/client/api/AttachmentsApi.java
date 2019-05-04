package bednarczuk.voizilla.data.client.api;


import java.util.UUID;

import bednarczuk.voizilla.data.client.model.ObjectId;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface AttachmentsApi {
  /**
   * Attachment download
   * 
   * @param attachmentId Attachment id (required)
   * @return Call&lt;Void&gt;
   */
  @GET("attachments/{attachmentId}")
  Call<Void> download(
          @retrofit2.http.Path("attachmentId") UUID attachmentId
  );

  /**
   * Attachment upload
   *
   * @param file file (required)
   * @return Call&lt;ObjectId&gt;
   */
  @retrofit2.http.Multipart
  @POST("attachments")
  Call<ObjectId> upload(
          @retrofit2.http.Part("file") MultipartBody.Part file
  );

}
