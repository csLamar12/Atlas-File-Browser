package Model;

import com.azure.identity.*;
import com.azure.storage.blob.*;
import com.azure.storage.blob.models.BlobItem;

public class AzureConnection {

        private BlobServiceClient blobServiceClient;

        public AzureConnection() {
            InteractiveBrowserCredential credential = new InteractiveBrowserCredentialBuilder()
                    .clientId("733b4657-8943-4734-b057-83f1d39d84a9")
                    .redirectUrl("http://localhost:11142")
                    .build();

            blobServiceClient = new BlobServiceClientBuilder()
                    .endpoint("https://atlas1234.blob.core.windows.net")
                    .credential(credential)
                    .buildClient();
        }

        public void listContainersAndBlobs() {
            blobServiceClient.listBlobContainers().forEach(container -> {
                System.out.println("Container: " + container.getName());
                BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(container.getName());
                containerClient.listBlobs().forEach(blob -> {
                    System.out.println(" - Blob: " + blob.getName());
                });
            });
        }

        public static void main(String[] args) {
            AzureConnection explorer = new AzureConnection();
            explorer.listContainersAndBlobs();
        }
}
