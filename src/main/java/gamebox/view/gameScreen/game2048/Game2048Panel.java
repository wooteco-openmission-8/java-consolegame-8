package gamebox.view.gameScreen.game2048;

import gamebox.game2048.Tile;
import gamebox.game2048.service.entity.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game2048Panel extends JPanel {
    private static final int GRID_GAP = 3;
    private static final int GRID_SIZE = 4;

    private TilePanel[][] tile;
    private Board board;

    public Game2048Panel() {
        setupPanel();
    }

    private void setupPanel() {
        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE, GRID_GAP, GRID_GAP));
        setBackground(Color.white);
        initBoard();

        // 키 입력이 가능하도록 설정.
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    System.out.println("위 방향키 누름");
                    board.upTile();
                    board.randomSpawn(1);
                    updateBoard();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    System.out.println("아래 방향키 누름");
                    board.downTile();
                    board.randomSpawn(1);
                    updateBoard();
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    System.out.println("왼쪽 방향키 누름");
                    board.leftTile();
                    board.randomSpawn(1);
                    updateBoard();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    System.out.println("오른쪽 방향키 누름");
                    board.rightTile();
                    board.randomSpawn(1);
                    updateBoard();
                }
            }
        });

        // 화면이 완전히 보인 뒤 실행하도록.
        SwingUtilities.invokeLater(this::requestFocusInWindow);
    }

    private void initBoard() {
        // TODO: Board(Tile[][]) 초기화
        board = new Board(GRID_SIZE, GRID_SIZE);
        drawTiles();
    }

    public void updateBoard() {
        // TODO: 전달받은 Board 상태를 패널 내부 변수에 저장
        removeAll();
        drawTiles();
        revalidate();
        repaint();
    }

    private void drawTiles() {
        tile = new TilePanel[GRID_SIZE][GRID_SIZE];
        for (int r=0; r<GRID_SIZE; r++) {
            for (int c=0; c<GRID_SIZE; c++) {
                TilePanel currentTile = tile[r][c];
                currentTile = new TilePanel();

                /**
                 * 2048 컨트롤러 구현 완료 후 수정
                 */
                Tile tile = board.get(r, c);
                if (tile.getNumber() != 0) {
                    currentTile.setTile(tile.getNumber());
                    currentTile.setColor(tile.getBackgroundColor());
                }

                add(currentTile);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // TODO: 내부 Board 상태를 읽어 타일을 화면에 그리기
    }
}
