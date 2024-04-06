package edu.ntnu.idatt2105.utils;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.stereotype.Component;

/**
 * Component class for managing RSA key pair properties.
 */
@Component
public class RSAKeyProperties {

  private RSAPublicKey publicKey;
  private RSAPrivateKey privateKey;

  /**
   * Constructs an RSAKeyProperties object and generates an RSA key pair.
   */
  public RSAKeyProperties() {
    KeyPair keyPair = KeyGeneratorUtility.generateRSAKey();
    publicKey = (RSAPublicKey) keyPair.getPublic();
    privateKey = (RSAPrivateKey) keyPair.getPrivate();
  }

  /**
   * Gets the RSA public key.
   *
   * @return The RSA public key.
   */
  public RSAPublicKey getPublicKey() {
    return publicKey;
  }

  /**
   * Sets the RSA public key.
   *
   * @param publicKey The RSA public key to set.
   */
  public void setPublicKey(RSAPublicKey publicKey) {
    this.publicKey = publicKey;
  }

  /**
   * Gets the RSA private key.
   *
   * @return The RSA private key.
   */
  public RSAPrivateKey getPrivateKey() {
    return privateKey;
  }

  /**
   * Sets the RSA private key.
   *
   * @param privateKey The RSA private key to set.
   */
  public void setPrivateKey(RSAPrivateKey privateKey) {
    this.privateKey = privateKey;
  }
}
