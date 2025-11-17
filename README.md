# 🎨 Swing 구현

## 🎯 학습 목표

- Java Swing을 활용해 **그래픽 기반 GUI 애플리케이션**을 설계하고 구현한다.
- **이벤트 기반 프로그래밍**을 이해하고, 사용자 입력(키보드/마우스)에 반응하는 구조를 만든다.
- 공통 클래스를 통해 **객체지향적 구조와 코드 재사용성**을 높인다.

---

## 🖥️ 화면 구조

```
┌─────────────────────────────────────────────┐
│              GameBoxFrame                   │
│  ┌─────────────────────────────────────┐    │
│  │          MainPanel                  │    │
│  │  ┌─────────────────────────────┐    │    │
│  │  │    BackgroundPanel          │    │    │
│  │  │  - 네비게이션 역할           │    │    │
│  │  └─────────────────────────────┘    │    │
│  │  ┌─────────────────────────────┐    │    │
│  │  │     ContentPanel            │    │    │
│  │  │  ┌──────────────────────┐   │    │    │
│  │  │  │ • GameButtonPane     │   │    │    │
│  │  │  │   - 게임 선택 화면    │   │    │    │
│  │  │  │ • Game2048Panel      │   │    │    │
│  │  │  │   - 2048             │   │    │    │
│  │  │  │ • GameSamePicPanel   │   │    │    │
│  │  │  │   - 같은 그림 찾기    │   │    │    │
│  │  │  └──────────────────────┘   │    │    │
│  │  └─────────────────────────────┘    │    │
│  └─────────────────────────────────────┘    │
└─────────────────────────────────────────────┘
```

<details>

<summary> Swing 기본 구조 </summary>

Swing의 기본 구조는 크게 컨테이너(Container), 컴포넌트(Component), 그리고 레이아웃(Layout)으로 나눠진다.

### 1. 컨테이너 (Container)

컨테이너는 다른 컴포넌트들을 **담고 관리**하는 역할을 한다. `JFrame`이라는 최상위 컨테이너를 사용하여 애플리케이션 창을 만든다.

- **JFrame:** 애플리케이션의 **기본 창**을 만드는 클래스이다. 가장 바깥 창이 `JFrame`이 된다.
- **JPanel:** 하나의 **부분적인 컨테이너**로, 여러 UI 컴포넌트를 포함할 수 있는 영역을 만든다. `JFrame` 안에 여러 개의 `JPanel`을 배치하여 UI를 세분화할 수 있다.

ex)

```java
JFrame frame = new JFrame("Game Window");
frame.

setSize(600,600); // 창의 크기 설정
frame.

setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫을 때 애플리케이션 종료
frame.

setVisible(true); // 창을 보이게 설정
```

![JFrame과 JPanel의 구조](JFrame_JPanel_structure.png)

### 2. 컴포넌트 (Component)

컴포넌트는 **GUI를 구성하는 기본 요소**로, 사용자와의 상호작용을 담당한다. 컴포넌트는 버튼, 텍스트 필드, 레이블 등 다양한 UI 요소들을 말한다.

- **JButton:** 버튼 컴포넌트. 클릭 이벤트를 처리할 수 있다.
- **JLabel:** 텍스트나 이미지를 표시하는 컴포넌트.
- **JTextField:** 사용자가 텍스트를 입력할 수 있는 필드.
- **JCheckBox, JRadioButton:** 체크박스나 라디오 버튼과 같은 선택 UI를 제공한다.

ex)

```java
JButton startButton = new JButton("Start Game");
JLabel scoreLabel = new JLabel("Score: 0");
```

### 3. 레이아웃 (Layout)

레이아웃은 컴포넌트가 **어떻게 배치**될지를 결정하는 방식이다.

- **FlowLayout:** 컴포넌트를 **왼쪽에서 오른쪽으로, 위에서 아래**로 순차적으로 배치한다. 기본적인 레이아웃이다.
- **BorderLayout:** **상, 하, 좌, 우, 중앙**의 5개 영역에 컴포넌트를 배치할 수 있다. (NORTH, SOUTH, EAST, WEST, CENTER)
- **GridLayout:** 컴포넌트를 **격자 형태**로 배치한다. 지정된 행과 열 수에 맞게 컴포넌트를 균등하게 배치한다.
- **CardLayout:** 여러 패널을 **카드처럼 겹쳐서** 배치하고, 한 번에 하나만 보여준다. 화면 전환에 유용하다.
- **BoxLayout:** 컴포넌트를 **수평 또는 수직**으로 배치한다.

ex)

```java
frame.setLayout(new BorderLayout());
        frame.

add(startButton, BorderLayout.NORTH);
frame.

add(scoreLabel, BorderLayout.CENTER);
```

### 4. 이벤트 처리

Swing에서 사용자의 입력에 반응하려면 **이벤트 처리**가 필요하다. 이벤트는 사용자가 버튼을 클릭하거나, 키를 누르는 등의 동작을 의미한다.

- **ActionListener:** 버튼을 클릭하거나 특정 동작을 했을 때 이벤트를 처리한다.
- **KeyListener / KeyAdapter:** 키보드 입력을 처리한다. KeyAdapter는 필요한 메소드만 오버라이드할 수 있다.
- **MouseListener:** 마우스 클릭이나 드래그 등 마우스 이벤트를 처리한다.

