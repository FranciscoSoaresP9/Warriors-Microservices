package com.example.resourceService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.FileTypeMap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


/**
 *Class to mapping Request and serve the resources
 */
@RestController
@RequestMapping(path = "resource/")
public class ImageRestController {

    @GetMapping("img/warriorRegist.jpg")
    public ResponseEntity<byte[]> getRegistImg() throws IOException {
        File img = new File("src/main/resources/templates/img/warriorRegist.jpg");
        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
    }

    @GetMapping("img/background.jpg")
    public ResponseEntity<byte[]> getRegisterBackfroundImg() throws IOException {
        File img = new File("src/main/resources/templates/img/background.jpg");
        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
    }

    @GetMapping("img/backgroundhomepage.jpg")
    public ResponseEntity<byte[]> getHomePageImg() throws IOException {
        File img = new File("src/main/resources/templates/img/backgroundhomepage.jpg");
        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
    }
    @GetMapping("img/backgroundhomepage2.jpg")
    public ResponseEntity<byte[]> getHomePageImg2() throws IOException {
        File img = new File("src/main/resources/templates/img/backgroundhomepage2.jpg");
        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
    }

    @GetMapping("img/backgroundhomepage3.jpg")
    public ResponseEntity<byte[]> getHomePageImg3() throws IOException {
        File img = new File("src/main/resources/templates/img/backgroundhomepage3.jpg");
        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
    }
    @GetMapping("img/backgroundform.jpg")
    public ResponseEntity<byte[]> getRegisterBackgroundFormImg() throws IOException {
        File img = new File("src/main/resources/templates/img/backgroundform.jpg");
        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
    }


    @GetMapping("img/archer.jpeg")
    public ResponseEntity<byte[]> getArcherImg() throws IOException {
        File img = new File("src/main/resources/templates/img/archer.jpeg");
        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
    }

    @GetMapping("img/assassin.jpeg")
    public ResponseEntity<byte[]> getAssasinImg() throws IOException {
        File img = new File("src/main/resources/templates/img/assasin.jpg");
        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
    }

    @GetMapping("img/warrior.jpeg")
    public ResponseEntity<byte[]> getWarriorImg() throws IOException {
        File img = new File("src/main/resources/templates/img/warrior.jpg");
        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
    }
    @GetMapping("img/monster.jpeg")
    public ResponseEntity<byte[]> getMonsterImg() throws IOException {
        File img = new File("src/main/resources/templates/img/monster.jpg");
        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
    }
}
