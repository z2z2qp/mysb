package me.will.sb.webclient;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

/**
 * me.will.sb.webclient.WebClientTest
 *
 * @author Zhuang Jiabin
 * @version V1.0.0
 * @since 2020 /8/12 14:59
 */
public class WebClientTest {

    private WebClient client;

    @BeforeAll
    private void before() {
        client = WebClient.builder().build();
    }

    /**
     * Test post json.
     */
    @Test
    public void testPostJSON() {
        var param = new HashMap<String, Object>();
        param.put("wd", "天气");
        var result = client.post().uri("https://www.baidu.com/s")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(param)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(result);
    }

    /**
     * Test get.
     */
    @Test
    public void testGet() {
        var result = client.get().uri("https://www.baidu.com/s?wd={wd}", "name")//占位符
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(result);
    }

    /**
     * Test url builder.
     */
    @Test
    public void testUrlBuilder() {
        var result = client.get()
                .uri(
                        uriBuilder -> uriBuilder
                                .scheme("https")
                                .host("www.baidu.com")
                                .path("/s")
                                .queryParam("wd", "天气")
                                .build()
                )
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(result);
    }

    /**
     * Test form param.
     */
    @Test
    public void testFormParam() {
        var param = new LinkedMultiValueMap<String, String>();
        param.add("wd", "天气");
        var result = client.post()
                .uri("https://www.baidu.com/s")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(param))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(result);
    }

    /**
     * Test upload file.
     */
    @Test
    public void testUploadFile() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        var entity = new HttpEntity<ClassPathResource>(new ClassPathResource("file.png"), headers);
        var param = new LinkedMultiValueMap<String, Object>();
        param.add("file", entity);
        var result = client.post()
                .uri("http://localhost")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(param))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(result);
    }

    /**
     * Test download.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testDownload() throws IOException {
        var result = client.get()
                .uri("http://localhost/download?id=1")
                .accept(MediaType.IMAGE_PNG)
                .retrieve()
                .bodyToMono(Resource.class)
                .block();
        var buffer = ImageIO.read(Objects.requireNonNull(result).getInputStream());
        ImageIO.write(buffer, "png", new File("new.png"));
    }
}
