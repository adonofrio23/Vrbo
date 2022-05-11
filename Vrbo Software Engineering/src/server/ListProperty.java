package server;

import java.util.HashMap;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.StorageException;
import com.google.firebase.cloud.StorageClient;

public class ListProperty {
	public static void list(String address, String beds, String baths, String amenities, String description, String price, String city, byte[] picture) {
		Bucket bucket = StorageClient.getInstance().bucket();
		String fileName = address + ".png";
		BlobId blobId = BlobId.of("vrbo-server.appspot.com", fileName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("png").setContentEncoding("utf-8").build();
		String link = "https://storage.googleapis.com/vrbo-server.appspot.com/" + fileName.replaceAll(" ", "%20");
		try {
			Blob blob = bucket.getStorage().create(blobInfo, picture);
			blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
		} catch (StorageException e) {
			e.printStackTrace();
		}
		Map<String, Object> data = new HashMap<>();
		data.put("address", address);
		data.put("beds", beds);
		data.put("baths", baths);
		data.put("amenities", amenities);
		data.put("description", description);
		data.put("price", price);
		data.put("link", link);
		
		ApiFuture<WriteResult> resp = Firebase.db.collection("rentals")
				.document(city)
				.collection(city)
				.document(address)
				.set(data, SetOptions.merge());
		ApiFutures.addCallback(resp, new ApiFutureCallback<WriteResult>() {
			@Override
		    public void onSuccess(WriteResult result) {
		    	createResult(result, true);
		    }

		    @Override
		    public void onFailure(Throwable t) {
		    	System.out.println("[Firebase] - " + t);
		    	createResult(null, false);
		    }
		}, Runnable::run);
	}
	
	private static void createResult(WriteResult result, boolean success) {
		if (success) {
			Server.sendMessage("SUCCESS");
		} else {
			Server.sendMessage("FAILED");
		}
	}
}
