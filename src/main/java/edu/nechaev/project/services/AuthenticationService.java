package edu.nechaev.project.services;

import edu.nechaev.project.dto.AuthenticationRequest;
import edu.nechaev.project.dto.AuthenticationResponse;
import edu.nechaev.project.dto.MemberPost;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

public interface AuthenticationService {
    AuthenticationResponse login(AuthenticationRequest request);
    @Transactional
    AuthenticationResponse register(MemberPost member, MultipartFile multipartFile);

    AuthenticationResponse refreshToken(@NonNull String refreshToken);
}
