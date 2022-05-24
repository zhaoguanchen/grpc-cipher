package service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.gc.cipher.CipherGrpc.CipherImplBase;
import com.gc.cipher.CipherOuterClass.DecryptReply;
import com.gc.cipher.CipherOuterClass.DecryptRequest;
import com.gc.cipher.CipherOuterClass.EncryptReply;
import com.gc.cipher.CipherOuterClass.EncryptRequest;
import com.gc.cipher.CipherOuterClass.KeyReply;
import com.gc.cipher.CipherOuterClass.KeyRequest;

import io.grpc.stub.StreamObserver;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

//The RSA algorithm (Rivest-Shamir-Adleman) is the basis of a cryptosystem -- a suite of cryptographic algorithms that are used for specific security services or purposes -- which enables public key encryption and is widely used to secure sensitive data, particularly when it is being sent over an insecure network such as the internet.
//
//RSA was first publicly described in 1977 by Ron Rivest, Adi Shamir and Leonard Adleman of the Massachusetts Institute of Technology, though the 1973 creation of a public key algorithm by British mathematician Clifford Cocks was kept classified by the U.K.'s GCHQ until 1997.
//
//Public key cryptography, also known as asymmetric cryptography, uses two different but mathematically linked keys -- one public and one private. The public key can be shared with everyone, whereas the private key must be kept secret.

/**
 * RSA service
 * 
 * The RSA algorithm (Rivest-Shamir-Adleman) is the basis of a cryptosystem -- a
 * suite of cryptographic algorithms that are used for specific security
 * services or purposes -- which enables public key encryption and is widely
 * used to secure sensitive data, particularly when it is being sent over an
 * insecure network such as the internet.
 * 
 * RSA was first publicly described in 1977 by Ron Rivest, Adi Shamir and
 * Leonard Adleman of the Massachusetts Institute of Technology, though the 1973
 * creation of a public key algorithm by British mathematician Clifford Cocks
 * was kept classified by the U.K.'s GCHQ until 1997.
 * 
 * Public key cryptography, also known as asymmetric cryptography, uses two
 * different but mathematically linked keys -- one public and one private. The
 * public key can be shared with everyone, whereas the private key must be kept
 * secret. In RSA cryptography, both the public and the private keys can encrypt
 * a message. The opposite key from the one used to encrypt a message is used to
 * decrypt it. This attribute is one reason why RSA has become the most widely
 * used asymmetric algorithm: It provides a method to assure the
 * confidentiality, integrity, authenticity, and non-repudiation of electronic
 * communications and data storage.
 * 
 * Many protocols, including Secure Shell (SSH), OpenPGP, S/MIME, and SSL/TLS,
 * rely on RSA for encryption and digital signature functions. It is also used
 * in software programs -- browsers are an obvious example, as they need to
 * establish a secure connection over an insecure network, like the internet, or
 * validate a digital signature. RSA signature verification is one of the most
 * commonly performed operations in network-connected systems.
 * 
 * workflow: plain test -- > public key encryption -- >cipher test -- >private
 * key decryption -- >plain text
 * 
 * @author zhaoguanchen
 *
 */
public class CipherService extends CipherImplBase {

	public static final String KEY_ALGORITHM = "RSA";
	public static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
	public static final String PUBLIC_KEY = "publicKey";
	public static final String PRIVATE_KEY = "privateKey";
	public static final Integer KEY_SIZE = 1024;

	/**
	 * get public and private key
	 */
	@Override
	public void getKeys(KeyRequest request, StreamObserver<KeyReply> responseObserver) {
		Map<String, byte[]> keyMap = getKeyMap();

		// get publicKey
		String publicKeyStr = encryptBASE64(keyMap.get(PUBLIC_KEY));
		System.out.println("publicKeyStr:" + publicKeyStr);

		// get privateKey

		String privateKeyStr = encryptBASE64(keyMap.get(PRIVATE_KEY));
		System.out.println("privateKeyStr: " + privateKeyStr);

		KeyReply.Builder reply = KeyReply.newBuilder();

		reply.setPrivateKey(privateKeyStr).setPublicKey(publicKeyStr);
		responseObserver.onNext(reply.build());
		responseObserver.onCompleted();
	}

