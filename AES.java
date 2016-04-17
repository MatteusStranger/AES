import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

import java.util.Scanner;

import javax.crypto.Cipher;

public class AES {

	static String IV = "AAAAAAAAAAAAAAAA";
	static String textopuro = "0000 0111 0011 1000 less ecb $$$)";
	static String chaveencriptacao = "0123456789abcdef";

	public static void main(String[] args) {

		try {
			Scanner le = new Scanner(System.in);

			System.out.print("Entre com o texto: ");
			textopuro = le.next();
			System.out.println();

			System.out.print("Entre com a chave: ");
			chaveencriptacao = le.next();
			System.out.println();

			System.out.println("Texto Puro: " + textopuro);
			System.out.println("Chave: " + chaveencriptacao);

			byte[] textoencriptado = encrypt(textopuro, chaveencriptacao);

			System.out.print("Texto Encriptado em hexadecimal: ");

			for (int i = 0; i < textoencriptado.length; i++)
				System.out.print(Integer.toHexString(textoencriptado[i]) + " ");

			System.out.println();

			String textodecriptado = decrypt(textoencriptado, chaveencriptacao);

			System.out.println("Texto Decriptado novamente: " + textodecriptado);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static byte[] encrypt(String textopuro, String chaveencriptacao) throws Exception {
		Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
		encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
		return encripta.doFinal(textopuro.getBytes("UTF-8"));
	}

	public static String decrypt(byte[] textoencriptado, String chaveencriptacao) throws Exception {
		Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
		decripta.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
		return new String(decripta.doFinal(textoencriptado), "UTF-8");
	}

}