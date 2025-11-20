package gamebox.game_samepic.picture.service.entity;

/**
 * 같은 그림 찾기 게임에서 사용되는 그림 정보를 나타내는 엔티티
 */
public class Picture {

    private String id;
    private String title;
    private String path;

    private Picture() {
    }

    /**
     * 그림 ID 조회
     *
     * @return 그림의 고유 식별자
     */
    public String getId() {
        return id;
    }

    /**
     * 그림 제목 조회
     *
     * @return 그림의 제목
     */
    public String getTitle() {
        return title;
    }

    /**
     * 그림 파일 경로 조회
     *
     * @return 그림 파일의 경로
     */
    public String getPath() {
        return path;
    }

    /**
     * Picture 객체를 생성하기 위한 Builder 클래스
     */
    public static class Builder {
        private final Picture picture = new Picture();

        /**
         * 그림 ID 설정
         *
         * @param id 그림의 고유 식별자
         * @return Builder 인스턴스
         */
        public Builder id(String id) {
            picture.id = id;
            return this;
        }

        /**
         * 그림 제목 설정
         *
         * @param title 그림의 제목
         * @return Builder 인스턴스
         */
        public Builder title(String title) {
            picture.title = title;
            return this;
        }

        /**
         * 그림 파일 경로 설정
         *
         * @param path 그림 파일의 경로
         * @return Builder 인스턴스
         */
        public Builder path(String path) {
            picture.path = path;
            return this;
        }

        /**
         * Picture 객체 생성
         *
         * @return 생성된 Picture 객체
         */
        public Picture build() {
            return picture;
        }
    }
}