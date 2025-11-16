package gamebox.game_2048.service;

import gamebox.game_2048.entity.Direction;
import gamebox.game_2048.entity.GameStatus;
import gamebox.game_2048.entity.Tile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class Game2048ServiceTest {

    @Test
    @DisplayName("서비스 생성 테스트")
    void serviceInstanceTest() {
        Game2048Service service = new Game2048Service(4, 4);
        assertThat(service).isInstanceOf(Game2048Service.class);
    }

    @Test
    @DisplayName("게임 시작 시 적어도 한 개의 타일이 생성된다")
    void atLeastOneTile() {
        Game2048Service service = new Game2048Service(4, 4);
        int value = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Tile tile = service.getTile(i, j);
                if (tile.getNumber() != 0) {
                    value++;
                }
            }
        }

        assertThat(value).isNotEqualTo(0);
    }

    @Test
    @DisplayName("게임 시작 시 타일 2개 생성")
    void initTwoTiles() {
        int tileCount = 0;

        Game2048Service service = new Game2048Service(4, 4);
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                Tile tile = service.getTile(r, c);
                if (tile.getNumber() != 0) {
                    tileCount++;
                }
            }
        }

        assertThat(tileCount).isEqualTo(2);
    }

    @Test
    @DisplayName("Tile 이동 및 병합 테스트")
    void tileMoveTest() {
        Game2048Service service = new Game2048Service(4, 4);

        // 초기 상태
        int[][] initial = {
                {2, 2, 2, 4},
                {2, 2, 4, 2},
                {2, 4, 2, 2},
                {4, 2, 2, 2}
        };

        // ------------------- UP 테스트 -------------------
        service.loadBoard(initial);
        service.tileMove(Direction.UP);
        int[][] upExpected = {
                {4, 4, 2, 4},
                {2, 4, 4, 4},
                {4, 2, 4, 2},
                {0, 0, 0, 0}
        };
        assertThat(service.snapshotBoard()).isDeepEqualTo(upExpected);

        // ------------------- DOWN 테스트 -------------------
        service.loadBoard(initial);
        service.tileMove(Direction.DOWN);
        int[][] downExpected = {
                {0, 0, 0, 0},
                {2, 4, 2, 4},
                {4, 4, 4, 2},
                {4, 2, 4, 4}
        };
        assertThat(service.snapshotBoard()).isDeepEqualTo(downExpected);

        // ------------------- LEFT 테스트 -------------------
        service.loadBoard(initial);
        service.tileMove(Direction.LEFT);
        int[][] leftExpected = {
                {4, 2, 4, 0},
                {4, 4, 2, 0},
                {2, 4, 4, 0},
                {4, 4, 2, 0}
        };
        assertThat(service.snapshotBoard()).isDeepEqualTo(leftExpected);

        // ------------------- RIGHT 테스트 -------------------
        service.loadBoard(initial);
        service.tileMove(Direction.RIGHT);
        int[][] rightExpected = {
                {0, 2, 4, 4},
                {0, 4, 4, 2},
                {0, 2, 4, 4},
                {0, 4, 2, 4}
        };
        assertThat(service.snapshotBoard()).isDeepEqualTo(rightExpected);
    }

    @Test
    @DisplayName("UP 이동 가능 테스트")
    void upMovable() {
        Game2048Service service = new Game2048Service(4, 4);
        service.loadBoard(new int[][]{
                {2, 0, 2, 4},
                {2, 4, 0, 4},
                {0, 2, 2, 0},
                {0, 0, 0, 0}
        });

        boolean changed = service.tileMove(Direction.UP);
        assertThat(changed).isTrue();
    }

    @Test
    @DisplayName("UP 이동 불가 테스트")
    void upImmovable() {
        Game2048Service service = new Game2048Service(4, 4);
        service.loadBoard(new int[][]{
                {2, 4, 2, 4},
                {4, 2, 4, 2},
                {2, 4, 2, 4},
                {4, 2, 4, 2}
        });

        boolean changed = service.tileMove(Direction.UP);
        assertThat(changed).isFalse();
    }

    @Test
    @DisplayName("DOWN 이동 가능 테스트")
    void downMovable() {
        Game2048Service service = new Game2048Service(4, 4);
        service.loadBoard(new int[][]{
                {2, 0, 2, 4},
                {2, 4, 0, 4},
                {0, 2, 2, 0},
                {0, 0, 0, 0}
        });

        boolean changed = service.tileMove(Direction.DOWN);
        assertThat(changed).isTrue();
    }

    @Test
    @DisplayName("DOWN 이동 불가 테스트")
    void downImmovable() {
        Game2048Service service = new Game2048Service(4, 4);
        service.loadBoard(new int[][]{
                {2, 4, 2, 4},
                {4, 2, 4, 2},
                {2, 4, 2, 4},
                {4, 2, 4, 2}
        });

        boolean changed = service.tileMove(Direction.DOWN);
        assertThat(changed).isFalse();
    }

    @Test
    @DisplayName("LEFT 이동 가능 테스트")
    void leftMovable() {
        Game2048Service service = new Game2048Service(4, 4);
        service.loadBoard(new int[][]{
                {0, 2, 2, 4},
                {2, 0, 4, 4},
                {0, 2, 2, 0},
                {0, 0, 0, 0}
        });

        boolean changed = service.tileMove(Direction.LEFT);
        assertThat(changed).isTrue();
    }

    @Test
    @DisplayName("LEFT 이동 불가 테스트")
    void leftImmovable() {
        Game2048Service service = new Game2048Service(4, 4);
        service.loadBoard(new int[][]{
                {2, 4, 2, 4},
                {4, 2, 4, 2},
                {2, 4, 2, 4},
                {4, 2, 4, 2}
        });

        boolean changed = service.tileMove(Direction.LEFT);
        assertThat(changed).isFalse();
    }

    @Test
    @DisplayName("RIGHT 이동 가능 테스트")
    void rightMovable() {
        Game2048Service service = new Game2048Service(4, 4);
        service.loadBoard(new int[][]{
                {0, 2, 2, 4},
                {2, 0, 4, 4},
                {0, 2, 2, 0},
                {0, 0, 0, 0}
        });

        boolean changed = service.tileMove(Direction.RIGHT);
        assertThat(changed).isTrue();
    }

    @Test
    @DisplayName("RIGHT 이동 불가 테스트")
    void rightImmovable() {
        Game2048Service service = new Game2048Service(4, 4);
        service.loadBoard(new int[][]{
                {2, 4, 2, 4},
                {4, 2, 4, 2},
                {2, 4, 2, 4},
                {4, 2, 4, 2}
        });

        boolean changed = service.tileMove(Direction.RIGHT);
        assertThat(changed).isFalse();
    }

    @Test
    @DisplayName("게임 오버 상태 테스트")
    void gameOverStatus() {
        Game2048Service service = new Game2048Service(4, 4);
        service.loadBoard(new int[][]{
                {2, 4, 2, 4},
                {4, 2, 4, 2},
                {2, 4, 2, 4},
                {4, 2, 4, 2}
        });

        assertThat(service.getGameStatus()).isEqualTo(GameStatus.GAME_OVER);
    }

    @Test
    @DisplayName("게임 진행 중 상태 테스트")
    void runningStatus() {
        Game2048Service service = new Game2048Service(4, 4);
        assertThat(service.getGameStatus()).isEqualTo(GameStatus.RUNNING);
    }
}