ex)

```java
startButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed (ActionEvent e){
        System.out.println("Game Started!");
    }
});
```

</details>

---

## 📁 프로젝트 구조

```
gamebox/
├── swing/
│   ├── frame/
│   │   └── GameBoxFrame.java          # 메인 프레임
│   ├── panel/
│   │   ├── MainPanel.java             # 메인 패널 (전체 레이아웃 관리)
│   │   ├── BackgroundPanel.java       # 상단 네비게이션
│   │   ├── GameButtonPanel.java       # 게임 선택 화면
│   │   ├── Game2048Panel.java         # 2048 게임
│   │   └── GameSamePicPanel.java      # 같은 그림 찾기 게임
│   ├── components/
│   │   ├── Grid.java                  # 재사용 가능한 그리드
│   │   ├── ImageButton.java           # 이미지 버튼
│   │   ├── TilePanel.java             # 2048 타일
│   │   └── DifficultySelectPanel.java # 난이도 선택
│   ├── listener/
│   │   ├── AppListener.java           # 네비게이션 리스너
│   │   ├── Game2048KeyListener.java   # 2048 키 입력
│   │   └── GameListener.java          # 게임 공통 리스너
│   └── swing_util/
│       └── SwingUtils.java            # UI 유틸리티
```

---

## 📌 구현 기능 목록

### 📌 메인 프레임 (GameBoxFrame)

- [x] 애플리케이션의 메인 윈도우를 생성한다.
- [x] 창 크기(1000x800), 제목("GameBox"), 종료 방식을 설정한다.
- [x] MainPanel을 포함하여 전체 앱 구조를 관리한다.

### 📌 패널 구조

- [x] **MainPanel**: 전체 레이아웃을 관리하는 메인 패널
    - [x] BackgroundPanel (상단): 타이틀과 홈버튼을 표시
    - [x] ContentPanel (중앙): 게임 선택 화면과 실제 게임 화면을 표시

- [x] **BackgroundPanel**: 상단 네비게이션 영역
    - [x] GameBox 타이틀 표시
    - [x] "게임을 선택하세요" 메시지 표시
    - [x] 홈버튼 표시/숨김 기능

- [x] **GameButtonPanel**: 게임 선택 화면
    - [x] 2048 게임 버튼
    - [x] 같은 그림 찾기 게임 버튼
    - [x] null 레이아웃을 사용한 절대 위치 배치

### 📌 게임별 패널

- [x] **Game2048Panel**: 2048 게임 구현
    - [x] 4x4 그리드 게임 보드
    - [x] Reset 버튼으로 게임 초기화
    - [x] 방향키 입력 처리 (KeyListener)
    - [x] 게임 승리/패배 메시지 표시

- [x] **GameSamePicPanel**: 같은 그림 찾기 게임 구현
    - [x] CardLayout을 통한 화면 전환 (난이도 선택 → 게임)
    - [x] 난이도별 그리드 크기 (4x4, 6x6, 8x8)
    - [x] 카드 뒤집기 애니메이션 (Timer 사용)
    - [x] 게임 클리어 시 이동 횟수 표시

### 📌 공통 컴포넌트

- [x] **Grid**: 재사용 가능한 그리드 패널 생성
    - [x] GridLayout으로 행/열 구성
    - [x] 패딩 설정

- [x] **ImageButton**: 이미지 버튼 컴포넌트
    - [x] Picture 객체를 받아 이미지 버튼 생성
    - [x] 이미지 스케일링 기능
    - [x] ClientProperty로 이미지 정보 저장

- [x] **TilePanel**: 2048 게임용 타일 표시 패널
    - [x] 숫자와 색상 표시
    - [x] 빈 타일 처리

- [x] **DifficultySelectPanel**: 난이도 선택 UI
    - [x] 쉬움/보통/어려움 버튼
    - [x] Consumer 패턴으로 선택 결과 전달

### 📌 이벤트 리스너

- [x] **AppListener**: 메인 네비게이션 이벤트 처리
    - [x] 게임 선택 버튼 클릭 처리
    - [x] 홈버튼 클릭 시 메인 화면으로 복귀
    - [x] 화면 전환 시 UI 업데이트

- [x] **Game2048KeyListener**: 2048 게임 키보드 입력 처리
    - [x] 방향키 입력 감지 (상/하/좌/우)
    - [x] 이동 성공 시 콜백 실행

- [x] **GameListener**: 게임 공통 확인 대화상자 처리
    - [x] JOptionPane으로 확인 대화상자 표시
    - [x] YES/NO 선택에 따른 액션 실행

### 📌 유틸리티

- [x] **SwingUtils**: UI 새로고침 유틸리티
    - [x] revalidate()와 repaint()를 통한 컴포넌트 새로고침

### 📌 사용자 인터랙션

- [x] 사용자는 게임을 시작하기 위해 게임 창을 실행한다.
- [x] 사용자는 메인 화면에서 원하는 게임을 선택한다.
- [x] 게임 중 홈버튼을 통해 메인 화면으로 돌아갈 수 있다.
- [x] 각 게임별 리셋/뒤로가기 기능을 제공한다.

### 📌 예외 처리

- [x] 이미지 로드 실패 시 빈 ImageIcon 반환
- [x] 난이도 선택 시 null 체크로 IllegalStateException 방지
