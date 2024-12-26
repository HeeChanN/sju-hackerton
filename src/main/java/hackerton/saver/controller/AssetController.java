package hackerton.saver.controller;


import hackerton.saver.service.AssertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class AssetController
{
    private final AssertService assertService;

    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> uploadImage(
            @RequestParam(value = "file",required = true) MultipartFile files){
        try {
            String image =assertService.upload(files, "uploads");
            return ResponseEntity.status(HttpStatus.OK).body(image);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
