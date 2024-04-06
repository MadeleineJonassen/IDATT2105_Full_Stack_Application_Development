package edu.ntnu.idatt2105.service;

import edu.ntnu.idatt2105.dto.UserDTO;
import edu.ntnu.idatt2105.model.User;
import edu.ntnu.idatt2105.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TokenService {

  private final JwtEncoder jwtEncoder;
  private final JwtDecoder jwtDecoder;
  private final UserRepository userRepository;

  public TokenService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder, UserRepository userRepository) {
    this.jwtEncoder = jwtEncoder;
    this.jwtDecoder = jwtDecoder;
    this.userRepository = userRepository;
  }

  public String generateJwt(Authentication auth) {
    Instant now = Instant.now();
    Instant expiresAt = now.plusSeconds(300);

    String scope = auth.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));

    JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .subject(auth.getName())
            .claim("roles", scope)
            .expiresAt(expiresAt)
            .build();

    return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }

  public void verifyToken(String token) {
    try {
      jwtDecoder.decode(token);
    } catch (JwtException e) {
      throw new IllegalArgumentException("Invalid token, could not decode. Message:", e);
    }
  }

  public String getUsernameFromToken(String token) {
    Jwt jwt = jwtDecoder.decode(token);
    return jwt.getSubject();
  }

  public UserDTO getUserDTOFromToken(String token) {
    UserDTO userDTO = new UserDTO();
    Jwt jwt = jwtDecoder.decode(token);
    if (Optional.ofNullable(jwt.getSubject()).isEmpty()) {
      throw new IllegalArgumentException("Invalid token, could not decode");
    } else {
      User user = userRepository.findByUsername(jwt.getSubject())
          .orElseThrow(() -> new IllegalArgumentException("User not found"));
      userDTO.setId(user.getId());
      userDTO.setUsername(user.getUsername());
      userDTO.setPassword(user.getPassword());
    }
    return userDTO;
  }
}

