package client;

import java.io.IOException;

import com.gc.cipher.CipherGrpc;
import com.gc.cipher.CipherGrpc.CipherBlockingStub;
import com.gc.cipher.CipherOuterClass.DecryptReply;
import com.gc.cipher.CipherOuterClass.DecryptRequest;
import com.gc.cipher.CipherOuterClass.EncryptReply;
import com.gc.cipher.CipherOuterClass.EncryptRequest;
import com.gc.cipher.CipherOuterClass.KeyReply;
import com.gc.cipher.CipherOuterClass.KeyRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {

	public static void main(String[] args) throws InterruptedException, IOException {
		String contentString = "Hello, I am Guanchen.";

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext(true).build();

		CipherBlockingStub cipherStub = CipherGrpc.newBlockingStub(channel);

		KeyRequest keyRequest = KeyRequest.newBuilder().build();
		KeyReply reply = cipherStub.getKeys(keyRequest);

		System.out.println("------------- get key ------------");
		System.out.println("PrivateKey:" + reply.getPrivateKey());
		System.out.println("PublicKey:" + reply.getPublicKey());

		String privateKey = reply.getPrivateKey();

		String publicKey = reply.getPublicKey();
		System.out.println("------------- origin string ------------");
		System.out.println("");
		System.out.println(contentString);
		System.out.println("");
		System.out.println("------------- encrypt ------------");
		System.out.println("");
		EncryptRequest encryptRequest = EncryptRequest.newBuilder().setContent(contentString).setKey(privateKey)
				.build();
		EncryptReply encryptReply = cipherStub.encrypt(encryptRequest);

		String encryptedStr = encryptReply.getEncryptedStr();
		System.out.println("encryptedStr:" + encryptedStr);

		System.out.println("");
		System.out.println("------------- decrypt ------------");

		DecryptRequest decryptRequest = DecryptRequest.newBuilder().setContent(encryptedStr).setKey(publicKey).build();

		DecryptReply decryptReply = cipherStub.decrypt(decryptRequest);
		String decryptedStr = decryptReply.getDecryptedStr();
		System.out.println("");
		System.out.println("decryptedStr:" + decryptedStr);

	}

}