	/**
	 * encrypt using private key
	 */
	@Override
	public void encrypt(EncryptRequest request, StreamObserver<EncryptReply> responseObserver) {

		String content = request.getContent();
		String keyStr = request.getKey();

		PrivateKey privateKey = restorePrivateKey(decryptBASE64(keyStr));
		try {
			byte[] encodedText = RSAEncode(privateKey, content.getBytes("UTF-8"));

			String privateResult = byteArrayToHexString(encodedText);

			EncryptReply reply = EncryptReply.newBuilder().setEncryptedStr(privateResult).build();
			responseObserver.onNext(reply);
			responseObserver.onCompleted();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * decrypt using public key
	 */
	@Override
	public void decrypt(DecryptRequest request, StreamObserver<DecryptReply> responseObserver) {
		String content = request.getContent();
		String keyStr = request.getKey();

		PublicKey publicKey = restorePublicKey(decryptBASE64(keyStr));
		String result = RSADecode(publicKey, hexStringToByte(content));

		DecryptReply reply = DecryptReply.newBuilder().setDecryptedStr(result).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	public static Map<String, byte[]> getKeyMap() {
		try {
			// RSA KeyPairGenerator
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
			// init KEY size
			keyPairGenerator.initialize(KEY_SIZE);
			// generate a KeyPair
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			// get public key
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			// get private key
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

			Map<String, byte[]> keyMap = new HashMap<String, byte[]>(2);
			keyMap.put(PUBLIC_KEY, publicKey.getEncoded());
			keyMap.put(PRIVATE_KEY, privateKey.getEncoded());
			return keyMap;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * PublicKey，X509EncodedKeySpec
	 *
	 * @param keyBytes
	 * @return
	 */
	public static PublicKey restorePublicKey(byte[] keyBytes) {
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);

		try {
			KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
			PublicKey publicKey = factory.generatePublic(x509EncodedKeySpec);
			return publicKey;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * PrivateKey，PKCS8EncodedKeySpec
	 *
	 * @param keyBytes
	 * @return
	 */
	public static PrivateKey restorePrivateKey(byte[] keyBytes) {

		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);

		KeyFactory factory;
		try {
			factory = KeyFactory.getInstance(KEY_ALGORITHM);
			PrivateKey privateKey = factory.generatePrivate(pkcs8EncodedKeySpec);
			return privateKey;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Encode
	 *
	 * @param key
	 * @param plainText
	 * @return
	 */
	public static byte[] RSAEncode(PrivateKey key, byte[] plainText) {

		try {
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return cipher.doFinal(plainText);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * decode
	 *
	 * @param key
	 * @param encodedText
	 * @return
	 */
	public static String RSADecode(PublicKey key, byte[] encodedText) {

		try {
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			return new String(cipher.doFinal(encodedText));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * String to byte[]
	 **/
	public static byte[] decryptBASE64(String key) {

		try {
			return (new BASE64Decoder()).decodeBuffer(key);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new byte[0];
	}

	/**
	 * byte[] to String
	 **/
	public static String encryptBASE64(byte[] key) {
		try {
			return (new BASE64Encoder()).encodeBuffer(key);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}

	private static String byteToHexString(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0F];
		ob[1] = Digit[ib & 0X0F];
		String s = new String(ob);
		return s;
	}

	private static String byteArrayToHexString(byte[] bytearray) {
		String strDigest = "";
		for (int i = 0; i < bytearray.length; i++) {
			strDigest += byteToHexString(bytearray[i]);
		}
		return strDigest;
	}

	public static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}

		return result;
	}

	public static int toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

}
