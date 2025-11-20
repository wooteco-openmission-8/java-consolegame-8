package gamebox.game_samepic.picture.service.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PictureTest {

    @Test
    @DisplayName("Picture 빌더 테스트")
    void pictureBuilderTest() {
        String id = "test-id";
        String path = "/path";
        String title = "title";

        Picture picture = new Picture.Builder()
                .id(id)
                .path(path)
                .title(title)
                .build();

        assertThat(picture).isInstanceOf(Picture.class);
        assertThat(picture.getId()).isEqualTo(id);
        assertThat(picture.getPath()).isEqualTo(path);
        assertThat(picture.getTitle()).isEqualTo(title);
    }
}
