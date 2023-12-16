package edu.nechaev.project.controllers;

import edu.nechaev.project.services.MemberService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/image")
@AllArgsConstructor
public class ImageController {
    private MemberService memberServiceImpl;
    @GetMapping
    public ResponseEntity<byte[]> getImage(@RequestParam long id) throws IOException {
        String imagePath = memberServiceImpl.getImage(id);
        Path path = Paths.get(imagePath);

        byte[] imageBytes = Files.readAllBytes(path);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .contentLength(imageBytes.length)
                .body(imageBytes);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handle(IOException authenticationException) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(authenticationException.getLocalizedMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @SneakyThrows
    public ResponseEntity<String> handle(RuntimeException authenticationException) {
//        byte[] imageBytes = Files.readAllBytes(Paths.get("D:\\Project\\pfps\\null.jpg"));
//        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
//                .contentLength(imageBytes.length)
//                .body(imageBytes);
        return ResponseEntity.notFound().build();
    }
}
