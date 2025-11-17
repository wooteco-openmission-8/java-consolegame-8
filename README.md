# 🎮 같은 그림 찾기

---

## 🎯 게임 규칙

1. **게임 보드**

   카드 형태의 격자판 위에서 게임이 진행됩니다. 각 칸에는 그림이 숨겨져 있으며, 플레이어는 직접 카드를 선택해 그림을 확인합니다.

2. **게임 시작**

   모든 카드는 뒤집힌 상태로 시작하며, 플레이어는 두 장씩 카드를 선택하며 그림을 맞춰야 합니다.

3. **카드 선택 및 매칭**
    - 한 번에 두 장의 카드를 선택할 수 있습니다.
    - 선택한 카드의 그림이 같으면 카드가 제거됩니다.
    - 그림이 다르면 카드가 다시 뒤집혀 원래 상태로 돌아갑니다.

4. **난이도 설정**

   난이도(`Difficulty`)에 따라 카드 수와 배치가 달라집니다.

    - EASY: 카드 수 적음, 매칭 쉬움
    - NORMAL: 적당한 난이도
    - HARD: 카드 수 많음, 매칭 어려움

5. **승리 조건**

   보드 위 모든 카드를 제거하면 게임에서 승리합니다.

## 📁 프로젝트 구조

```
gamebox/
├── game_samepic/
│   ├── game/
│   │   ├── controller/
│   │   │   └── GameSamePicController.java    # 게임 컨트롤러
│   │   ├── entity/
│   │   │   ├── GameSamePicBoard.java        # 게임 보드 관리
│   │   │   ├── Card.java                    # 카드 엔티티
│   │   │   └── Difficulty.java              # 난이도 설정
│   │   └── service/
│   │       └── GameSamePicService.java      # 게임 로직 서비스
│   └── picture/
│       └── service/
│           ├── entity/
│           │   └── Picture.java             # 그림 엔티티
│           └── repository/
│               └── PictureRepository.java   # 그림 저장소
└── common/
    └── Game.java                            # 게임 인터페이스
```

---

## 📌 구현 기능 목록

### 📌 1. 게임 컨트롤러 (`GameSamePicController`)

- [x] `Game` 인터페이스 구현
- [x] `start()` - 게임 보드 및 카드 초기화
- [x] `getName()` - 게임 이름 반환
- [x] 카드 선택/매칭 처리 메서드
- [x] 게임 상태 반환 (`RUNNING` / `WIN` / `GAME_OVER`)

### 📌 2. 카드 클래스 (`Card`)

- [x] 그림 ID, 위치 정보 관리
- [x] `isMatched()` - 카드 매칭 여부 확인
- [x] 카드 선택/해제 처리

### 📌 3. 게임 보드 (`GameSamePicBoard`)

- [x] 카드 배열 관리
- [x] 초기 카드 배치 및 섞기
- [x] 카드 선택 가능 여부 체크
- [x] 테스트용 메서드:
    - [x] `loadFrom(Card[][])` - 특정 배열로 보드 초기화
    - [x] `snapshotBoard()` - 현재 보드 상태 스냅샷

### 📌 4. 게임 서비스 (`GameSamePicService`)

- [x] 카드 선택/매칭 로직
- [x] 게임 상태 판정 (승리/패배)
- [x] 난이도 설정 적용
- [x] 테스트용 메서드:
    - [x] `loadBoard(Card[][])` - 보드 상태 로드
    - [x] `snapshotBoard()` - 보드 상태 스냅샷

### 📌 5. 그림 관리 (`Picture` / `PictureRepository`)

- [x] 그림 엔티티 (`Picture`) 관리
- [x] 그림 저장소 (`PictureRepository`)에서 이미지 조회 및 저장
- [x] 게임 보드에 필요한 그림 랜덤 배치 지원

### 📌 6. 열거형 타입

#### Difficulty

```
EASY, NORMAL, HARD
```

#### GameStatus

```
RUNNING, WIN, GAME_OVER
```

---
