package gamebox.game_samepic.game.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameSamePicServiceTest {
    @Test
    @DisplayName("이미지 경로 테스트")
    void imagePathTest() {
        String path = "images/find_same/fullimage.png";

        URL url = Thread.currentThread().getContextClassLoader().getResource(path);

        assertNotNull(url, "[ERROR] 리소스를 찾을 수 없습니다. " + path);

        try {
            BufferedImage image = ImageIO.read(url);
            assertNotNull(image, "[ERROR] 이미지 읽기 실패: " + path);
            System.out.println("이미지 로드 성공: " + path);
        } catch (Exception e) {
            fail("[ERROR] 이미지 로드 중 예외 발생: " + e.getMessage());
        }
    }
}