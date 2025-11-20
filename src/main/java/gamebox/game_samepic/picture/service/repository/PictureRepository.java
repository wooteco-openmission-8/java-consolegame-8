package gamebox.game_samepic.picture.service.repository;

import gamebox.game_samepic.picture.service.entity.Picture;
import gamebox.util.exceptions.ErrorType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Picture 엔티티를 관리하는 Repository (싱글톤 패턴)
 */
public class PictureRepository {
    private static PictureRepository pictureRepository;
    private final Map<String, Picture> pictureMap = new HashMap<>();

    /**
     * PictureRepository 생성자 인스턴스 생성 시 32개의 기본 그림 데이터를 자동으로 초기화
     */
    private PictureRepository() {
        initializePictures();
    }

    /**
     * 싱글톤 인스턴스 반환
     *
     * @return PictureRepository의 유일한 인스턴스
     */
    public static PictureRepository getInstance() {
        if (pictureRepository == null) {
            pictureRepository = new PictureRepository();
        }
        return pictureRepository;
    }

    /**
     * 32개의 기본 그림 데이터 초기화
     */
    private void initializePictures() {
        for (int i = 1; i <= 32; i++) {
            Picture pic = new Picture.Builder()
                    .id("picture_" + i)
                    .title("Picture " + i)
                    .path("/images/find_same/pic" + i + ".png")
                    .build();
            pictureMap.put(pic.getId(), pic);
        }
    }

    /**
     * ID로 그림 조회
     *
     * @param id 조회할 그림의 ID
     * @return 조회된 Picture 객체
     * @throws IllegalArgumentException 해당 ID의 그림이 존재하지 않는 경우
     */
    public Picture findById(String id) {
        Picture picture = pictureMap.get(id);
        if (picture == null) {
            throw new IllegalArgumentException(ErrorType.NOT_EXIST_PICTURE.getMessage() + id);
        }
        return picture;
    }

    /**
     * 모든 그림 ID 목록 조회
     *
     * @return 모든 그림의 ID 리스트
     */
    public List<String> findAllIds() {
        return new ArrayList<>(pictureMap.keySet());
    }
}