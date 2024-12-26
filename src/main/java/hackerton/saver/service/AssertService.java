package hackerton.saver.service;

import com.github.f4b6a3.ulid.UlidCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssertService {
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "png", "jpeg", "webp","pdf","pptx","ppt","docx");


    private final S3Client s3Client;


    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    /** MultipartFile을 전달받아 File로 전환한 후 S3에 업로드  */
    public String upload(MultipartFile multipartFile, String dirName) throws IOException,Exception {
        log.info("Uploading " + multipartFile.getOriginalFilename() + " to " + dirName);
        if(!isValidFileExtension(multipartFile.getOriginalFilename())){
            throw new RuntimeException("지원하는 확장자가 아닙니다.");
        }
        return uploadFileToS3Direct(multipartFile,dirName);
    }


    private String uploadFileToS3Direct(MultipartFile uploadFile, String dirName) throws IOException{

        InputStream inputStream = uploadFile.getInputStream();

        String objectKey = dirName + "/" + UlidCreator.getUlid().toString() + "."+getFileExtension(uploadFile.getOriginalFilename());

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(objectKey)
                .contentType(uploadFile.getContentType())
                .contentLength(uploadFile.getSize())
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, uploadFile.getSize()));
        inputStream.close();
        return s3Client.utilities().getUrl(b-> b.bucket(bucket).key(objectKey)).toString();     // 업로드된 파일의 S3 URL 주소 반환
    }

    private boolean isValidFileExtension(String fileName) {
        String fileExtension = getFileExtension(fileName).toLowerCase();  // 파일 확장자 추출
        return ALLOWED_EXTENSIONS.contains(fileExtension);
    }

    private String getFileExtension(String filename) {
        int dot = filename.lastIndexOf(".");
        return (dot > 0) ? filename.substring(dot + 1) : "";
    }

}
