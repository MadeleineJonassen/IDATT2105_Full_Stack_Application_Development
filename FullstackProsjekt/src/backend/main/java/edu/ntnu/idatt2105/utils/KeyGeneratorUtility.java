package edu.ntnu.idatt2105.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

/**
 * Utility class for generating RSA keys.
 */
public class KeyGeneratorUtility {

  /**
   * Generates an RSA key pair.
   *
   * @return The generated RSA key pair.
   */
  public static KeyPair generateRSAKey() {
    KeyPair keyPair;

    try {
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      keyPairGenerator.initialize(2048);
      keyPair = keyPairGenerator.generateKeyPair();
    } catch (Exception e) {
      throw new IllegalStateException("Failed to generate RSA key pair.", e);
    }

    return keyPair;
  }
}
