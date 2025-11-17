# 🎮 2048 Game

## 🎯 게임 규칙

1. **게임 보드**: 4x4 격자
2. **시작**: 무작위 두 칸에 2 또는 4가 생성 (90% 확률로 2, 10% 확률로 4)
3. **이동 메커니즘**:
   - 상하좌우 방향키로 모든 타일 이동
   - 같은 숫자의 타일이 충돌하면 병합 (두 배의 값)
   - 한 번의 이동에서 같은 타일은 한 번만 병합
4. **새 타일 생성**: 이동 후 무작위 빈 칸에 2 또는 4 생성
5. **승리 조건**: 2048 타일 생성
6. **패배 조건**: 보드가 가득 차고 더 이상 이동 불가능

## 📁 프로젝트 구조

```
gamebox.game_2048/
├── controller/
│   └── Game2048Controller.java    # 게임 메인 컨트롤러
├── entity/
│   ├── Direction.java              # 이동 방향 열거형
│   ├── Game2048Board.java          # 게임 보드 관리
│   ├── GameStatus.java             # 게임 상태 열거형
│   ├── Tile.java                   # 타일 엔티티
│   └── TileValue.java              # 타일 값별 색상 정의
└── service/
    └── Game2048Service.java        # 게임 로직 서비스
```

## 기능 구현 목록

### 📌 1. 게임 컨트롤러 (`Game2048Controller`)
- [x] `Game` 인터페이스 구현
- [x] `start()` - 4x4 보드로 게임 서비스 초기화
- [x] `getName()` - "2048" 반환
- [x] 방향별 이동 메서드 (`moveUp()`, `moveDown()`, `moveLeft()`, `moveRight()`)
- [x] `getTile(row, col)` - 특정 위치 타일 조회
- [x] `getGameStatus()` - 게임 상태 반환 (RUNNING/WIN/GAME_OVER)

### 📌 2. 타일 클래스 (`Tile`)
- [x] 타일 값 유효성 검증 (0 또는 2~2048 사이의 2의 거듭제곱)
- [x] `isEmpty()` - 빈 타일 여부 확인
- [x] `canMergeWith(Tile other)` - 병합 가능 여부 확인
- [x] `merge()` - 타일 값 두 배로 병합
- [x] `merge(Tile other)` - 두 타일을 병합한 새 타일 반환
- [x] `moveTo(Tile other)` - 타일 이동
- [x] `spawn()` - 빈 타일에 2(90%) 또는 4(10%) 생성
- [x] `getBackgroundColor()`, `getTextColor()` - TileValue enum 연동 색상 반환
- [x] `resetMerged()` - 병합 플래그 초기화

### 📌 3. 게임 보드 (`Game2048Board`)
- [x] `Tile[][]` 2차원 배열로 보드 관리
- [x] 생성자에서 초기 타일 2개 자동 스폰
- [x] `filterColumn(int)`, `filterRow(int)` - 0이 아닌 타일 필터링
- [x] `randomSpawn(int n)` - n개의 타일 랜덤 생성
- [x] `isFull()` - 보드 가득 참 여부 확인
- [x] `canMove()` - 이동 가능 여부 확인 (인접 타일 병합 가능 체크)
- [x] `isWin()`, `setWin()` - 승리 상태 관리
- [x] 테스트용 메서드:
  - [x] `loadFrom(int[][])` - 특정 숫자 배열로 보드 로드
  - [x] `snapshotNumbers()` - 현재 보드 상태 스냅샷

### 📌 4. 게임 서비스 (`Game2048Service`)
- [x] `move(Direction)` - 타일 이동 + 새 타일 생성
- [x] `tileMove(Direction)` - 방향별 타일 이동 처리
- [x] `moveVertical(boolean reverse)` - 수직 이동 (위/아래)
- [x] `moveHorizontal(boolean reverse)` - 수평 이동 (좌/우)
- [x] `merge(List<Tile>)` - 타일 리스트 병합 로직
- [x] `isGameOver()` - 게임 오버 판정
- [x] `isWin()` - 승리 판정
- [x] `getGameStatus()` - 게임 상태 반환
- [x] 테스트용 메서드:
  - [x] `loadBoard(int[][])` - 보드 상태 로드
  - [x] `snapshotBoard()` - 보드 상태 스냅샷


### 📌 5. 열거형 타입

#### Direction
```java
UP, DOWN, LEFT, RIGHT
```

#### GameStatus  
```java
RUNNING, WIN, GAME_OVER
```

#### TileValue
- 타일 값별 배경색과 텍스트 색상 정의
- 0(EMPTY) ~ 2048까지 각 값에 대한 색상 매핑
- `fromValue(int)` - 값으로 TileValue 조회
